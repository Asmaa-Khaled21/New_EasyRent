package com.sakura.easyrent.fragments.receiptsfragment

data class ReceiptsItem(

    val contractName: String,
    val price: String,
    val payment_Method: String,
    val payment_Sucsses: String,
    val payment_Day: String,
    val payment_Time: String,
    var navigator: ReceiptsFragment
)
