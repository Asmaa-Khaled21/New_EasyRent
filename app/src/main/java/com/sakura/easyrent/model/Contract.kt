package com.sakura.easyrent.model

import com.google.gson.annotations.SerializedName

// Data(Contracts):
data class Contract(
    // Fields:
    val id: Long,
    @SerializedName("rent_cost")
    val rentCost: Long,
    @SerializedName("rent_frequency")
    val rentFrequency: String,
    @SerializedName("contract_holder_name")
    val contractHolderName: String,
    @SerializedName("date_from")
    val dateFrom: String,
    @SerializedName("date_to")
    val dateTo: String,
    @SerializedName("first_rent_due_date")
    val firstRentDueDate: String,
    @SerializedName("owner")
    val owner: Long,
    @SerializedName("unit")
    val unit: Long
)