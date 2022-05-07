package com.sakura.easyrent.fragments.receiptsfragment

object ReceiptsDataBase {
    const val  TYPE_ITEM =0
    fun getReceiptsItem():ArrayList<Any> {

    val receiptsItemList= arrayListOf<Any>()
        receiptsItemList.add ( ReceiptsItem.Item (1,"House 1"
            ,"Helwan","The rent has been paid " +
                "20 August 2021 , 5:15 PM"))
        receiptsItemList.add ( ReceiptsItem.Item (2,"House 2"
            ,"October", "The rent has been paid \" +\n" +
                "                \"20 August 2021 , 5:15 PM\""))

        return receiptsItemList

    }
}