package com.milkman.dairyapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.milkman.dairyapp.R
import com.milkman.dairyapp.data.entity.CustomerEntity
import com.milkman.dairyapp.databinding.DialogEditBuyerBinding
import com.milkman.dairyapp.databinding.FragmentBuyersBinding
import com.milkman.dairyapp.ui.AddBuyerActivity
import com.milkman.dairyapp.ui.PartnerSummaryActivity
import com.milkman.dairyapp.ui.adapters.CustomerAdapter
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.BuyerViewModel

class BuyersFragment : Fragment() {
    private var _binding: FragmentBuyersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: BuyerViewModel
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: CustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[BuyerViewModel::class.java]
        val canManageCustomers = sessionManager.canManageUsersAndCustomers()
        adapter = CustomerAdapter(
            priceLabel = "Selling Price",
            onViewSummary = { customer -> openPartnerSummary(customer) },
            onEdit = if (canManageCustomers) ({ customer -> showEditDialog(customer) }) else null,
            onDelete = if (canManageCustomers) ({ customer -> confirmDelete(customer) }) else null
        )

        binding.recyclerViewBuyers.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewBuyers.adapter = adapter

        binding.fabAddBuyer.setOnClickListener {
            startActivity(Intent(requireContext(), AddBuyerActivity::class.java))
        }

        binding.etSearchBuyer.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setSearchText(newText.orEmpty())
                return true
            }
        })

        viewModel.buyers.observe(viewLifecycleOwner) { buyers ->
            adapter.submitList(buyers)
        }

        viewModel.actionState.observe(viewLifecycleOwner) { result ->
            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showEditDialog(item: CustomerEntity) {
        if (!sessionManager.canManageUsersAndCustomers()) return

        val dialogBinding = DialogEditBuyerBinding.inflate(LayoutInflater.from(requireContext()))
        val typeAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.buyer_types,
            android.R.layout.simple_spinner_item
        )
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialogBinding.spinnerBuyerType.adapter = typeAdapter

        dialogBinding.etBuyerName.setText(item.name)
        dialogBinding.etBuyerPhone.setText(item.phone)
        dialogBinding.etBuyerAddress.setText(item.address)
        dialogBinding.etBuyerPrice.setText(item.pricePerLiter.toString())

        val typePos = resources.getStringArray(R.array.buyer_types).indexOf(item.type).coerceAtLeast(0)
        dialogBinding.spinnerBuyerType.setSelection(typePos)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Edit Buyer Profile")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val name = dialogBinding.etBuyerName.text?.toString().orEmpty()
                val phone = dialogBinding.etBuyerPhone.text?.toString().orEmpty()
                val address = dialogBinding.etBuyerAddress.text?.toString().orEmpty()
                val type = dialogBinding.spinnerBuyerType.selectedItem?.toString().orEmpty()
                val price = dialogBinding.etBuyerPrice.text?.toString().orEmpty().toDoubleOrNull()

                if (name.isBlank() || phone.isBlank() || address.isBlank() || price == null || price <= 0) {
                    Toast.makeText(requireContext(), "Enter valid values", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.updateBuyer(
                        buyerId = item.id,
                        name = name,
                        phone = phone,
                        address = address,
                        type = type,
                        sellingPricePerLiter = price,
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
            .setTitle("Delete Buyer")
            .setMessage("Delete ${item.name}? This action cannot be undone.")
            .setPositiveButton("Delete") { _, _ ->
                val token = sessionManager.getToken().orEmpty()
                if (token.isBlank()) {
                    Toast.makeText(requireContext(), "Login required", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                viewModel.deleteBuyer(
                    item = item,
                    userId = sessionManager.userId().toIntOrNull() ?: 0,
                    authToken = token
                )
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun openPartnerSummary(item: CustomerEntity) {
        startActivity(
            Intent(requireContext(), PartnerSummaryActivity::class.java).apply {
                putExtra(PartnerSummaryActivity.EXTRA_CUSTOMER_ID, item.id)
                putExtra(PartnerSummaryActivity.EXTRA_CUSTOMER_NAME, item.name)
                putExtra(PartnerSummaryActivity.EXTRA_CUSTOMER_TYPE, item.type)
                putExtra(PartnerSummaryActivity.EXTRA_CUSTOMER_CATEGORY, item.category)
                putExtra(PartnerSummaryActivity.EXTRA_PRICE_PER_LITER, item.pricePerLiter)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
