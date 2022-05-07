package com.sakura.easyrent.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sakura.easyrent.database.model.Post
import retrofit2.Response

open class BaseViewModel <N>:ViewModel(){
    var myResponse : MutableLiveData<Response<Post>> = MutableLiveData()
    var navigator:N?=null
    val messageLiveData = MutableLiveData<String>()
    val showLoading = MutableLiveData < Boolean >()
}