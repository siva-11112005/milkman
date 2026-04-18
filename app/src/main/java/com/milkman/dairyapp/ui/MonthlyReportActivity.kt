package com.milkman.dairyapp.ui

import android.app.DatePickerDialog
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkman.dairyapp.data.model.MonthlyCustomerReport
import com.milkman.dairyapp.databinding.ActivityMonthlyReportBinding
import com.milkman.dairyapp.ui.adapters.MonthlyReportAdapter
import com.milkman.dairyapp.util.PdfExportUtil
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.util.TimeUtils
import com.milkman.dairyapp.viewmodel.ReportViewModel
import java.util.Calendar

class MonthlyReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMonthlyReportBinding
    private lateinit var viewModel: ReportViewModel
    private lateinit var sessionManager: SessionManager
    private lateinit var reportAdapter: MonthlyReportAdapter

    private var currentRows: List<MonthlyCustomerReport> = emptyList()
    private var showAmount: Boolean = true

    private val exportPdfLauncher = registerForActivityResult(
        ActivityResultContracts.CreateDocument("application/pdf")
    ) { uri: Uri? ->
        if (uri != null) {
            PdfExportUtil.exportMonthlyReport(
                context = this,
                uri = uri,
                month = binding.etReportMonth.text?.toString().orEmpty(),
                reportRows = currentRows,
                includeAmount = showAmount
            )
            Toast.makeText(this, "PDF exported", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonthlyReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if (!sessionManager.isAdmin()) {
            Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        showAmount = true
        viewModel = ViewModelProvider(this)[ReportViewModel::class.java]

        reportAdapter = MonthlyReportAdapter(showAmount)
        binding.rvMonthlyReport.layoutManager = LinearLayoutManager(this)
        binding.rvMonthlyReport.adapter = reportAdapter

        if (!showAmount) {
            binding.tvReportTotalAmount.visibility = View.GONE
        }

        binding.etReportMonth.setText(TimeUtils.currentMonth())
        binding.etReportMonth.setOnClickListener { showMonthPicker() }

        binding.btnExportPdf.setOnClickListener {
            val month = binding.etReportMonth.text?.toString().orEmpty()
            exportPdfLauncher.launch("monthly_report_$month.pdf")
        }

        viewModel.monthlyReport.observe(this) { rows ->
            currentRows = rows
            reportAdapter.submitList(rows)

            val totalQty = rows.sumOf { it.totalQuantity }
            val totalAmount = rows.sumOf { it.totalAmount }

            binding.tvReportTotalQty.text = "Total Quantity: %.2f L".format(totalQty)
            if (showAmount) {
                binding.tvReportTotalAmount.text = "Total Amount: %.2f".format(totalAmount)
            }
        }

        viewModel.setMonth(binding.etReportMonth.text?.toString().orEmpty())
    }

    private fun showMonthPicker() {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, _ ->
                val selectedMonth = String.format("%04d-%02d", year, month + 1)
                binding.etReportMonth.setText(selectedMonth)
                viewModel.setMonth(selectedMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            1
        ).show()
    }
}
