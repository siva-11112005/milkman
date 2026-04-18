package com.milkman.dairyapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.milkman.dairyapp.databinding.FragmentEntriesBinding
import com.milkman.dairyapp.ui.AddMilkEntryActivity
import com.milkman.dairyapp.ui.adapters.MilkEntryAdapter
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager
import com.milkman.dairyapp.viewmodel.MilkEntryViewModel

class EntriesFragment : Fragment() {
    private var _binding: FragmentEntriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MilkEntryViewModel
    private lateinit var adapter: MilkEntryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionManager = SessionManager(requireContext())
        viewModel = ViewModelProvider(this)[MilkEntryViewModel::class.java]
        adapter = MilkEntryAdapter(
            canModify = sessionManager.isAdmin(),
            onEdit = { /* legacy screen: no inline edit */ },
            onDelete = { /* legacy screen: no inline delete */ }
        )

        binding.recyclerViewEntries.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewEntries.adapter = adapter

        binding.fabAddEntry.setOnClickListener {
            startActivity(
                Intent(requireContext(), AddMilkEntryActivity::class.java).apply {
                    putExtra(AddMilkEntryActivity.EXTRA_ENTRY_TYPE, AppConstants.ENTRY_COLLECTION)
                }
            )
        }

        viewModel.setFilters()
        viewModel.entries.observe(viewLifecycleOwner) { entries ->
            adapter.submitList(entries)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
