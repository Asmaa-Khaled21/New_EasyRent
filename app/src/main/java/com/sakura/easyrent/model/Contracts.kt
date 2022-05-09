package com.sakura.easyrent.model

import android.widget.Button
import com.google.gson.annotations.SerializedName

// Data(Contracts):
data class Contracts(
    val id: String = "",

    @SerializedName("rent_cost")
    val rent_cost: String = User.NOT_INITIALIZED,

    @SerializedName("rent_frequency")
    val rent_frequency: String = User.NOT_INITIALIZED,

    @SerializedName("contract_holder_name")
    val contract_holder_name: String = User.NOT_INITIALIZED,

    @SerializedName("date_from")
    val date_from: String = User.NOT_INITIALIZED,

    @SerializedName("date_to")
    val date_to: String = User.NOT_INITIALIZED,

    @SerializedName("first_rent_due_date")
    val first_rent_due_date: String = User.NOT_INITIALIZED,

//    @SerializedName("owner")
//    val owner: String = User.NOT_INITIALIZED,

    @SerializedName("unit")
    val unit: String = User.NOT_INITIALIZED,

    val button_pay: Button
    ) {}