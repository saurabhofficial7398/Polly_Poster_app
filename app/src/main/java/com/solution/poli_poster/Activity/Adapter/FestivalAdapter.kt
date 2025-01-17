package com.solution.poli_poster.Activity.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.R

import com.solution.poli_poster.Activity.Data_Class.Festival

class FestivalAdapter(private val festivalList: List<Festival>) :
    RecyclerView.Adapter<FestivalAdapter.FestivalViewHolder>() {

    class FestivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventDateTextView: TextView = itemView.findViewById(R.id.eventDateTextView)
        val eventNameTextView: TextView = itemView.findViewById(R.id.eventNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FestivalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.iteams_festivals, parent, false) // Replace "cardview_layout" with your card layout filename
        return FestivalViewHolder(view)
    }

    override fun onBindViewHolder(holder: FestivalViewHolder, position: Int) {
        val festival = festivalList[position]
        holder.eventDateTextView.text = festival.date
        holder.eventNameTextView.text = festival.name
    }

    override fun getItemCount(): Int {
        return festivalList.size
    }
}
