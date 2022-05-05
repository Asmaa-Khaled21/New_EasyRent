package com.sakura.easyrent.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel <N>:ViewModel(){
    var navigator:N?=null
    val messageLiveData = MutableLiveData<String>()
    val showLoading = MutableLiveData < Boolean >()
}