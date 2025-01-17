package com.solution.poli_poster.Activity.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Activity.Festivals_clicked_activity
import com.solution.poli_poster.R

class VideoStatusAdapter(
    private var items: List<Int>
) : RecyclerView.Adapter<VideoStatusAdapter.VideoStatusViewHolder>() {

    inner class VideoStatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val statusImage: ImageView = view.findViewById(R.id.video_statusImage)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Start FestivalActivity when an item is clicked
                    val context = itemView.context
                    val intent = Intent(context,Festivals_clicked_activity::class.java).apply {
                        putExtra("ITEM_ID", items[position]) // Pass item data if needed
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoStatusViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_status, parent, false)
        return VideoStatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoStatusViewHolder, position: Int) {
        holder.statusImage.setImageResource(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<Int>) {
        items = newItems
        notifyDataSetChanged()
    }
}
