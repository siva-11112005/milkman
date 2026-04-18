package com.milkman.dairyapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.databinding.FragmentHomeBinding
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

    private var adminSummaryLiveData: LiveData<AdminDailySummary>? = null
    private var customerSummaryLiveData: LiveData<CustomerDailySummary>? = null

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
