package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.solution.poli_poster.R
import com.solution.poli_poster.adapter.ImagePagerAdapter

class Social_Media_Detail_Edit_Activity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var dotsIndicator: LinearLayout
    private val images = listOf(
        R.drawable.banner, R.drawable.banner2, R.drawable.banner
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_media_detail_edit)
        val nextButton: Button = findViewById(R.id.btn_next)
        nextButton.setOnClickListener {
            // Open Social_Media_Detail_Edit_Activity
            val intent = Intent(this,Editors_Activity::class.java)
            startActivity(intent)
        }

        viewPager = findViewById(R.id.imageViewPager)
        dotsIndicator = findViewById(R.id.dotsIndicator)

        val adapter = ImagePagerAdapter(this, images)
        viewPager.adapter = adapter

        setupDotsIndicator(images.size)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDotsIndicator(position)
            }
        })
    }

    private fun setupDotsIndicator(size: Int) {
        for (i in 0 until size) {
            val dot = TextView(this).apply {
                text = "•"
                textSize = 36f
                setTextColor(resources.getColor(R.color.gray, theme))
            }
            dotsIndicator.addView(dot)
        }
        updateDotsIndicator(0) // Highlight the first dot
    }

    private fun updateDotsIndicator(selectedPosition: Int) {
        for (i in 0 until dotsIndicator.childCount) {
            val dot = dotsIndicator.getChildAt(i) as TextView
            dot.setTextColor(
                if (i == selectedPosition) resources.getColor(R.color.app_background_color, theme)
                else resources.getColor(R.color.gray, theme)
            )
        }
    }
}
