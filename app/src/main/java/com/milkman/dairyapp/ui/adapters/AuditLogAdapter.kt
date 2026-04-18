package com.milkman.dairyapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.milkman.dairyapp.R
import com.milkman.dairyapp.data.entity.AuditLogEntity
import com.milkman.dairyapp.util.TimeUtils

class AuditLogAdapter : RecyclerView.Adapter<AuditLogAdapter.AuditLogViewHolder>() {
    private val items = mutableListOf<AuditLogEntity>()

    fun submitList(newItems: List<AuditLogEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuditLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_audit_log, parent, false)
        return AuditLogViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AuditLogViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class AuditLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvAction: TextView = itemView.findViewById(R.id.tvAuditAction)
        private val tvDetails: TextView = itemView.findViewById(R.id.tvAuditDetails)
        private val tvMeta: TextView = itemView.findViewById(R.id.tvAuditMeta)

        fun bind(item: AuditLogEntity) {
            tvAction.text = "${item.action} on ${item.tableName} (#${item.recordId})"
            tvDetails.text = item.details
            tvMeta.text = "User ${item.userId} | ${TimeUtils.formatDateTime(item.createdAt)}"
        }
    }
}
