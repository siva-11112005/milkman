package com.milkman.dairyapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkman.dairyapp.databinding.ActivityAuditLogBinding
import com.milkman.dairyapp.ui.adapters.AuditLogAdapter
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.AuditLogViewModel

class AuditLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuditLogBinding
    private lateinit var viewModel: AuditLogViewModel
    private lateinit var sessionManager: SessionManager
    private val adapter = AuditLogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuditLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if (!sessionManager.isAdmin()) {
            Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[AuditLogViewModel::class.java]

        binding.rvAuditLogs.layoutManager = LinearLayoutManager(this)
        binding.rvAuditLogs.adapter = adapter

        viewModel.recentLogs.observe(this) { logs ->
            adapter.submitList(logs)
        }
    }
}
