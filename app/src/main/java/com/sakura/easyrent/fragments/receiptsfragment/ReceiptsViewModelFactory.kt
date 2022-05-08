package com.sakura.easyrent.fragments.receiptsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ReceiptsViewModelFactory constructor(private val repository: ReceiptsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ReceiptsViewModel::class.java)) {
            ReceiptsViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}