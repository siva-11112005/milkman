package com.milkman.dairyapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.databinding.DialogEditSupplierBinding
import com.milkman.dairyapp.databinding.FragmentCustomersBinding
import com.milkman.dairyapp.service.DataSyncService
import com.milkman.dairyapp.ui.AddCustomerActivity
import com.milkman.dairyapp.ui.adapters.CustomerAdapter
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.CustomerViewModel
import kotlinx.coroutines.launch

class CustomersFragment : Fragment() {
    private var _binding: FragmentCustomersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CustomerViewModel
    private lateinit var sessionManager: SessionManager
    private lateinit var dataSyncService: DataSyncService

    private lateinit var adapter: CustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())
        dataSyncService = DataSyncService(requireContext())
        viewModel = ViewModelProvider(this)[CustomerViewModel::class.java]
        val canManageCustomers = sessionManager.canManageUsersAndCustomers()
        adapter = CustomerAdapter(
            priceLabel = "Buying Price",
            onEdit = if (canManageCustomers) ({ customer -> showEditDialog(customer) }) else null,
            onDelete = if (canManageCustomers) ({ customer -> confirmDelete(customer) }) else null
        )

        binding.recyclerViewCustomers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCustomers.adapter = adapter

        binding.fabAddCustomer.setOnClickListener {
            startActivity(Intent(requireContext(), AddCustomerActivity::class.java))
        }

        binding.etSearchCustomer.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchText(newText.orEmpty())
                return true
            }
        })

        viewModel.customers.observe(viewLifecycleOwner) { customers ->
            adapter.submitList(customers)
        }

        viewModel.actionState.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
            if (result.success) {
                refreshFromServer()
            }
        }

        // Pull latest server state immediately when this screen is opened.
        refreshFromServer()
    }

    override fun onResume() {
        super.onResume()
        // Also refresh when returning to this tab after add/edit actions.
        refreshFromServer()
    }

    private fun refreshFromServer() {
        lifecycleScope.launch {
            try {
                val result = dataSyncService.syncPendingOperations()
                if (result.fetchedCount > 0) {
                    Toast.makeText(
                        requireContext(),
                        "Updated from Atlas: ${result.fetchedCount}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (_: Exception) {
                // Keep UI responsive even if network sync fails.
            }
        }
    }

    private fun showEditDialog(item: CustomerEntity) {
        if (!sessionManager.canManageUsersAndCustomers()) return

        val dialogBinding = DialogEditSupplierBinding.inflate(LayoutInflater.from(requireContext()))
        dialogBinding.etSupplierName.setText(item.name)
        dialogBinding.etSupplierPhone.setText(item.phone)
        dialogBinding.etSupplierAddress.setText(item.address)
        dialogBinding.etSupplierPrice.setText(item.pricePerLiter.toString())

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Edit Supplier Profile")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val name = dialogBinding.etSupplierName.text?.toString().orEmpty()
                val phone = dialogBinding.etSupplierPhone.text?.toString().orEmpty()
                val address = dialogBinding.etSupplierAddress.text?.toString().orEmpty()
                val price = dialogBinding.etSupplierPrice.text?.toString().orEmpty().toDoubleOrNull()

                if (name.isBlank() || phone.isBlank() || address.isBlank() || price == null || price <= 0) {
                    Toast.makeText(requireContext(), "Enter valid values", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.updateSupplier(
                        supplierId = item.id,
                        name = name,
                        phone = phone,
                        address = address,
                        buyingPricePerLiter = price,
                        userId = sessionManager.userId().toInt()
                    )
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun confirmDelete(item: CustomerEntity) {
        if (!sessionManager.canManageUsersAndCustomers()) return

        val role = sessionManager.getRole()
        if (role != AppConstants.ROLE_ADMIN && role != AppConstants.ROLE_SUPER_USER) {
            return
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete Customer")
            .setMessage("Delete ${item.name}? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                val token = sessionManager.getToken().orEmpty()
                if (token.isBlank()) {
                    Toast.makeText(requireContext(), "Login required", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                viewModel.deleteSupplier(
                    item = item,
                    userId = sessionManager.userId().toIntOrNull() ?: 0,
                    authToken = token
                )
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
