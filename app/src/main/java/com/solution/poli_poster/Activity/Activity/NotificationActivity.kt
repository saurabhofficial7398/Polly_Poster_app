package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.R
import com.solution.poli_poster.Activity.Adapter.NotificationAdapter
import com.solution.poli_poster.Activity.Adapter.NotificationItem

class NotificationActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var notificationCountTextView: TextView
    private lateinit var readAllTextView: TextView // Add reference for "Read All"
    private lateinit var adapter: NotificationAdapter
    private lateinit var notificationList: MutableList<NotificationItem>
    private var notificationCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // Initialize views
        backButton = findViewById(R.id.back_button)
        notificationCountTextView = findViewById(R.id.notification_count)
        readAllTextView = findViewById(R.id.readall) // Initialize "Read All" TextView
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create a list of notifications
        notificationList = mutableListOf(
            NotificationItem(R.drawable.ic_info, "Post Unpublished", "Your post has been unpublished due to policy violations."),
            NotificationItem(R.drawable.ic_info, "New Comment", "Someone commented on your post."),
            NotificationItem(R.drawable.ic_info, "Update Available", "A new update is available for your app."),
            NotificationItem(R.drawable.ic_info, "Reminder", "Don't forget to complete your profile."),
            NotificationItem(R.drawable.ic_info, "Weekly Summary", "Here is your weekly activity summary."),
            NotificationItem(R.drawable.ic_info, "Weekly Summary", "Here is your weekly activity summary."),
            NotificationItem(R.drawable.ic_info, "Weekly Summary", "Here is your weekly activity summary."),
            NotificationItem(R.drawable.ic_info, "Weekly Summary", "Here is your weekly activity summary."),
        )

        // Set initial notification count based on the list size
        notificationCount = notificationList.size

        // Initialize the adapter with the notification list and the click listener
        adapter = NotificationAdapter(notificationList) { position ->
            handleNotificationClick(position)
        }
        recyclerView.adapter = adapter

        // Set initial notification count
        updateNotificationCount()

        // Handle back button click
        backButton.setOnClickListener {
            val intent = Intent(this, Dashbord::class.java)
            startActivity(intent)
            finish()
        }

        // Handle "Read All" click to clear all notifications
        readAllTextView.setOnClickListener {
            // Clear the notification list and update the adapter
            notificationList.clear()
            adapter.notifyDataSetChanged()

            // Reset the notification count to 0 and update the display
            notificationCount = 0
            updateNotificationCount()
        }
    }

    private fun handleNotificationClick(position: Int) {
        // Remove the clicked notification and update the adapter
        if (notificationList.size > position) {
            notificationList.removeAt(position)
            adapter.notifyItemRemoved(position)

            // Decrease the notification count and update the display
            notificationCount--
            updateNotificationCount()
        }
    }

    private fun updateNotificationCount() {
        notificationCountTextView.text = notificationCount.toString()
    }
}
