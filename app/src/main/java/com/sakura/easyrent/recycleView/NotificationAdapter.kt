package com.sakura.easyrent.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.sakura.easyrent.R

class NotificationAdapter (var items:List<NotificationsItem>?=null) :RecyclerView.Adapter<NotificationAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items?.get(position)
        if (item != null) {
            item?.Image?.let { holder.ImageNotification.setImageResource(it) }
        }
        holder.TextNotificationTop.setText(item?.TopText)
        holder.TextNotificationBottom.setText(item?.BottomText)

    }

    override fun getItemCount(): Int =items?.size?:0

    /*fun changeData(newItems:List<Notification>){
        items = newItems
        notifyDataSetChanged()
    }*/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val ImageNotification :ImageView = itemView.findViewById(R.id.Image_Notification)
        val TextNotificationTop  :TextView = itemView.findViewById(R.id.Notification_Text_top)
        val TextNotificationBottom  :TextView = itemView.findViewById(R.id.Notification_Text_Bottom)
    }
}

