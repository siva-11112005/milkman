package com.milkman.dairyapp.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.milkman.dairyapp.R
import com.milkman.dairyapp.databinding.ActivityAddBuyerBinding
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.BuyerViewModel

class AddBuyerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBuyerBinding
    private lateinit var viewModel: BuyerViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBuyerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if (!sessionManager.canManageUsersAndCustomers()) {
            Toast.makeText(this, "Only admin or super user can create buyers", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[BuyerViewModel::class.java]

        val typeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.buyer_types,
            android.R.layout.simple_spinner_item
        )
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBuyerType.adapter = typeAdapter

        binding.btnSaveBuyer.setOnClickListener {
            val name = binding.etBuyerName.text?.toString().orEmpty()
            val phone = binding.etBuyerPhone.text?.toString().orEmpty()
            val address = binding.etBuyerAddress.text?.toString().orEmpty()
            val type = binding.spinnerBuyerType.selectedItem?.toString().orEmpty()
            val sellingPrice = binding.etSellingPrice.text?.toString().orEmpty().toDoubleOrNull()

            if (name.isBlank() || phone.isBlank() || address.isBlank()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (sellingPrice == null || sellingPrice <= 0) {
                Toast.makeText(this, "Enter valid selling price", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addBuyer(
                name = name,
                phone = phone,
                address = address,
                type = type,
                sellingPricePerLiter = sellingPrice,
                userId = sessionManager.userId().toInt()
            )
        }

        viewModel.actionState.observe(this) { result ->
            Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            if (result.success) finish()
        }
    }
}
