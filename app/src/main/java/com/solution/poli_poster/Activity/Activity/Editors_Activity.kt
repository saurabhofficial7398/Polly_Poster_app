package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Adapter.ImageAdapter
import com.solution.poli_poster.Activity.Data_Class.ImageItem
import com.solution.poli_poster.R

class Editors_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editors)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val btnNext: Button = findViewById(R.id.btn_next) // Find the button by its ID

        // Find the 'back' LinearLayout and set an OnClickListener
        val backButton: LinearLayout = findViewById(R.id.back)
        backButton.setOnClickListener {
            // Start Today_Special_Clicked_Activity when back is clicked
            val intent = Intent(this, Today_Special_Clicked_Activity::class.java)
            startActivity(intent)
        }

        // Get the selected image resource ID passed from the previous activity
        val selectedImageResId = intent.getIntExtra("selected_image", R.drawable.editable) // Default image if not passed

        // Update the ImageView with the selected image
        val bannerImage: ImageView = findViewById(R.id.banner_image)
        bannerImage.setImageResource(selectedImageResId)

        val imageList = listOf(
            ImageItem(R.drawable.editable),
            ImageItem(R.drawable.editable2),
            ImageItem(R.drawable.editable),
            ImageItem(R.drawable.editable2)
        )

        val adapter = ImageAdapter(imageList)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        btnNext.setOnClickListener {
            val intent = Intent(this, Dashbord::class.java)
            startActivity(intent)
        }
    }
}
