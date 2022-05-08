package com.sakura.easyrent.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.LayoutHomeBinding
import com.sakura.easyrent.model.HomeItem

class HomeAdapter(private val context: Context, private val items: ArrayList<HomeItem>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = LayoutHomeBinding.inflate(inflater, parent, false)
        // Returning:
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        // Initializing:
        val item = items[position]
    }

    override fun getItemCount(): Int = items.size

    // Holder:
    inner class HomeViewHolder(val binding: LayoutHomeBinding) : RecyclerView.ViewHolder(binding.root)
}