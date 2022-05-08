package com.sakura.easyrent.model

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.sakura.easyrent.R
import com.sakura.easyrent.model.states.ValidationStates
import com.sakura.easyrent.model.states.success
import com.sakura.easyrent.model.states.warning

// Data(User):
data class User(
    val id: String = "",
    @SerializedName("username")
    val userName: String = NOT_INITIALIZED,
    @SerializedName("first_name")
    val firstName: String = NOT_INITIALIZED,
    @SerializedName("last_name")
    val lastName: String = NOT_INITIALIZED,
    val email: String = NOT_INITIALIZED,
    val password: String = NOT_INITIALIZED
) {

    // Static:
    companion object {

        // Fields:
        const val NOT_INITIALIZED: String = "NOT_INITIALIZED"

        // Method(Validate):
        fun validate(ctx: Context, user: User): ValidationStates {
            // Returning:
            return when {
                // Checking:
                user.userName != NOT_INITIALIZED && user.userName.isBlank() -> warning(ctx.getString(R.string.user_name_warning))
                user.firstName != NOT_INITIALIZED && user.firstName.isBlank() -> warning(ctx.getString(R.string.user_name_warning))
                user.lastName != NOT_INITIALIZED && user.lastName.isBlank() -> warning(ctx.getString(R.string.user_name_warning))
                user.email != NOT_INITIALIZED && user.email.isBlank() -> warning(ctx.getString(R.string.email_warning))
                user.password != NOT_INITIALIZED && user.password.isBlank() -> warning(ctx.getString(R.string.password_warning))
                user.password != NOT_INITIALIZED && user.password.length < 6 -> warning(ctx.getString(R.string.pass_length_warning))
                // Else:
                else -> success(true)
            }
        }
    }
}