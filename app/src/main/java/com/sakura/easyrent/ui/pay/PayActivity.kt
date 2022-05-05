package com.sakura.easyrent.ui.pay

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sakura.easyrent.R
import kotlinx.android.synthetic.main.activity_pay.*


class PayActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        creditText.setOnClickListener{

            creditText.background= resources.getDrawable(R.drawable.ic_credit_select,null)
            fawrytText.background = null
            creditLayout.visibility = View.VISIBLE
            fawryLayout.visibility = View.GONE
        }
        fawrytText.setOnClickListener {
            creditText.background = null
            fawrytText.background = resources.getDrawable(R.drawable.switch_trcks,null)
            creditLayout.visibility = View.GONE
            fawryLayout.visibility = View.VISIBLE
        }
    }
}