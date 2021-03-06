package com.sakura.easyrent.ui.activitys.login

import com.sakura.easyrent.model.AccessToken
import com.sakura.easyrent.model.User

// Class(LoginActivityStates):
sealed class LoginActivityStates {

    // State(RegisterState):
    data class RegisterState(val status: Boolean, val user: User) : LoginActivityStates()

    // State(LoginState):
    data class LoginState(val status: Boolean, val token: AccessToken) : LoginActivityStates()

    // State(FailureState):
    data class FailureState(val message: String, val exception: Exception?) : LoginActivityStates()
}