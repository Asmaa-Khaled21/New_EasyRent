package com.sakura.easyrent.ui.activitys.main

import com.sakura.easyrent.model.RepairRequest

// Class(MainActivityIntentions):
sealed class MainActivityIntentions {

    // Intention(UserMetaData):
    data class UserMetaDataIntention(val token: String) : MainActivityIntentions()

    // Intention(ContractsIntention):
    data class ContractsIntention(val token: String) : MainActivityIntentions()

    // Intention(GetRepairRequests):
    data class GetRepairRequests(val token: String) : MainActivityIntentions()

    // Intention(AddRepairRequests):
    data class AddRepairRequests(val token: String, val request: RepairRequest) : MainActivityIntentions()
}