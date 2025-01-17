package com.solution.poli_poster.Activity.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Activity.Today_Special_Clicked_Activity
import com.solution.poli_poster.R

class Today_Spicial_Card_Adapter(private var itemList: List<Int>) : RecyclerView.Adapter<Today_Spicial_Card_Adapter.CardViewHolder>() {

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.cardImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_today_spicial_card_item, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.imageView.setImageResource(itemList[position])

        // Set click listener to navigate to the detail page
        holder.imageView.setOnClickListener {
            val context = it.context
            val intent = Intent(context,Today_Special_Clicked_Activity::class.java)
            intent.putExtra("image_resource", itemList[position]) // Pass image resource or any other data you need
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateData(newItems: List<Int>) {
        itemList = newItems
        notifyDataSetChanged()
    }
}
