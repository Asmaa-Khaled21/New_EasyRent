package com.sakura.easyrent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepairRequest(
    @ColumnInfo
    var details: String = "",
    @ColumnInfo
    var date: String = "",
    @ColumnInfo
    var unit: Long = 0,
    @ColumnInfo
    var notes: String = "",
    @ColumnInfo
    var status: String = StatusTypes.PENDING.name.lowercase(),
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {

    // Status(Types):
    enum class StatusTypes { CANCELLED, CLOSED, PENDING }
}