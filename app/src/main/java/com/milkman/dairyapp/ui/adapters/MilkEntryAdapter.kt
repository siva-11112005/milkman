package com.milkman.dairyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.milkman.dairyapp.R
import com.milkman.dairyapp.data.model.MilkEntryWithCustomer
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.TimeUtils

class MilkEntryAdapter(
    private val canModify: Boolean = true,
    private val userRole: String = AppConstants.ROLE_ADMIN,
    private val onEdit: (MilkEntryWithCustomer) -> Unit,
    private val onDelete: (MilkEntryWithCustomer) -> Unit
) : RecyclerView.Adapter<MilkEntryAdapter.MilkEntryViewHolder>() {

    private val items = mutableListOf<MilkEntryWithCustomer>()

    fun submitList(newItems: List<MilkEntryWithCustomer>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilkEntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_milk_entry, parent, false)
        return MilkEntryViewHolder(view, canModify, userRole, onEdit, onDelete)
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MilkEntryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MilkEntryViewHolder(
        itemView: View,
        private val canModify: Boolean,
        private val userRole: String,
        private val onEdit: (MilkEntryWithCustomer) -> Unit,
        private val onDelete: (MilkEntryWithCustomer) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvLine1: TextView = itemView.findViewById(R.id.tvEntryLine1)
        private val tvLine2: TextView = itemView.findViewById(R.id.tvEntryLine2)
        private val tvAmount: TextView = itemView.findViewById(R.id.tvEntryAmount)
        private val tvLockStatus: TextView = itemView.findViewById(R.id.tvEntryLockStatus)
        private val btnEdit: MaterialButton = itemView.findViewById(R.id.btnEditEntry)
        private val btnDelete: MaterialButton = itemView.findViewById(R.id.btnDeleteEntry)

        fun bind(item: MilkEntryWithCustomer) {
            val locked = TimeUtils.isLocked(item.createdAt, userRole)
            val entryLabel = if (item.entryType == AppConstants.ENTRY_COLLECTION) "Collection" else "Distribution"

            tvLine1.text = "$entryLabel - ${item.customerName} - ${item.entryDate} ${item.session}"
            tvLine2.text = "Qty: %.2f L, Rate: ₹ %.2f / L".format(item.quantityLiters, item.pricePerLiter)
            tvAmount.text = "Amount: ₹ %.2f".format(item.amount)
            tvLockStatus.text = if (locked) {
                "Locked (1h edit window)"
            } else {
                if (userRole == AppConstants.ROLE_SUPER_USER) "Editable (Super User)" else "Editable"
            }
            val startDrawable = if (locked) R.drawable.ic_lock else 0
            tvLockStatus.setCompoundDrawablesWithIntrinsicBounds(startDrawable, 0, 0, 0)
            tvLockStatus.compoundDrawablePadding = 8

            if (!canModify) {
                btnEdit.visibility = View.GONE
                btnDelete.visibility = View.GONE
            } else {
                btnEdit.visibility = View.VISIBLE
                btnDelete.visibility = View.VISIBLE
                btnEdit.isEnabled = !locked
                btnDelete.isEnabled = !locked

                btnEdit.setOnClickListener {
                    if (!locked) onEdit(item)
                }
                btnDelete.setOnClickListener {
                    if (!locked) onDelete(item)
                }
            }
        }
    }
}
