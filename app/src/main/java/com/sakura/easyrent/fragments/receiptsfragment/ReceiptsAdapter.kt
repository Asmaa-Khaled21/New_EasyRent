package com.sakura.easyrent.fragments.receiptsfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sakura.easyrent.databinding.ReceiptsItemBinding

class ReceiptsAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var receipts = mutableListOf<ReceiptsItem>()
    fun setMovieList(movies: List<ReceiptsItem>) {
        this.receipts = movies.toMutableList()
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReceiptsItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val receipt = receipts.get(position)
        holder.bind(receipt)
    }
    override fun getItemCount(): Int {
        return receipts.size
    }
}
class MainViewHolder(val binding: ReceiptsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(receiptsItem: ReceiptsItem) {
        binding.item=receiptsItem
        binding.invalidateAll() //refresh item

    }  }