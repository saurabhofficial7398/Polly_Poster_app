package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.solution.poli_poster.R

class Social_Media_Details_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media_details)
        val nextButton: Button = findViewById(R.id.btn_next)

        // Set click listener
        nextButton.setOnClickListener {
            // Open Social_Media_Detail_Edit_Activity
            val intent = Intent(this, Social_Media_Detail_Edit_Activity::class.java)
            startActivity(intent)
        }
    }
}