package com.sakura.easyrent.recycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.R

class SettingsAdapter (var items:List<SettingsItem>): RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items.get(position)

        holder.oneText.text= item.oneText
        holder.twoText.text = item.twoText
        holder.threeText.text = item.threeText
        holder.fourText.text=item.fourText
        holder.fiveText.text = item.fiveText

        onItemClickListener?.let {

        }
       if (onItemClickListener!=null){
            holder.sixItem.setOnClickListener{
                 onItemClickListener?.onItemClick(position,item)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    var onItemClickListener :OnItemClickListener?=null

   interface OnItemClickListener{
        fun onItemClick(pos:Int ,item:SettingsItem)
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val oneImage: ImageView =itemView.findViewById(R.id.image_House_NUMBER)
        val oneText : TextView =itemView.findViewById(R.id.House_NUMBER)
        val twoImage: ImageView =itemView.findViewById(R.id.image_Day)
        val twoText : TextView =itemView.findViewById(R.id.Day)
        val threeImage: ImageView =itemView.findViewById(R.id.image_GBS)
        val threeText : TextView =itemView.findViewById(R.id.GBS)
        val fourImage: ImageView =itemView.findViewById(R.id.image_clock)
        val fourText : TextView =itemView.findViewById(R.id.clock)
        val fiveText : TextView =itemView.findViewById(R.id.money)
        val sixItem : Button = itemView.findViewById(R.id.Button_Bay)
    }
}