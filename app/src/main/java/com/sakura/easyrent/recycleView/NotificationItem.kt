package com.sakura.easyrent.recycleView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotificationsItem (
    @PrimaryKey(autoGenerate = true)
    val id:Int?= null,
    @ColumnInfo
    var Image:Int? =null,
    @ColumnInfo
    var TopText: String? = null,
    @ColumnInfo
    var BottomText: String?=null,
)