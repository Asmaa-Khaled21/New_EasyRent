package com.sakura.easyrent.control.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.ContractItemViewBinding
import com.sakura.easyrent.model.Contract
import com.sakura.easyrent.ui.activitys.pay.PayActivity

// Adapter(Contracts):
class ContractsAdapter(
    // Fields:
    private val context: Context,
    private val contracts: ArrayList<Contract>
) : RecyclerView.Adapter<ContractsAdapter.ContractsViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractsViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = ContractItemViewBinding.inflate(inflater, parent, false)
        // Returning:
        return ContractsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContractsViewHolder, position: Int) {
        // Initializing:
        val contract = contracts[position]
        // Applying:
        holder.binding.apply {
            // Setting:
            contractHolderName.text = contract.contractHolderName
            dateFrom.text = contract.dateFrom
            dateTo.text = contract.dateTo
            rentCost.text = contract.rentCost.toString()
            rentFrequency.text = contract.rentFrequency
            unit.text = contract.unit.toString()
            // Developing:
            payButton.setOnClickListener { context.startActivity(Intent(context, PayActivity::class.java)) }
        }
    }

    override fun getItemCount(): Int = contracts.size

    // Holder:
    inner class ContractsViewHolder(val binding: ContractItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}