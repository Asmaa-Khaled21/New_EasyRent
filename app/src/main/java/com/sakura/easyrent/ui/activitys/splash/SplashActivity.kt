package com.sakura.easyrent.ui.activitys.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sakura.easyrent.databinding.ActivitySplashBinding
import com.sakura.easyrent.ui.activitys.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    // Fields:
    private val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "SplashActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Super:
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // Setting content to the root of binding.
        // Developing::
        Handler(Looper.getMainLooper()).postDelayed(::onSplash, 2000)
    }

    // Method(OnSplash):
    private fun onSplash() = startActivity(Intent(this, LoginActivity::class.java)).also { finish() }
}
