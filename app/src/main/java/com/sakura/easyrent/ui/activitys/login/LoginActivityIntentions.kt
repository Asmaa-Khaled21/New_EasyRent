package com.sakura.easyrent.ui.activitys.login

import com.sakura.easyrent.model.User

// Class(LoginActivityIntentions):
sealed class LoginActivityIntentions {

    // Intention(Register):
    data class Register(val user: User) : LoginActivityIntentions()

    // Intention(Login):
    data class Login(val user: User) : LoginActivityIntentions()
}