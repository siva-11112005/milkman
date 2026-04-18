package com.milkman.dairyapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.milkman.dairyapp.databinding.FragmentReportsBinding
import com.milkman.dairyapp.ui.MilkHistoryActivity
import com.milkman.dairyapp.ui.MonthlyReportActivity
import com.milkman.dairyapp.ui.SessionSummaryActivity
import com.milkman.dairyapp.util.SessionManager

class ReportsFragment : Fragment() {
    private var _binding: FragmentReportsBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager = SessionManager(requireContext())

        if (!sessionManager.isAdmin()) {
            binding.cardMonthlyReport.visibility = View.GONE
            binding.cardSessionSummary.visibility = View.GONE
        }

        binding.btnViewMonthlyReport.setOnClickListener {
            startActivity(Intent(requireContext(), MonthlyReportActivity::class.java))
        }

        binding.btnViewSessionSummary.setOnClickListener {
            startActivity(Intent(requireContext(), SessionSummaryActivity::class.java))
        }

        binding.btnViewMilkHistory.setOnClickListener {
            startActivity(Intent(requireContext(), MilkHistoryActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
