package com.sakura.easyrent.fragments.receiptsfragment

class ReceiptsRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllEstates() = retrofitService.getAllEstates()
}