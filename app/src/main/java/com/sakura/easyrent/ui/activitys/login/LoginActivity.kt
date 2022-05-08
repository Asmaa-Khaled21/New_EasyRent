@file:Suppress("UNUSED_PARAMETER")

package com.sakura.easyrent.ui.activitys.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sakura.easyrent.R
import com.sakura.easyrent.databinding.ActivityLoginBinding
import com.sakura.easyrent.model.User
import com.sakura.easyrent.model.states.ValidationStates
import com.sakura.easyrent.ui.activitys.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    // Fields:
    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    @Suppress("unused")
    private val model: LoginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "LoginActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Super:
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // Setting content to the root of binding.
        // Rendering:
        render()
        // Developing:
        binding.singUp.setOnClickListener(::onSignUp)
        binding.logIn.setOnClickListener(::onLogin)
        binding.signIn.setOnClickListener(::onSignIn)
    }

    // Method(Render):
    private fun render() {
        // Launching:
        lifecycleScope.launchWhenCreated {
            // Collecting:
            model.states.collect {
                // Checking:
                when (it) {
                    // Developing:
                    is LoginActivityStates.RegisterState -> if (it.status) onSignSuccess()
                    is LoginActivityStates.LoginState -> if (it.status) onSignSuccess()
                    is LoginActivityStates.FailureState -> Log.e(TAG, "render: ${it.message}", it.exception)
                }
            }
        }
    }

    // Method(OnSignSuccess):
    private fun onSignSuccess() = startActivity(Intent(this, MainActivity::class.java))

    // Method(OnSignUp):
    private fun onSignUp(view: View?) = changeUI(1)

    // Method(OnLogin):
    private fun onLogin(view: View?) = changeUI(2)

    // Method(OnSignIn):
    private fun onSignIn(view: View?) {
        // Initializing:
        val isUserOnRegister = binding.singUp.background != null
        val user: User
        // Checking:
        if (isUserOnRegister) { // That's mean user want to sign up not login
            // Initializing:
            user = User(
                // Fields:
                (1..1000).random().toString(),
                binding.UserName.text.toString(),
                binding.FirstName.text.toString(),
                binding.LastName.text.toString(),
                binding.EmailAdress.text.toString(),
                binding.passwordsRegister.text.toString()
            )
        } else {
            // Initializing:
            user = User(
                // Fields:
                userName = binding.UserName.text.toString(),
                password = binding.passwords.text.toString()
            )
        }
        // Checking:
        when (val validation = User.validate(this, user)) {
            // Validating:
            is ValidationStates.Success -> onValidationSuccess(isUserOnRegister, user)
            is ValidationStates.Warning -> Toast.makeText(this, validation.message, Toast.LENGTH_SHORT).show()
        }
    }

    // Method(OnValidationSuccess):
    private fun onValidationSuccess(isUserOnRegister: Boolean, user: User) {
        // Launching:
        lifecycleScope.launchWhenCreated {
            // Sending:
            if (isUserOnRegister) model.channel.send(LoginActivityIntentions.Register(user))
            else model.channel.send(LoginActivityIntentions.Login(user))
        }
    }

    // Method(ChangeUI):
    private fun changeUI(type: Int) {
        // Backgrounds:
        binding.singUp.background = if (type == 1) ContextCompat.getDrawable(this, R.drawable.switch_trcks) else null
        binding.logIn.background = if (type == 2) ContextCompat.getDrawable(this, R.drawable.switch_trcks) else null
        // Colors:
        binding.singUp.setTextColor(ContextCompat.getColor(this, if (type == 1) R.color.textColor else R.color.Dark_Blue))
        binding.logIn.setTextColor(ContextCompat.getColor(this, if (type == 2) R.color.textColor else R.color.Dark_Blue))
        // Visibility:
        binding.singUpLayout.visibility = if (type == 1) View.VISIBLE else View.GONE
        binding.logInLayout.visibility = if (type == 1) View.GONE else View.VISIBLE
    }
}