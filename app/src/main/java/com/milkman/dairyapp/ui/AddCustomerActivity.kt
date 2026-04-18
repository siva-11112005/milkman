package com.milkman.dairyapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.milkman.dairyapp.databinding.ActivityAddCustomerBinding
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.CustomerViewModel

class AddCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCustomerBinding
    private lateinit var viewModel: CustomerViewModel
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCustomerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if (!sessionManager.canManageUsersAndCustomers()) {
            Toast.makeText(this, "Only admin or super user can create supplier accounts", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel = ViewModelProvider(this)[CustomerViewModel::class.java]

        binding.btnSaveCustomer.setOnClickListener {
            val name = binding.etCustomerName.text?.toString().orEmpty()
            val phone = binding.etPhone.text?.toString().orEmpty()
            val address = binding.etAddress.text?.toString().orEmpty()
            val buyingPrice = binding.etBuyingPrice.text?.toString().orEmpty().toDoubleOrNull()
            val username = binding.etUsername.text?.toString().orEmpty()
            val password = binding.etPassword.text?.toString().orEmpty()

            if (name.isBlank() || phone.isBlank() || address.isBlank() || username.isBlank() || password.length < 6) {
                Toast.makeText(this, "Fill all fields and use at least 6-character password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (buyingPrice == null || buyingPrice <= 0) {
                Toast.makeText(this, "Enter a valid buying price per liter", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.addSupplier(
                name = name,
                phone = phone,
                address = address,
                buyingPricePerLiter = buyingPrice,
                username = username,
                password = password,
                userId = sessionManager.userId().toInt()
            )
        }

        viewModel.actionState.observe(this) { result ->
            Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
            if (result.success) {
                finish()
            }
        }
    }
}
