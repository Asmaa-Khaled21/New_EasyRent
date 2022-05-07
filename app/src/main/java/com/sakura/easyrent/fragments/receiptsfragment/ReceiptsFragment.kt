package com.sakura.easyrent.fragments.receiptsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.constraintlayout.helper.widget.Carousel

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
import com.sakura.easyrent.R
import com.sakura.easyrent.database.DataBase
import com.sakura.easyrent.databinding.FragmentReceiptsBinding


class ReceiptsFragment : Fragment(),ReceiptsNavigator {

    private lateinit var receiptsbinding:FragmentReceiptsBinding
    private val adapterList by lazy { ReceiptsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        receiptsbinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_receipts, container, false)
        val receiptsViewModel = ViewModelProvider(this).get(ReceiptsViewModel::class.java)
        receiptsbinding.receiptsViewModel = receiptsViewModel
        receiptsViewModel.navigator = this
        return receiptsbinding.root



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = ReceiptsDataBase.getReceiptsItem()
        adapterList.updateList(itemList)
        receiptsbinding.RecycleViewReceipts.apply {
            adapter = adapterList }
        }

    companion object {
        @JvmStatic
        fun newInstance() =
            ReceiptsFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}