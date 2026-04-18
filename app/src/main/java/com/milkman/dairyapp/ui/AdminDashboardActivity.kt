package com.milkman.dairyapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.milkman.dairyapp.R
import com.milkman.dairyapp.ui.fragments.CustomersFragment
import com.milkman.dairyapp.ui.fragments.EntryFragment
import com.milkman.dairyapp.ui.fragments.HomeFragment
import com.milkman.dairyapp.ui.fragments.ProfileFragment
import com.milkman.dairyapp.ui.fragments.ReportsFragment
import com.milkman.dairyapp.ui.fragments.BuyersFragment
import com.milkman.dairyapp.util.AppConstants
import com.milkman.dairyapp.util.SessionManager

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        sessionManager = SessionManager(this)

        val btnProfile = findViewById<android.widget.ImageButton>(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val role = sessionManager.getRole()
            if (role == AppConstants.ROLE_SUPER_USER) {
                startActivity(Intent(this, SuperUserProfileActivity::class.java))
            } else {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_entry -> EntryFragment()
                R.id.nav_customers -> CustomersFragment()
                R.id.nav_buyers -> BuyersFragment()
                R.id.nav_reports -> ReportsFragment()
                else -> null
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit()
                true
            } else {
                false
            }
        }

        // Load the default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }
    }
}
