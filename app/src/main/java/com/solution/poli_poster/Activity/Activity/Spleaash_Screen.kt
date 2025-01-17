package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.solution.poli_poster.R

class Spleaash_Screen : AppCompatActivity() {
    private val PREF_NAME = "LaunchPref"
    private val IS_FIRST_LAUNCH = "IsFirstLaunch"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spleaash_screen)

        val sharedPreferences: SharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean(IS_FIRST_LAUNCH, true)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (isFirstLaunch) {
                sharedPreferences.edit().putBoolean(IS_FIRST_LAUNCH, false).apply()
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, Dashbord::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}