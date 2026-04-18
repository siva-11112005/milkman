package com.milkman.dairyapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.milkman.dairyapp.R
import com.milkman.dairyapp.ui.AddMilkEntryActivity
import com.milkman.dairyapp.ui.MilkHistoryActivity
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager

class EntryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sessionManager = SessionManager(requireContext())
        if (!sessionManager.isAdmin()) {
            Toast.makeText(requireContext(), "Only admin can access entries", Toast.LENGTH_SHORT).show()
            return
        }

        view.findViewById<Button>(R.id.btn_add_milk_collection).setOnClickListener {
            startActivity(
                Intent(requireContext(), AddMilkEntryActivity::class.java).apply {
                    putExtra(AddMilkEntryActivity.EXTRA_ENTRY_TYPE, AppConstants.ENTRY_COLLECTION)
                }
            )
        }

        view.findViewById<Button>(R.id.btn_add_milk_distribution).setOnClickListener {
            startActivity(
                Intent(requireContext(), AddMilkEntryActivity::class.java).apply {
                    putExtra(AddMilkEntryActivity.EXTRA_ENTRY_TYPE, AppConstants.ENTRY_DISTRIBUTION)
                }
            )
        }

        view.findViewById<Button>(R.id.btn_open_entry_history).setOnClickListener {
            startActivity(Intent(requireContext(), MilkHistoryActivity::class.java))
        }
    }
}
