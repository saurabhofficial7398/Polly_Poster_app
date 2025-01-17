package com.solution.poli_poster.Activity.Adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Activity.Editors_Activity
import com.solution.poli_poster.Activity.Activity.Profile_Basic_Detail_Activity
import com.solution.poli_poster.Activity.Activity.Social_Media_Detail_Edit_Activity
import com.solution.poli_poster.Activity.Activity.Social_Media_Details_Activity
import com.solution.poli_poster.R

class DateContainerAdapter(private val context: Context, private val imageList: List<Int>) : RecyclerView.Adapter<DateContainerAdapter.DateViewHolder>() {

    // SharedPreferences to track the first-time click
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    private val FIRST_TIME_KEY = "first_time_click"

    // ViewHolder class to hold the references to the item views
    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImage = itemView.findViewById<ImageView>(R.id.datecardImage) // Correct reference to the ImageView in your layout
    }

    // Create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.iteam_date_containner, parent, false)
        return DateViewHolder(view)
    }

    // Bind the image to the ImageView in each item
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        val imageResId = imageList[position] // Drawable resource ID
        holder.cardImage.setImageResource(imageResId)

        // Set click listener to navigate to the appropriate activity
        holder.cardImage.setOnClickListener {
            val isFirstTime = sharedPreferences.getBoolean(FIRST_TIME_KEY, true)

            if (isFirstTime) {
                // If it's the first time, open ProfileBasicDetailActivity
                val intent = Intent(context,Social_Media_Detail_Edit_Activity::class.java)
                context.startActivity(intent)

                // After the first time, update the SharedPreferences flag to false
                sharedPreferences.edit().putBoolean(FIRST_TIME_KEY, false).apply()
            } else {
                // If it's not the first time, open Editors_Activity
                val intent = Intent(context, Editors_Activity::class.java)
                intent.putExtra("selected_image", imageResId) // Pass the selected image resource ID
                context.startActivity(intent)
            }
        }
    }

    // Return the size of the list
    override fun getItemCount(): Int = imageList.size
}
