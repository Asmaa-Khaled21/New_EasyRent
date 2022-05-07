package com.sakura.easyrent.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity( tableName = "user_table")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    // @ColumnInfo( name ="user_name")
    val userName: String? = null,
    // @ColumnInfo( name ="first_name")
    val firstName:String? =null,
    // @ColumnInfo( name ="last_name")
    val lastName:String?=null,
    // @ColumnInfo( name ="email")
    val email: String? = null
)