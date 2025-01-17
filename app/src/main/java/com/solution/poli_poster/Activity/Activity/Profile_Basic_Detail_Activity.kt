package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.solution.poli_poster.R

class Profile_Basic_Detail_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_basic_detail)

        val dropdownIcon: ImageView = findViewById(R.id.dropdown_icon)
        val dropdownText: TextView = findViewById(R.id.dropdown_text)
        val nextButton: Button = findViewById(R.id.btn_next) // Find the "Next" button by its ID

        // Add click listener to the dropdown icon
        dropdownIcon.setOnClickListener {
            showDropdownMenu(dropdownIcon, dropdownText)
        }

        // Add click listener to the "Next" button
        nextButton.setOnClickListener {
            // Navigate to Social_Media_Detail_Activity
            val intent = Intent(this, Social_Media_Details_Activity::class.java)
            startActivity(intent)
        }
    }

    private fun showDropdownMenu(anchor: ImageView, dropdownText: TextView) {
        val popupMenu = PopupMenu(this, anchor)
        popupMenu.menuInflater.inflate(R.menu.political_party_menu, popupMenu.menu)

        // Handle menu item clicks
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            dropdownText.text = menuItem.title // Update the TextView with the selected item
            true
        }
        popupMenu.show()
    }
}
