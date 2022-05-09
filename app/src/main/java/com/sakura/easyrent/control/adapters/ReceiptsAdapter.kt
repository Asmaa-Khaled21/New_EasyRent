package com.sakura.easyrent.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.LayoutHomeBinding
import com.sakura.easyrent.model.Contracts


class ReceiptsAdapter(private val context: Context, private val items: ArrayList<Contracts>) : RecyclerView.Adapter<ReceiptsAdapter.ReceiptsViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptsViewHolder{
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = LayoutHomeBinding.inflate(inflater, parent, false)
        // Returning:
        return ReceiptsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptsViewHolder, position: Int) {
        // Initializing:
        val item = items[position]
    }

    override fun getItemCount(): Int = items.size

    // Holder:
    inner class ReceiptsViewHolder(val binding: LayoutHomeBinding) : RecyclerView.ViewHolder(binding.root)
}