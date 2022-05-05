package com.sakura.easyrent.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sakura.easyrent.base.BaseActivity
import com.sakura.easyrent.ui.main.MainActivity
import com.sakura.easyrent.R
import com.sakura.easyrent.databinding.ActivityLoginBinding

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() ,Navigator{
      @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            viewDataBinding.viewModel = viewModel
            viewModel.navigator =this

            singUp.setOnClickListener {
                singUp.background = resources.getDrawable(R.drawable.switch_trcks,null)
                singUp.setTextColor(resources.getColor(R.color.textColor,null))
                logIn.background = null
                singUpLayout.visibility = View.VISIBLE
                logInLayout.visibility = View.GONE
                logIn.setTextColor(resources.getColor(R.color.Dark_Blue,null))
            }
            logIn.setOnClickListener {
                singUp.background = null
                singUp.setTextColor(resources.getColor(R.color.Dark_Blue,null))
                logIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
                singUpLayout.visibility = View.GONE
                logInLayout.visibility = View.VISIBLE
                logIn.setTextColor(resources.getColor(R.color.textColor,null))
            }
            singIn.setOnClickListener {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }

    override fun getLayoutId(): Int {
       return R.layout.activity_login
    }

    override fun initializeViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun gotoMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    }