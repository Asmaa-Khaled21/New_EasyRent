package com.sakura.easyrent.ui.activitys.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sakura.easyrent.control.managers.SPManager
import com.sakura.easyrent.databinding.ActivitySplashBinding
import com.sakura.easyrent.ui.activitys.login.LoginActivity
import com.sakura.easyrent.ui.activitys.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    // Fields:
    private val binding: ActivitySplashBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    @Inject lateinit var manager: SPManager

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
    private fun onSplash() {
        // Initializing:
        val activity = if ((manager.read(SPManager.ACCESS_TOKEN, "") as String).isBlank()) LoginActivity::class.java
        else MainActivity::class.java
        // Starting:
        startActivity(Intent(this, activity)).also { finish() }
    }
}
