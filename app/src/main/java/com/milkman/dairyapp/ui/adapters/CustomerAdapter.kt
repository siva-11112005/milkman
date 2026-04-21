package com.milkman.dairyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.milkman.dairyapp.R
import com.milkman.dairyapp.data.entity.CustomerEntity

class CustomerAdapter(
    private val priceLabel: String,
    private val onViewSummary: ((CustomerEntity) -> Unit)? = null,
    private val onEdit: ((CustomerEntity) -> Unit)? = null,
    private val onDelete: ((CustomerEntity) -> Unit)? = null
) : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {
    private val items = mutableListOf<CustomerEntity>()

    fun submitList(newItems: List<CustomerEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
        return CustomerViewHolder(view, priceLabel, onViewSummary, onEdit, onDelete)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class CustomerViewHolder(
        itemView: View,
        private val priceLabel: String,
        private val onViewSummary: ((CustomerEntity) -> Unit)?,
        private val onEdit: ((CustomerEntity) -> Unit)?,
        private val onDelete: ((CustomerEntity) -> Unit)?
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvCustomerName)
        private val tvPhone: TextView = itemView.findViewById(R.id.tvCustomerPhone)
        private val tvType: TextView = itemView.findViewById(R.id.tvCustomerType)
        private val tvAddress: TextView = itemView.findViewById(R.id.tvCustomerAddress)
        private val tvPrice: TextView = itemView.findViewById(R.id.tvCustomerPrice)
        private val btnSummary: MaterialButton = itemView.findViewById(R.id.btnViewSummary)
        private val btnEdit: MaterialButton = itemView.findViewById(R.id.btnEditCustomer)
        private val btnDelete: MaterialButton = itemView.findViewById(R.id.btnDeleteCustomer)

        fun bind(item: CustomerEntity) {
            tvName.text = item.name
            tvPhone.text = "Phone: ${item.phone}"
            tvType.text = "Type: ${item.type}"
            tvAddress.text = "Address: ${item.address}"
            tvPrice.text = "$priceLabel: ₹ %.2f / L".format(item.pricePerLiter)

            if (onViewSummary == null) {
                btnSummary.visibility = View.GONE
                itemView.setOnClickListener(null)
            } else {
                btnSummary.visibility = View.VISIBLE
                btnSummary.setOnClickListener { onViewSummary.invoke(item) }
                itemView.setOnClickListener { onViewSummary.invoke(item) }
            }

            if (onEdit == null) {
                btnEdit.visibility = View.GONE
            } else {
                btnEdit.visibility = View.VISIBLE
                btnEdit.setOnClickListener { onEdit.invoke(item) }
            }

            if (onDelete == null) {
                btnDelete.visibility = View.GONE
            } else {
                btnDelete.visibility = View.VISIBLE
                btnDelete.setOnClickListener { onDelete.invoke(item) }
            }
        }
    }
}
