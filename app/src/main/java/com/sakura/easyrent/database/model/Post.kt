package com.sakura.easyrent.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user_table")
data class Post(
    @PrimaryKey
    val id: String = "",
    // @ColumnInfo( name ="user_name")
    @SerializedName("username")
    val userName: String? = null,
    // @ColumnInfo( name ="first_name")
    @SerializedName("first_name")
    val firstName: String? = null,
    // @ColumnInfo( name ="last_name")
    @SerializedName("last_name")
    val lastName: String? = null,
    // @ColumnInfo( name ="email")
    val email: String? = null,
    val password: String? = null
)