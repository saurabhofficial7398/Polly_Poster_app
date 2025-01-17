package com.solution.poli_poster.Activity.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.R

// Data class for notifications
data class NotificationItem(
    val iconResId: Int, // Resource ID for the icon
    val title: String,
    val description: String
)

// Adapter class
class NotificationAdapter(
    private val notificationList: List<NotificationItem>,
    private val onNotificationClick: (Int) -> Unit // Lambda for handling item clicks
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    // ViewHolder class
    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val title: TextView = itemView.findViewById(R.id.notificationtitle)
        val description: TextView = itemView.findViewById(R.id.description)

        init {
            itemView.setOnClickListener {
                onNotificationClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.iteam_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notificationList[position]
        holder.icon.setImageResource(notification.iconResId)
        holder.title.text = notification.title
        holder.description.text = notification.description
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }
}
