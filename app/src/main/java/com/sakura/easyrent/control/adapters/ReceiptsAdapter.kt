package com.sakura.easyrent.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.ReceiptsItemViewBinding
import com.sakura.easyrent.model.Contract


// Adapter(ReceiptsAdapter):
class ReceiptsAdapter(
    // Fields:
    private val context: Context,
    private val receipts: ArrayList<Contract>
) : RecyclerView.Adapter<ReceiptsAdapter.ReceiptsViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptsViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = ReceiptsItemViewBinding.inflate(inflater, parent, false)
        // Returning:
        return ReceiptsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptsViewHolder, position: Int) {
        // Initializing:
        val receipt = receipts[position]
    }

    override fun getItemCount(): Int = receipts.size

    // Holder:
    inner class ReceiptsViewHolder(val binding: ReceiptsItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}