package com.milkman.dairyapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.databinding.FragmentHomeBinding
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.util.TimeUtils
import com.milkman.dairyapp.viewmodel.AdminDailySummary
import com.milkman.dairyapp.viewmodel.CustomerDailySummary
import com.milkman.dairyapp.viewmodel.DashboardViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DashboardViewModel
    private lateinit var sessionManager: SessionManager

    private var selectedDate: String = TimeUtils.currentDate()
    private var linkedSupplierId: Int? = null
    private var selectedSupplierId: Int? = null
    private var selectedBuyerId: Int? = null
    private var supplierProfiles: List<CustomerEntity> = emptyList()
    private var buyerProfiles: List<CustomerEntity> = emptyList()

    private var adminSummaryLiveData: LiveData<AdminDailySummary>? = null
    private var customerSummaryLiveData: LiveData<CustomerDailySummary>? = null
    private var supplierAmountLiveData: LiveData<Double>? = null
    private var buyerAmountLiveData: LiveData<Double>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        val calendar = Calendar.getInstance()
        binding.calendarView.date = calendar.timeInMillis
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = TimeUtils.dateFromPicker(year, month, dayOfMonth)
            updateDateDisplay()
            loadDataForDate()
        }

        updateDateDisplay()

        if (sessionManager.isAdmin()) {
            binding.cardAdminSection.visibility = View.VISIBLE
            binding.cardCustomerSection.visibility = View.GONE
            setupAdminPartnerSelectors()
            loadDataForDate()
        } else {
            binding.cardAdminSection.visibility = View.GONE
            binding.cardCustomerSection.visibility = View.VISIBLE
            resolveSupplierAndLoad()
        }
    }

    private fun resolveSupplierAndLoad() {
        lifecycleScope.launch {
            val user = AppDatabase.getInstance(requireContext()).userDao().findById(sessionManager.userId().toInt())
            linkedSupplierId = user?.linkedCustomerId
            if (linkedSupplierId == null) {
                Toast.makeText(requireContext(), "Supplier profile not linked", Toast.LENGTH_SHORT).show()
                return@launch
            }
            loadDataForDate()
        }
    }

    private fun updateDateDisplay() {
        binding.tvSelectedDate.text = "Selected Date: ${TimeUtils.toDisplayDate(selectedDate)}"
    }

    private fun loadDataForDate() {
        if (sessionManager.isAdmin()) {
            adminSummaryLiveData?.removeObservers(viewLifecycleOwner)
            adminSummaryLiveData = viewModel.getAdminDailySummary(selectedDate)
            adminSummaryLiveData?.observe(viewLifecycleOwner) { summary ->
                bindAdminSummary(summary)
            }
            observeSelectedSupplierAmount()
            observeSelectedBuyerAmount()
        } else {
            val supplierId = linkedSupplierId ?: return
            customerSummaryLiveData?.removeObservers(viewLifecycleOwner)
            customerSummaryLiveData = viewModel.getCustomerDailySummary(selectedDate, supplierId)
            customerSummaryLiveData?.observe(viewLifecycleOwner) { summary ->
                bindCustomerSummary(summary)
            }
        }
    }

    private fun bindAdminSummary(summary: AdminDailySummary) {
        binding.tvMilkCollected.text = "%.2f L".format(summary.milkCollected)
        binding.tvMilkSold.text = "%.2f L".format(summary.milkSold)
        binding.tvMilkTeaShop.text = "%.2f L".format(summary.milkToTeaShops)
        binding.tvMilkIndustry.text = "%.2f L".format(summary.milkToIndustries)
        binding.tvBuyingAmount.text = "₹ %.2f".format(summary.buyingAmount)
        binding.tvSellingAmount.text = "₹ %.2f".format(summary.sellingAmount)
        binding.tvProfitAmount.text = "₹ %.2f".format(summary.profit)
        val profitColor = if (summary.profit >= 0) {
            com.milkman.dairyapp.R.color.success_green
        } else {
            com.milkman.dairyapp.R.color.error_red
        }
        binding.tvProfitAmount.setTextColor(ContextCompat.getColor(requireContext(), profitColor))
    }

    private fun setupAdminPartnerSelectors() {
        viewModel.suppliers.observe(viewLifecycleOwner) { suppliers ->
            supplierProfiles = suppliers
            val labels = if (suppliers.isEmpty()) {
                listOf("No suppliers found")
            } else {
                suppliers.map { it.name }
            }
            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, labels)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerAdminSupplier.adapter = spinnerAdapter

            val nextSupplierId = suppliers.firstOrNull { it.id == selectedSupplierId }?.id
                ?: suppliers.firstOrNull()?.id
            selectedSupplierId = nextSupplierId
            binding.spinnerAdminSupplier.isEnabled = suppliers.isNotEmpty()

            if (suppliers.isNotEmpty()) {
                val selectedIndex = suppliers.indexOfFirst { it.id == selectedSupplierId }.coerceAtLeast(0)
                binding.spinnerAdminSupplier.setSelection(selectedIndex)
            } else {
                binding.tvAdminSupplierPaid.text = "₹ 0.00"
            }

            observeSelectedSupplierAmount()
        }

        binding.spinnerAdminSupplier.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSupplierId = supplierProfiles.getOrNull(position)?.id
                observeSelectedSupplierAmount()
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) = Unit
        }

        viewModel.buyers.observe(viewLifecycleOwner) { buyers ->
            buyerProfiles = buyers
            val labels = if (buyers.isEmpty()) {
                listOf("No buyers found")
            } else {
                buyers.map { it.name }
            }
            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, labels)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerAdminBuyer.adapter = spinnerAdapter

            val nextBuyerId = buyers.firstOrNull { it.id == selectedBuyerId }?.id
                ?: buyers.firstOrNull()?.id
            selectedBuyerId = nextBuyerId
            binding.spinnerAdminBuyer.isEnabled = buyers.isNotEmpty()

            if (buyers.isNotEmpty()) {
                val selectedIndex = buyers.indexOfFirst { it.id == selectedBuyerId }.coerceAtLeast(0)
                binding.spinnerAdminBuyer.setSelection(selectedIndex)
            } else {
                binding.tvAdminBuyerEarned.text = "₹ 0.00"
            }

            observeSelectedBuyerAmount()
        }

        binding.spinnerAdminBuyer.onItemSelectedListener = object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedBuyerId = buyerProfiles.getOrNull(position)?.id
                observeSelectedBuyerAmount()
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) = Unit
        }
    }

    private fun observeSelectedSupplierAmount() {
        supplierAmountLiveData?.removeObservers(viewLifecycleOwner)
        val supplierId = selectedSupplierId ?: run {
            binding.tvAdminSupplierPaid.text = "₹ 0.00"
            return
        }
        supplierAmountLiveData = viewModel.getPartnerDailyAmount(
            date = selectedDate,
            customerId = supplierId,
            entryType = AppConstants.ENTRY_COLLECTION
        )
        supplierAmountLiveData?.observe(viewLifecycleOwner) { amount ->
            binding.tvAdminSupplierPaid.text = "₹ %.2f".format(amount)
        }
    }

    private fun observeSelectedBuyerAmount() {
        buyerAmountLiveData?.removeObservers(viewLifecycleOwner)
        val buyerId = selectedBuyerId ?: run {
            binding.tvAdminBuyerEarned.text = "₹ 0.00"
            return
        }
        buyerAmountLiveData = viewModel.getPartnerDailyAmount(
            date = selectedDate,
            customerId = buyerId,
            entryType = AppConstants.ENTRY_DISTRIBUTION
        )
        buyerAmountLiveData?.observe(viewLifecycleOwner) { amount ->
            binding.tvAdminBuyerEarned.text = "₹ %.2f".format(amount)
        }
    }

    private fun bindCustomerSummary(summary: CustomerDailySummary) {
        binding.tvCustomerTotalMilk.text = "%.2f L".format(summary.totalQuantity)
        binding.tvCustomerMorningMilk.text = "%.2f L".format(summary.morningQuantity)
        binding.tvCustomerEveningMilk.text = "%.2f L".format(summary.eveningQuantity)
        binding.tvCustomerEarnings.text = "₹ %.2f".format(summary.earnings)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
