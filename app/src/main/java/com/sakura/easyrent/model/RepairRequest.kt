package com.sakura.easyrent.model
import android.widget.ImageButton
import android.widget.Spinner
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepairRequest (
    @PrimaryKey(autoGenerate = true)
    val id:Int?= null,
    @ColumnInfo
    var details:String? =null,
    @ColumnInfo
    var Date: ImageButton? = null,
    @ColumnInfo
    var Status: Spinner?=null,
    @ColumnInfo
    var Unit: Spinner?=null,
    @ColumnInfo
    var Notes: String?=null,
)