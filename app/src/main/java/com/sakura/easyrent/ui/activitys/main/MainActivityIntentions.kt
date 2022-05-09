package com.sakura.easyrent.ui.activitys.main

// Class(MainActivityIntentions):
sealed class MainActivityIntentions {

    // Intention(Contracts):
    data class Contracts(val token: String) : MainActivityIntentions()
}