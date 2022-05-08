package com.sakura.easyrent.fragments.receiptsfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sakura.easyrent.R
import com.sakura.easyrent.databinding.FragmentReceiptsBinding
import retrofit2.Call


class ReceiptsFragment : Fragment(),RetrofitService,Navigator {

    private val TAG = "MainActivity"

    lateinit var viewModel: ReceiptsViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = ReceiptsAdapter()
    private lateinit var receiptsbinding:FragmentReceiptsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        receiptsbinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_receipts, container, false)
        val receiptsViewModel = ViewModelProvider(this).get(ReceiptsItem::class.java)
        receiptsbinding.receiptsViewModel = receiptsViewModel
        receiptsViewModel.navigator = this
        return receiptsbinding.root
    }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ReceiptsViewModelFactory(ReceiptsRepository(retrofitService)))
            .get(ReceiptsViewModel::class.java)
        receiptsbinding.RecycleViewReceipts.adapter = adapter
        viewModel.AssetsList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllEstates()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ReceiptsFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun getAllEstates(): Call<List<ReceiptsItem>> {
        TODO("Not yet implemented")
    }
}