@file:Suppress("UNUSED_PARAMETER")

package com.sakura.easyrent.ui.activitys.pay

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.sakura.easyrent.R
import com.sakura.easyrent.databinding.ActivityPayBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PayActivity : AppCompatActivity() {

    // Fields:
    private val binding: ActivityPayBinding by lazy { ActivityPayBinding.inflate(layoutInflater) }
    private val model: PayViewModel by lazy { ViewModelProvider(this)[PayViewModel::class.java] }

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "PayActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Super:
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // Setting content to the root of binding.
        // Developing:
        binding.creditText.setOnClickListener(::onCreditTextClicked)
        binding.fawrytText.setOnClickListener(::onFawryTextClicked)
    }

    // Method(OnCreditTextClicked):
    private fun onCreditTextClicked(view: View?) = changeUI(1)

    // Method(OnFawryTextClicked):
    private fun onFawryTextClicked(view: View?) = changeUI(2)

    // Method(ChangeUI):
    private fun changeUI(type: Int) {
        // Background:
        binding.creditText.background = if (type == 1) ContextCompat.getDrawable(this, R.drawable.switch_trcks_pay) else null
        binding.fawrytText.background = if (type == 2) ContextCompat.getDrawable(this, R.drawable.switch_trcks_pay) else null
        // Colors:
        if (type == 1)
        binding.creditText.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_creditcard, 0, 0) else null
        if (type == 1)
        binding.fawrytText.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_fawry_blue,0,0) else null
        if (type == 2)
            binding.creditText.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_creditcard_blue, 0, 0) else null
        if (type == 2)
            binding.fawrytText.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_fawry,0,0) else null

        binding.creditText.setTextColor(ContextCompat.getColor(this, if (type == 1) R.color.textColor else R.color.Dark_Blue))
        binding.fawrytText.setTextColor(ContextCompat.getColor(this, if (type == 2) R.color.textColor else R.color.Dark_Blue))

        // Visibility:
        binding.creditLayout.visibility = if (type == 1) View.VISIBLE else View.GONE
        binding.fawryLayout.visibility = if (type == 2) View.VISIBLE else View.GONE
    }
}