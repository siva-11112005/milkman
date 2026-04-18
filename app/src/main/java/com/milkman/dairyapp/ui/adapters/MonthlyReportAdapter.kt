package com.milkman.dairyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkman.dairyapp.R
import com.milkman.dairyapp.data.model.MonthlyCustomerReport

class MonthlyReportAdapter(
    private val showAmount: Boolean
) : RecyclerView.Adapter<MonthlyReportAdapter.ReportViewHolder>() {

    private val items = mutableListOf<MonthlyCustomerReport>()

    fun submitList(newItems: List<MonthlyCustomerReport>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_monthly_report, parent, false)
        return ReportViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        holder.bind(items[position], showAmount)
    }

    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.findViewById(R.id.tvReportCustomerName)
        private val tvType: TextView = itemView.findViewById(R.id.tvReportCustomerType)
        private val tvQty: TextView = itemView.findViewById(R.id.tvReportQty)
        private val tvAmount: TextView = itemView.findViewById(R.id.tvReportAmount)

        fun bind(item: MonthlyCustomerReport, showAmount: Boolean) {
            tvName.text = item.customerName
            tvType.text = "Type: ${item.customerType}"
            tvQty.text = "Total Quantity: %.2f L".format(item.totalQuantity)
            if (showAmount) {
                tvAmount.visibility = View.VISIBLE
                tvAmount.text = "Total Amount: %.2f".format(item.totalAmount)
            } else {
                tvAmount.visibility = View.GONE
            }
        }
    }
}
