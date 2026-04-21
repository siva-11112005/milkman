package com.milkman.dairyapp.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.databinding.ActivityPartnerSummaryBinding
import com.milkman.dairyapp.ui.adapters.MilkEntryAdapter
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.TimeUtils
import com.milkman.dairyapp.viewmodel.PartnerMonthlySummary
import com.milkman.dairyapp.viewmodel.PartnerSummaryViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PartnerSummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPartnerSummaryBinding
    private lateinit var viewModel: PartnerSummaryViewModel

    private lateinit var dayEntryAdapter: MilkEntryAdapter

    private var customerId: Int = -1
    private var customerName: String = ""
    private var customerCategory: String = ""
    private var customerType: String = ""
    private var pricePerLiter: Double = 0.0
    private var entryType: String = AppConstants.ENTRY_COLLECTION
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    private var selectedDate: String = TimeUtils.currentDate()
    private var suppressCalendarCallback: Boolean = false

    private var dayEntriesLiveData: LiveData<List<MilkEntryWithCustomer>>? = null
    private var monthSummaryLiveData: LiveData<PartnerMonthlySummary>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartnerSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!readIntentData()) {
            Toast.makeText(this, "Invalid partner details", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[PartnerSummaryViewModel::class.java]

        setupToolbar()
        setupPartnerHeader()
        setupEntryList()
        setupCalendar()
        setupMonthNavigation()
        updateDateUi()
        observeForDate()
    }

    private fun readIntentData(): Boolean {
        customerId = intent.getIntExtra(EXTRA_CUSTOMER_ID, -1)
        customerName = intent.getStringExtra(EXTRA_CUSTOMER_NAME).orEmpty()
        customerCategory = intent.getStringExtra(EXTRA_CUSTOMER_CATEGORY).orEmpty()
        customerType = intent.getStringExtra(EXTRA_CUSTOMER_TYPE).orEmpty()
        pricePerLiter = intent.getDoubleExtra(EXTRA_PRICE_PER_LITER, 0.0)

        if (customerId <= 0 || customerName.isBlank() || customerCategory.isBlank()) {
            return false
        }

        entryType = if (customerCategory == AppConstants.CATEGORY_SUPPLIER) {
            AppConstants.ENTRY_COLLECTION
        } else {
            AppConstants.ENTRY_DISTRIBUTION
        }
        return true
    }

    private fun setupToolbar() {
        binding.toolbar.title = "$customerName Summary"
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupPartnerHeader() {
        binding.tvPartnerName.text = customerName
        binding.tvPartnerMeta.text = "${customerCategory.replaceFirstChar { it.uppercase() }} - $customerType"
        val rateLabel = if (entryType == AppConstants.ENTRY_COLLECTION) "Buying Rate" else "Selling Rate"
        binding.tvPartnerRate.text = "$rateLabel: ₹ %.2f / L".format(pricePerLiter)
    }

    private fun setupEntryList() {
        dayEntryAdapter = MilkEntryAdapter(
            canModify = false,
            userRole = AppConstants.ROLE_ADMIN,
            onEdit = { },
            onDelete = { }
        )
        binding.recyclerDayEntries.layoutManager = LinearLayoutManager(this)
        binding.recyclerDayEntries.adapter = dayEntryAdapter
    }

    private fun setupCalendar() {
        val calendar = Calendar.getInstance()
        binding.calendarView.date = calendar.timeInMillis
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            if (suppressCalendarCallback) return@setOnDateChangeListener
            selectedDate = TimeUtils.dateFromPicker(year, month, dayOfMonth)
            updateDateUi()
            observeForDate()
        }
    }

    private fun setupMonthNavigation() {
        binding.btnPickMonth.setOnClickListener {
            showMonthPicker()
        }
        binding.btnPrevMonth.setOnClickListener {
            moveSelectedDateByMonths(-1)
        }
        binding.btnNextMonth.setOnClickListener {
            moveSelectedDateByMonths(1)
        }
    }

    private fun moveSelectedDateByMonths(delta: Int) {
        val current = parseSelectedDateCalendar() ?: return
        val originalDay = current.get(Calendar.DAY_OF_MONTH)
        current.set(Calendar.DAY_OF_MONTH, 1)
        current.add(Calendar.MONTH, delta)
        val maxDay = current.getActualMaximum(Calendar.DAY_OF_MONTH)
        current.set(Calendar.DAY_OF_MONTH, originalDay.coerceAtMost(maxDay))

        commitSelectedDate(current)
    }

    private fun showMonthPicker() {
        val current = parseSelectedDateCalendar() ?: Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, _ ->
                val selected = Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, 1)
                }
                val day = current.get(Calendar.DAY_OF_MONTH)
                selected.set(Calendar.DAY_OF_MONTH, day.coerceAtMost(selected.getActualMaximum(Calendar.DAY_OF_MONTH)))
                commitSelectedDate(selected)
            },
            current.get(Calendar.YEAR),
            current.get(Calendar.MONTH),
            current.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun parseSelectedDateCalendar(): Calendar? {
        val parsed = dateFormatter.parse(selectedDate) ?: return null
        return Calendar.getInstance().apply { time = parsed }
    }

    private fun commitSelectedDate(calendar: Calendar) {
        selectedDate = dateFormatter.format(calendar.time)
        suppressCalendarCallback = true
        binding.calendarView.date = calendar.timeInMillis
        suppressCalendarCallback = false
        updateDateUi()
        observeForDate()
    }

    private fun updateDateUi() {
        binding.tvSelectedDate.text = "Selected Date: ${TimeUtils.toDisplayDate(selectedDate)}"
        binding.tvMonthlyTitle.text = "Monthly Summary (${TimeUtils.monthFromDate(selectedDate)})"
    }

    private fun observeForDate() {
        dayEntriesLiveData?.removeObservers(this)
        dayEntriesLiveData = viewModel.getEntriesForDate(
            date = selectedDate,
            customerId = customerId,
            entryType = entryType
        )
        dayEntriesLiveData?.observe(this) { rows ->
            dayEntryAdapter.submitList(rows)
            val liters = rows.sumOf { it.quantityLiters }
            val amount = rows.sumOf { it.amount }
            binding.tvDailyLiters.text = "%.2f L".format(liters)
            binding.tvDailyAmount.text = "₹ %.2f".format(amount)
            binding.tvNoEntries.visibility = if (rows.isEmpty()) android.view.View.VISIBLE else android.view.View.GONE
        }

        val month = TimeUtils.monthFromDate(selectedDate)
        monthSummaryLiveData?.removeObservers(this)
        monthSummaryLiveData = viewModel.getMonthlySummary(
            month = month,
            customerId = customerId,
            entryType = entryType
        )
        monthSummaryLiveData?.observe(this) { monthly ->
            binding.tvMonthlyLiters.text = "%.2f L".format(monthly.totalQuantity)
            binding.tvMonthlyAmount.text = "₹ %.2f".format(monthly.totalAmount)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_CUSTOMER_ID = "extra_customer_id"
        const val EXTRA_CUSTOMER_NAME = "extra_customer_name"
        const val EXTRA_CUSTOMER_CATEGORY = "extra_customer_category"
        const val EXTRA_CUSTOMER_TYPE = "extra_customer_type"
        const val EXTRA_PRICE_PER_LITER = "extra_price_per_liter"
    }
}
