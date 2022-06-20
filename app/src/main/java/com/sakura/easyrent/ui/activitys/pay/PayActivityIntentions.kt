package com.sakura.easyrent.ui.activitys.pay

// Class(PayActivityIntentions):
sealed class PayActivityIntentions {

    // Intention(ContractsIntention):
    data class ContractsIntention(val token: String) : PayActivityIntentions()

}