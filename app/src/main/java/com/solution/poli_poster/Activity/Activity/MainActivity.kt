package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.solution.poli_poster.Activity.Adapter.ViewPagerAdapter
import com.solution.poli_poster.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var btnSkip: Button
    private lateinit var btnNext: Button
    private lateinit var btnLetsStart: Button
    private lateinit var letsStartBottomNavigation: LinearLayout
    private lateinit var dots: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        btnSkip = findViewById(R.id.btn_skip)
        btnNext = findViewById(R.id.btn_next)
        btnLetsStart = findViewById(R.id.lts_startbtn_next)
        letsStartBottomNavigation = findViewById(R.id.letsstratbottom_navigation)
        dots = listOf(
            findViewById(R.id.dot1),
            findViewById(R.id.dot2),
            findViewById(R.id.dot3),
            findViewById(R.id.dot4)
        )

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateDots(position)

                if (position == dots.size - 1) { // Fourth dot
                    btnNext.visibility = Button.GONE
                    letsStartBottomNavigation.visibility = LinearLayout.VISIBLE
                } else {
                    btnNext.visibility = Button.VISIBLE
                    letsStartBottomNavigation.visibility = LinearLayout.GONE
                }
            }
        })

        // Skip button to navigate to Login Activity
        btnSkip.setOnClickListener {
            val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
            finish()  // Optionally finish the MainActivity
        }

        // Next button to move to the next page
        btnNext.setOnClickListener {
            val nextItem = (viewPager.currentItem + 1) % viewPagerAdapter.itemCount
            viewPager.setCurrentItem(nextItem, true)
        }

        // Let's Start button to navigate to Login Activity
        btnLetsStart.setOnClickListener {
            val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
            finish()
        }

        // Let's Start button long click to reset ViewPager
        btnLetsStart.setOnLongClickListener {
            viewPager.setCurrentItem(0, true)
            btnNext.visibility = Button.VISIBLE
            letsStartBottomNavigation.visibility = LinearLayout.GONE
            true
        }
    }

    private fun updateDots(position: Int) {
        dots.forEachIndexed { index, imageView ->
            imageView.setBackgroundResource(
                if (index == position) R.drawable.indecator_selected else R.drawable.indicator_unselected
            )
        }
    }
}
