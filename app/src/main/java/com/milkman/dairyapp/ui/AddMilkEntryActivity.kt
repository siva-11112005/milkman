package com.milkman.dairyapp.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.milkman.dairyapp.R
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.databinding.ActivityAddMilkEntryBinding
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.util.TimeUtils
import com.milkman.dairyapp.viewmodel.MilkEntryViewModel
import java.util.Calendar

class AddMilkEntryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddMilkEntryBinding
    private lateinit var viewModel: MilkEntryViewModel
    private lateinit var sessionManager: SessionManager

    private var profiles: List<CustomerEntity> = emptyList()
    private var entryType: String = AppConstants.ENTRY_COLLECTION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMilkEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if (!sessionManager.isAdmin()) {
            Toast.makeText(this, "Only admin can create entries", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        entryType = intent.getStringExtra(EXTRA_ENTRY_TYPE) ?: AppConstants.ENTRY_COLLECTION
        viewModel = ViewModelProvider(this)[MilkEntryViewModel::class.java]

        setupToolbar()
        setupSessionSpinner()
        setupDateField()
        observeProfiles()
        updatePreviewAmount()

        binding.etQuantity.addTextChangedListener(SimpleTextWatcher { updatePreviewAmount() })
        binding.spinnerEntryCustomer.setOnItemSelectedListener(SimpleItemSelectedListener { updatePreviewAmount() })

        binding.btnSaveEntry.setOnClickListener {
            saveEntry()
        }

        viewModel.actionState.observe(this) { result ->
            Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            if (result.success) {
                finish()
            }
        }
    }

    private fun setupToolbar() {
        val isCollection = entryType == AppConstants.ENTRY_COLLECTION
        binding.toolbar.title = if (isCollection) "Collection Entry" else "Distribution Entry"
        binding.tvEntryPartyLabel.text = if (isCollection) "Supplier" else "Buyer"
        binding.tvAutoPriceLabel.text = if (isCollection) "Buying Price" else "Selling Price"
    }

    private fun setupSessionSpinner() {
        val sessionAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.milk_sessions,
            android.R.layout.simple_spinner_item
        )
        sessionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSession.adapter = sessionAdapter
        
        // Auto-suggest based on current time
        suggestSessionByTime()
    }

    private fun suggestSessionByTime() {
        val calendar = java.util.Calendar.getInstance()
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        
        // Before 12 PM (noon) = Morning
        // After 12 PM = Evening
        val suggestedSession = if (hour < 12) "MORNING" else "EVENING"
        
        @Suppress("UNCHECKED_CAST")
        val adapter = binding.spinnerSession.adapter as ArrayAdapter<String>
        val position = adapter.getPosition(suggestedSession)
        binding.spinnerSession.setSelection(position)
        
        // Show toast to inform user
        Toast.makeText(
            this,
            "Session suggested: $suggestedSession (You can change it if needed)",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupDateField() {
        binding.etEntryDate.setText(TimeUtils.currentDate())
        binding.etEntryDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    binding.etEntryDate.setText(TimeUtils.dateFromPicker(year, month, day))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun observeProfiles() {
        val source = if (entryType == AppConstants.ENTRY_COLLECTION) viewModel.suppliers else viewModel.buyers
        source.observe(this) { list ->
            profiles = list
            val labels = if (list.isEmpty()) {
                listOf("No profiles found")
            } else {
                list.map { "${it.name} (₹ %.2f/L)".format(it.pricePerLiter) }
            }
            val customerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, labels)
            customerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerEntryCustomer.adapter = customerAdapter
            updatePreviewAmount()
        }
    }

    private fun saveEntry() {
        if (profiles.isEmpty()) {
            val label = if (entryType == AppConstants.ENTRY_COLLECTION) "supplier" else "buyer"
            Toast.makeText(this, "Please add $label first", Toast.LENGTH_SHORT).show()
            return
        }

        val profile = profiles[binding.spinnerEntryCustomer.selectedItemPosition]
        val date = binding.etEntryDate.text?.toString().orEmpty()
        val session = binding.spinnerSession.selectedItem?.toString().orEmpty()
        val qty = binding.etQuantity.text?.toString().orEmpty().toDoubleOrNull()

        if (date.isBlank() || qty == null || qty <= 0) {
            Toast.makeText(this, "Enter valid date and quantity", Toast.LENGTH_SHORT).show()
            return
        }

        if (entryType == AppConstants.ENTRY_COLLECTION) {
            viewModel.addCollectionEntry(
                supplierId = profile.id,
                date = date,
                session = session,
                quantity = qty,
                userId = sessionManager.userId().toInt()
            )
        } else {
            viewModel.addDistributionEntry(
                buyerId = profile.id,
                date = date,
                session = session,
                quantity = qty,
                userId = sessionManager.userId().toInt()
            )
        }
    }

    private fun updatePreviewAmount() {
        val selected = profiles.getOrNull(binding.spinnerEntryCustomer.selectedItemPosition)
        if (selected == null) {
            binding.tvAutoPrice.text = "₹ 0.00 / L"
            binding.tvTotalAmount.text = "₹ 0.00"
            return
        }

        val quantity = binding.etQuantity.text?.toString().orEmpty().toDoubleOrNull() ?: 0.0
        val total = quantity * selected.pricePerLiter
        binding.tvAutoPrice.text = "₹ %.2f / L".format(selected.pricePerLiter)
        binding.tvTotalAmount.text = "₹ %.2f".format(total)
    }

    companion object {
        const val EXTRA_ENTRY_TYPE = "entry_type"
    }
}
