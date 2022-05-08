@file:Suppress("UNUSED_PARAMETER")

package com.sakura.easyrent.ui.activitys.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.sakura.easyrent.R
import com.sakura.easyrent.databinding.ActivityLoginBinding
import com.sakura.easyrent.model.User
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
        // Initializing:
        // Developing:
        binding.singUp.setOnClickListener(::onSignUp)
        binding.logIn.setOnClickListener(::onLogin)
        binding.singIn.setOnClickListener(::onSignIn)
    }

    // Method(OnSignUp):
    private fun onSignUp(view: View?) = changeUI(1)

    // Method(OnLogin):
    private fun onLogin(view: View?) = changeUI(2)

    // Method(OnSignIn):
    private fun onSignIn(view: View?) = startActivity(Intent(this, MainActivity::class.java))

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