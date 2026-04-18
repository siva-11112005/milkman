package com.milkman.dairyapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.milkman.dairyapp.R
import com.milkman.dairyapp.databinding.ActivityDashboardBinding
import com.milkman.dairyapp.ui.fragments.CustomersFragment
import com.milkman.dairyapp.ui.fragments.EntriesFragment
import com.milkman.dairyapp.ui.fragments.HomeFragment
import com.milkman.dairyapp.ui.fragments.ReportsFragment
import com.milkman.dairyapp.ui.fragments.SettingsFragment
import com.milkman.dairyapp.util.SessionManager

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // Set initial fragment
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_entries -> {
                    loadFragment(EntriesFragment())
                    true
                }
                R.id.nav_customers -> {
                    loadFragment(CustomersFragment())
                    true
                }
                R.id.nav_reports -> {
                    loadFragment(ReportsFragment())
                    true
                }
                R.id.nav_settings -> {
                    loadFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
