package com.sakura.easyrent.fragments.receiptsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReceiptsViewModel constructor(private val repository: ReceiptsRepository)  : ViewModel() {

    val AssetsList = MutableLiveData<List<ReceiptsItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllEstates() {
        val response = repository.getAllEstates()
        response.enqueue(object : Callback<List<ReceiptsItem>> {
            override fun onResponse(call: Call<List<ReceiptsItem>>, response: Response<List<ReceiptsItem>>) {
                AssetsList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<ReceiptsItem>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}