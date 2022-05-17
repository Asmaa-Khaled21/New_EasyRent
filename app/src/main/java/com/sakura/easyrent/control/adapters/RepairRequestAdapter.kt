package com.sakura.easyrent.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.RepairRequestItemViewBinding
import com.sakura.easyrent.model.RepairRequest

class RepairRequestAdapter(
    // Fields:
    private val context: Context,
    private val repairRequest : ArrayList<RepairRequest>)
    : RecyclerView.Adapter<RepairRequestAdapter.RepairRequestViewHolder>() {


    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepairRequestViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding =RepairRequestItemViewBinding.inflate(inflater, parent, false)
        // Returning:
        return RepairRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepairRequestViewHolder, position: Int) {
        // Initializing:
        val repairRequest = repairRequest[position]
        // Applying:
        holder.binding.apply {
            // Setting:
            Details.text = repairRequest.details
            Date.text = repairRequest.Date.toString()
            Status.text =repairRequest.Status.toString()
            Unit.text = repairRequest.Unit.toString()
            Notes.text =repairRequest.Notes
        }
    }

    override fun getItemCount(): Int = repairRequest.size

    // Holder:
    inner class RepairRequestViewHolder(val binding: RepairRequestItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}