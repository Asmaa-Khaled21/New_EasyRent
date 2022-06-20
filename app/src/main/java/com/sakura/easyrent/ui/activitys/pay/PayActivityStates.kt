package com.sakura.easyrent.ui.activitys.pay

import com.sakura.easyrent.model.Contract

// Class(PayActivityStates):
sealed class PayActivityStates {

    // State(ContractsState)
    data class ContractsState(val contracts: ArrayList<Contract>) : PayActivityStates()

    // State(FailureState):
    data class FailureState(val message: String, val exception: Exception?) : PayActivityStates()
}