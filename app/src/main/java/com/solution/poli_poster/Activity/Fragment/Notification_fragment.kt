package com.solution.poli_poster.Activity.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Activity.Dashbord
import com.solution.poli_poster.R
import com.solution.poli_poster.Activity.Adapter.NotificationAdapter
import com.solution.poli_poster.Activity.Adapter.NotificationItem
class Notification_fragment : Fragment() {
    private lateinit var backButton: ImageView
    private lateinit var notificationCountTextView: TextView
    private lateinit var readAllTextView: TextView
    private lateinit var adapter: NotificationAdapter
    private lateinit var notificationList: MutableList<NotificationItem>
    private var notificationCount = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_notification_fragment, container, false)
        backButton = rootView.findViewById(R.id.back_button)
        notificationCountTextView = rootView.findViewById(R.id.notification_count)
        readAllTextView = rootView.findViewById(R.id.readall) // Initialize "Read All" TextView
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
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
        notificationCount = notificationList.size
        adapter = NotificationAdapter(notificationList) { position ->
            handleNotificationClick(position)
        }
        recyclerView.adapter = adapter
        updateNotificationCount()
        backButton.setOnClickListener {
            val intent = Intent(activity, Dashbord::class.java)
            startActivity(intent)
            activity?.finish()
        }
        readAllTextView.setOnClickListener {
            notificationList.clear()
            adapter.notifyDataSetChanged()
            notificationCount = 0
            updateNotificationCount()
        }
        return rootView
    }
    private fun handleNotificationClick(position: Int) {
        if (notificationList.size > position) {
            notificationList.removeAt(position)
            adapter.notifyItemRemoved(position)
            notificationCount--
            updateNotificationCount()
        }
    }
    private fun updateNotificationCount() {
        notificationCountTextView.text = notificationCount.toString()
    }
}
