package com.sakura.easyrent.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.sakura.easyrent.R
import com.sakura.easyrent.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper())
            .postDelayed({StartHomeActivity()},2000);
    }

    fun StartHomeActivity()
    {
        val intent= Intent(this, LoginActivity::class.java);
        startActivity(intent);
        finish()
    }

    }
