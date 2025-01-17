package com.solution.poli_poster.Activity.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.R

class BannerPosterAdapter(private var bannerList: List<Int>) : RecyclerView.Adapter<BannerPosterAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.iteam_banners_poster, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(bannerList[position])
    }

    override fun getItemCount(): Int = bannerList.size

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bannerImageView: ImageView = itemView.findViewById(R.id.bannerposterImage)

        fun bind(imageResId: Int) {
            bannerImageView.setImageResource(imageResId)
        }
    }

    fun updateData(newItems: List<Int>) {
        bannerList = newItems
        notifyDataSetChanged()
    }
}
