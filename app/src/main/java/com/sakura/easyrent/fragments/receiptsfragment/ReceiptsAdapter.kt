package com.sakura.easyrent.fragments.receiptsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.LayoutReceiptsBinding
import com.sakura.easyrent.fragments.receiptsfragment.ReceiptsDataBase.TYPE_ITEM
import java.lang.IllegalArgumentException

class ReceiptsAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    class ItemViewHolder(val itemBinding:LayoutReceiptsBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun  bind(item: ReceiptsItem.Item){
        itemBinding.item=item
        }
    }

    private val itemList = arrayListOf<Any>()
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_ITEM ->ItemViewHolder(LayoutReceiptsBinding.inflate(LayoutInflater.from(parent.context)
                ,parent,false))
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder ->holder.bind(itemList[position]as ReceiptsItem.Item)
        }
    }

    override fun getItemCount(): Int =itemList.size

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]){
            is ReceiptsItem.Item -> TYPE_ITEM
            else -> throw IllegalArgumentException("Invalid Item")
        }
    }

    fun updateList(updatedList:List<Any>){
        itemList.clear()
        itemList.addAll(updatedList)
        notifyDataSetChanged()
    }
}