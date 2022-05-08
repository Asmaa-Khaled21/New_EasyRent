package com.sakura.easyrent.recycleView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.R
import com.sakura.easyrent.databinding.LayoutHomeBinding

class HomeAdapter (var homeList:List<HomeItem>):
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val homeItemBinding:LayoutHomeBinding=
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.layout_home,parent,false)
        return ViewHolder(homeItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeItem=homeList?.get(position)
        holder.bind(homeItem!!)
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    var onItemClickListener :OnItemClickListener?=null

   interface OnItemClickListener{
        fun onItemClick(pos:Int ,item:HomeItem)
    }

    class ViewHolder(val itembinding:LayoutHomeBinding): RecyclerView.ViewHolder(itembinding.root){
        fun bind(homeItem: HomeItem) {
            itembinding.homeItem=homeItem
            itembinding.invalidateAll() //refresh item

    }
  }
    }