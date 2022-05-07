package com.sakura.easyrent.fragments.receiptsfragment

sealed class ReceiptsItem {
    data class Item(val id:Int ,val HouseNumber:String ,val gbs :String ,val done:String):ReceiptsItem()
}