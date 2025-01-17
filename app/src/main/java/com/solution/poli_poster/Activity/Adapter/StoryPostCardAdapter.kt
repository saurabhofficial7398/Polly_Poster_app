package com.solution.poli_poster.Activity.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.R

class StoryPostCardAdapter(private var itemList: List<Int>) :
    RecyclerView.Adapter<StoryPostCardAdapter.CardViewHolder>() {

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.cardImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_today_spicial_card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.imageView.setImageResource(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateData(newItems: List<Int>) {
        itemList = newItems
        notifyDataSetChanged()
    }
}
