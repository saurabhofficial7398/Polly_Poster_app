package com.solution.poli_poster.Activity.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Data_Class.Event
import com.solution.poli_poster.R

class TodaySpecialAdapter(private val eventList: List<Event>, private val onEventClick: (Event) -> Unit) : RecyclerView.Adapter<TodaySpecialAdapter.EventViewHolder>() {

    private var selectedPosition = 0 // Start with the first item selected

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: CardView = view.findViewById(R.id.cardview)
        val dateTextView: TextView = view.findViewById(R.id.eventDateTextView)
        val nameTextView: TextView = view.findViewById(R.id.eventNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.iteam_today_spicial_clicked, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.dateTextView.text = event.date
        holder.nameTextView.text = event.eventName

        // Change background color based on selection
        if (position == selectedPosition) {
            holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.background_color))
        } else {
            holder.cardView.setCardBackgroundColor(holder.itemView.context.getColor(R.color.white))
        }

        // Set click listener to update the selected position and notify adapter
        holder.cardView.setOnClickListener {
            onEventClick(event)
            val previousSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(previousSelectedPosition)  // Reset the previous selected item background
            notifyItemChanged(selectedPosition)        // Update the new selected item background
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}
