package com.solution.poli_poster.Activity.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.R
import de.hdodenhof.circleimageview.CircleImageView

class CategoryAdapter(private val categories: List<Pair<Int, String>>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: CircleImageView = view.findViewById(R.id.image)
        val titleView: TextView = view.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (imageResId, title) = categories[position]
        holder.imageView.setImageResource(imageResId)
        holder.titleView.text = title
    }

    override fun getItemCount(): Int = categories.size
}
