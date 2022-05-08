package com.sakura.easyrent.control.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.NotificationItemBinding
import com.sakura.easyrent.model.NotificationItem

class NotificationAdapter(private val context: Context, private val notifications: ArrayList<NotificationItem>) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    // Adapter:
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        // Initializing:
        val inflater = LayoutInflater.from(context)
        val binding = NotificationItemBinding.inflate(inflater, parent, false)
        // Returning:
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        // Initializing:
        val notification = notifications[position]
    }

    override fun getItemCount(): Int = notifications.size

    // Holder:
    inner class NotificationViewHolder(val binding: NotificationItemBinding) : RecyclerView.ViewHolder(binding.root)
}