package com.sakura.easyrent.ui.activitys.main

// Class(MainActivityIntentions):
sealed class MainActivityIntentions {

    // Intention(UserMetaData):
    data class UserMetaDataIntention(val token: String) : MainActivityIntentions()

    // Intention(ContractsIntention):
    data class ContractsIntention(val token: String) : MainActivityIntentions()
}