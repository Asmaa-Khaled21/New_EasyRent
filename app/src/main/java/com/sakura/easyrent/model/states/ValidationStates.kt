package com.sakura.easyrent.model.states

/** [ValidationStates] this for validating any model class */
sealed class ValidationStates {

    // State(Success):
    data class Success(val status: Boolean) : ValidationStates()

    // State(Warning):
    data class Warning(val message: String) : ValidationStates()
}
