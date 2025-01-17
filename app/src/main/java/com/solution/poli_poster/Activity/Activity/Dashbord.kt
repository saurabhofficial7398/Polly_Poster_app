package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.solution.poli_poster.Activity.Fragment.Category_Fragment
import com.solution.poli_poster.Activity.Fragment.Dashbord_Fragment
import com.solution.poli_poster.Activity.Fragment.Home_Fragment
import com.solution.poli_poster.Activity.Fragment.Notification_fragment
import com.solution.poli_poster.Activity.Fragment.Profile_Fragment
import com.solution.poli_poster.R

class Dashbord : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashbord)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set the default selected item as Home
        bottomNavigationView.selectedItemId = R.id.navigation_home

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.scroll_view_section, Home_Fragment())
                .commit()
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.navigation_home -> Home_Fragment()
                R.id.navigation_dashboard -> Dashbord_Fragment()
                R.id.navigation_category -> Notification_fragment()
                R.id.navigation_profile -> Profile_Fragment()
                else -> Home_Fragment()
            }

            // Replace the current fragment with the selected one
            supportFragmentManager.beginTransaction()
                .replace(R.id.scroll_view_section, fragment)
                .commit()

            true
        }
    }
}

