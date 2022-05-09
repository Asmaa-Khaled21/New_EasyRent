package com.sakura.easyrent.ui.activitys.main

import com.sakura.easyrent.model.Contract

// Class(MainActivityStates):
sealed class MainActivityStates {

    // State(ContractsState)
    data class ContractsState(val contracts: ArrayList<Contract>) : MainActivityStates()

    // State(FailureState):
    data class FailureState(val message: String, val exception: Exception?) : MainActivityStates()
}