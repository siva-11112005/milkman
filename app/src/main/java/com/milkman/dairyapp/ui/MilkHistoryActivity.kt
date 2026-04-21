package com.milkman.dairyapp.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.milkman.dairyapp.data.AppDatabase
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.databinding.ActivityMilkHistoryBinding
import com.milkman.dairyapp.databinding.DialogEditMilkEntryBinding
import com.milkman.dairyapp.ui.adapters.MilkEntryAdapter
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.util.TimeUtils
import com.milkman.dairyapp.viewmodel.MilkEntryViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

class MilkHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMilkHistoryBinding
    private lateinit var viewModel: MilkEntryViewModel
    private lateinit var sessionManager: SessionManager

    private lateinit var adapter: MilkEntryAdapter

    private val customerOptions = mutableListOf<Pair<Int?, String>>()
    private var fixedCustomerId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMilkHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        viewModel = ViewModelProvider(this)[MilkEntryViewModel::class.java]
        adapter = MilkEntryAdapter(
            canModify = sessionManager.hasAdminAccess(),
            userRole = sessionManager.effectiveRole(),
            onEdit = { showEditDialog(it) },
            onDelete = { confirmDelete(it) }
        )

        binding.rvMilkHistory.layoutManager = LinearLayoutManager(this)
        binding.rvMilkHistory.adapter = adapter

        setupFilterInputs()
        observeData()

        binding.btnApplyFilters.setOnClickListener { applyFilters() }

        if (sessionManager.hasAdminAccess()) {
            binding.btnOpenAddEntry.visibility = View.VISIBLE
            binding.btnOpenAddEntry.setOnClickListener {
                startActivity(
                    android.content.Intent(this, AddMilkEntryActivity::class.java).apply {
                        putExtra(AddMilkEntryActivity.EXTRA_ENTRY_TYPE, AppConstants.ENTRY_COLLECTION)
                    }
                )
            }
            applyFilters()
        } else {
            binding.btnOpenAddEntry.visibility = View.GONE
            resolveCustomerAndApplyFilter()
        }
    }

    private fun resolveCustomerAndApplyFilter() {
        lifecycleScope.launch {
            val user = AppDatabase.getInstance(this@MilkHistoryActivity).userDao().findById(sessionManager.userId().toInt())
            fixedCustomerId = user?.linkedCustomerId
            if (fixedCustomerId == null) {
                Toast.makeText(this@MilkHistoryActivity, "Supplier profile not linked", Toast.LENGTH_SHORT).show()
                finish()
                return@launch
            }
            applyFilters()
        }
    }

    private fun setupFilterInputs() {
        binding.etFilterDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    binding.etFilterDate.setText(TimeUtils.dateFromPicker(year, month, day))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.etFilterDate.setOnLongClickListener {
            binding.etFilterDate.setText("")
            true
        }

        if (sessionManager.hasAdminAccess()) {
            val entryOptions = listOf("All Entries", "Collection", "Distribution")
            val entryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, entryOptions)
            entryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFilterType.adapter = entryAdapter
        } else {
            binding.spinnerFilterType.visibility = View.GONE
            binding.spinnerFilterCustomer.visibility = View.GONE
            binding.btnOpenAddEntry.visibility = View.GONE
        }
    }

    private fun observeData() {
        viewModel.customers.observe(this) { customers ->
            if (!sessionManager.hasAdminAccess()) return@observe

            customerOptions.clear()
            customerOptions.add(null to "All Profiles")
            customers.forEach { customer ->
                customerOptions.add(customer.id to "${customer.name} (${customer.type})")
            }
            val customerLabels = customerOptions.map { it.second }
            val customerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, customerLabels)
            customerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerFilterCustomer.adapter = customerAdapter
        }

        viewModel.entries.observe(this) { rows ->
            adapter.submitList(rows)
        }

        viewModel.actionState.observe(this) { result ->
            Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun applyFilters() {
        val date = binding.etFilterDate.text?.toString().orEmpty().ifBlank { null }

        if (sessionManager.hasAdminAccess()) {
            val customerId = customerOptions.getOrNull(binding.spinnerFilterCustomer.selectedItemPosition)?.first
            val entrySelection = binding.spinnerFilterType.selectedItem?.toString().orEmpty()
            val entryType = when (entrySelection) {
                "Collection" -> AppConstants.ENTRY_COLLECTION
                "Distribution" -> AppConstants.ENTRY_DISTRIBUTION
                else -> null
            }
            viewModel.setFilters(date = date, customerId = customerId, entryType = entryType)
        } else {
            viewModel.setFilters(
                date = date,
                customerId = fixedCustomerId,
                customerCategory = AppConstants.CATEGORY_SUPPLIER,
                entryType = AppConstants.ENTRY_COLLECTION
            )
        }
    }

    private fun showEditDialog(item: MilkEntryWithCustomer) {
        if (TimeUtils.isLocked(item.createdAt, sessionManager.effectiveRole())) {
            Toast.makeText(this, "Record is locked. Super users can edit anytime.", Toast.LENGTH_SHORT).show()
            return
        }

        val dialogBinding = DialogEditMilkEntryBinding.inflate(LayoutInflater.from(this))
        dialogBinding.etEditQuantity.setText(item.quantityLiters.toString())
        dialogBinding.tvEntryPrice.text = "Price: ₹ %.2f / L".format(item.pricePerLiter)

        MaterialAlertDialogBuilder(this)
            .setTitle("Edit Milk Entry")
            .setView(dialogBinding.root)
            .setPositiveButton("Update") { _, _ ->
                val quantity = dialogBinding.etEditQuantity.text?.toString()?.toDoubleOrNull()
                if (quantity == null || quantity <= 0) {
                    Toast.makeText(this, "Enter valid quantity", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.updateEntry(item.id, quantity, sessionManager.userId().toInt())
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun confirmDelete(item: MilkEntryWithCustomer) {
        if (TimeUtils.isLocked(item.createdAt, sessionManager.effectiveRole())) {
            Toast.makeText(this, "Record is locked. Super users can delete anytime.", Toast.LENGTH_SHORT).show()
            return
        }

        MaterialAlertDialogBuilder(this)
            .setTitle("Delete Entry")
            .setMessage("Delete this milk entry?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteEntry(item.id, sessionManager.userId().toInt())
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
