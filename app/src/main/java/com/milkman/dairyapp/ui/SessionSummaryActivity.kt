package com.milkman.dairyapp.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.milkman.dairyapp.data.model.DailySessionBreakdown
import com.milkman.dairyapp.databinding.ActivitySessionSummaryBinding
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.util.TimeUtils
import com.milkman.dairyapp.viewmodel.SessionSummaryViewModel
import java.util.Calendar

class SessionSummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySessionSummaryBinding
    private lateinit var viewModel: SessionSummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessionSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!SessionManager(this).isAdmin()) {
            Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[SessionSummaryViewModel::class.java]

        binding.etSummaryDate.setText(TimeUtils.currentDate())
        binding.etSummaryDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    val date = TimeUtils.dateFromPicker(year, month, day)
                    binding.etSummaryDate.setText(date)
                    viewModel.setDate(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        viewModel.breakdown.observe(this) { rows ->
            updateRows(rows)
        }

        viewModel.setDate(binding.etSummaryDate.text?.toString().orEmpty())
    }

    private fun updateRows(rows: List<DailySessionBreakdown>) {
        val morning = rows.firstOrNull { it.session == "MORNING" }
        val evening = rows.firstOrNull { it.session == "EVENING" }

        val mQty = morning?.totalQuantity ?: 0.0
        val mAmount = morning?.totalAmount ?: 0.0
        val mCount = morning?.entryCount ?: 0

        val eQty = evening?.totalQuantity ?: 0.0
        val eAmount = evening?.totalAmount ?: 0.0
        val eCount = evening?.entryCount ?: 0

        binding.tvMorningSummary.text =
            "Morning: %.2f L | %.2f | %d entries".format(mQty, mAmount, mCount)
        binding.tvEveningSummary.text =
            "Evening: %.2f L | %.2f | %d entries".format(eQty, eAmount, eCount)

        binding.tvTotalSummary.text =
            "Total: %.2f L | %.2f | %d entries".format(mQty + eQty, mAmount + eAmount, mCount + eCount)
    }
}
