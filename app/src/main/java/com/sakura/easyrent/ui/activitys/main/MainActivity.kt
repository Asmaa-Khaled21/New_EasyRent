package com.sakura.easyrent.ui.activitys.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.sakura.easyrent.ui.fragments.HomeFragment
import com.sakura.easyrent.ui.fragments.NotificationFragment
import com.sakura.easyrent.ui.fragments.ReceiptsFragment
import com.sakura.easyrent.ui.fragments.SettingsFragment
import com.sakura.easyrent.R
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.sakura.easyrent.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // Fields:
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val model: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    // Companion:
    companion object {
        // Tags:
        @Suppress("unused")
        const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Super:
        super.onCreate(savedInstanceState)
        setContentView(binding.root) // Setting content to the root of binding.
        // Initializing(UI):
        initializeUI()
        // Developing:
        binding.bottomNavigation.setOnClickMenuListener(::onMenuClick)
    }

    // Method(OnMenuClick):
    private fun onMenuClick(model: MeowBottomNavigation.Model) {
        // Checking:
        when (model.id) {
            // Replacing:
            0 -> replaceFragment(HomeFragment())
            1 -> replaceFragment(ReceiptsFragment())
            2 -> replaceFragment(NotificationFragment())
            3 -> replaceFragment(SettingsFragment())
            // Else:
            else -> replaceFragment(HomeFragment())
        }
    }

    // Method(InitializeUI):
    private fun initializeUI() {
        // Adding:
        addFragment(HomeFragment())
        // Showing:
        binding.bottomNavigation.show(0)
        // Adding:
        binding.bottomNavigation.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_receipts))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_notificationbar))
        binding.bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_setting))
    }

    // Method(ReplaceFragment):
    private fun replaceFragment(fragment: Fragment) {
        // Initializing:
        val transactions: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Replacing:
        transactions.replace(R.id.fragmentContainer, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    // Method(AddFragment):
    private fun addFragment(fragment: Fragment) {
        // Initializing:
        val transactions: FragmentTransaction = supportFragmentManager.beginTransaction()
        // Adding:
        transactions.add(R.id.fragmentContainer, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}