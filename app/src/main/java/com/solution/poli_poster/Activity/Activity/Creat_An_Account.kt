package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.solution.poli_poster.R

class Creat_An_Account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creat_an_account)

        // Get the button reference
        val createAccountButton = findViewById<View>(R.id.login_button)

        // Set the click listener
        createAccountButton.setOnClickListener {
            // Open LoginActivity
            val intent = Intent(this,Login_Activity::class.java)
            startActivity(intent)
        }
    }
}
