package com.solution.poli_poster.Activity.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.solution.poli_poster.Activity.Activity.Search_Activity
import com.solution.poli_poster.Activity.Adapter.*
import com.solution.poli_poster.R

class Home_Fragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAllButton: Button
    private lateinit var storyPostRecyclerView: RecyclerView
    private lateinit var topViewAllButton: Button
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var bannerRecyclerView: RecyclerView
    private lateinit var bannerViewAllButton: Button
    private lateinit var searchBadge: LinearLayout
    private val videoStatusFullList = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2
    )
    private val videoStatusInitialList = videoStatusFullList.take(3)
    private lateinit var videoStatusRecyclerView: RecyclerView
    private lateinit var videoStatusViewAllButton: Button
    private lateinit var videoStatusAdapter: VideoStatusAdapter
    private val bannerImages = listOf(
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner1
    )
    private val fullItemList = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2
    )
    private val initialItemList = fullItemList.take(3)
    private val bannerPosterImages = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2
    )
    private val initialBannerPosterImages = bannerPosterImages.take(3)
    private val storyPostFullList = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image1,
        R.drawable.image2
    )
    private val storyPostInitialList = storyPostFullList.take(3)
    private val categoryList = listOf(
        Pair(R.drawable.birthday_image, "Birthday"),
        Pair(R.drawable.meeting_image, "Meeting"),
        Pair(R.drawable.quote_image, "Quote"),
        Pair(R.drawable.morning_image, "Morning"),
        Pair(R.drawable.category_image, "Category"),
        Pair(R.drawable.category2_image, "Category 2"),
        Pair(R.drawable.poster_image, "Poster"),
        Pair(R.drawable.poster_image, "Poster"),
        Pair(R.drawable.view_all_image, "View All")
    )
    private var currentPage = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var autoScrollRunnable: Runnable
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        dotsLayout = view.findViewById(R.id.dotsLayout)
        recyclerView = view.findViewById(R.id.recyclerView)
        viewAllButton = view.findViewById(R.id.viewAllButton)
        searchBadge = view.findViewById(R.id.search_badge)
        searchBadge.setOnClickListener {
            val intent = Intent(requireContext(), Search_Activity::class.java)
            startActivity(intent)
        }
        val adapter = BannerAdapter(bannerImages)
        viewPager.adapter = adapter
        setupDots()
        autoScrollRunnable = Runnable {
            currentPage = (currentPage + 1) % bannerImages.size
            viewPager.setCurrentItem(currentPage, true)
            handler.postDelayed(autoScrollRunnable, 3000)
        }
        handler.post(autoScrollRunnable)
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val cardAdapter = Today_Spicial_Card_Adapter(initialItemList)
        recyclerView.adapter = cardAdapter
        viewAllButton.setOnClickListener {
            cardAdapter.updateData(fullItemList)
            viewAllButton.visibility = View.GONE
        }
        videoStatusRecyclerView = view.findViewById(R.id.Video_Status_View)
        videoStatusViewAllButton = view.findViewById(R.id.Video_StatusviewAllButton)
        videoStatusRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        videoStatusAdapter = VideoStatusAdapter(videoStatusInitialList)
        videoStatusRecyclerView.adapter = videoStatusAdapter
        videoStatusViewAllButton.setOnClickListener {
        videoStatusAdapter.updateData(videoStatusFullList)
        videoStatusViewAllButton.visibility = View.GONE
        }
        storyPostRecyclerView = view.findViewById(R.id.Story_post_View)
        topViewAllButton = view.findViewById(R.id.topviewAllButton)
        storyPostRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val storyPostAdapter = StoryPostCardAdapter(storyPostInitialList)
        storyPostRecyclerView.adapter = storyPostAdapter
        topViewAllButton.setOnClickListener {
        storyPostAdapter.updateData(storyPostFullList)
        topViewAllButton.visibility = View.GONE
        }
        categoryRecyclerView = view.findViewById(R.id.category_recycler_View)
        categoryRecyclerView.layoutManager = GridLayoutManager(context, 3)
        val categoryAdapter = CategoryAdapter(categoryList)
        categoryRecyclerView.adapter = categoryAdapter
        bannerRecyclerView = view.findViewById(R.id.Banners_Poster_View)
        bannerViewAllButton = view.findViewById(R.id.Banners_PosterviewAllButton)
        bannerRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val bannerPosterAdapter = BannerPosterAdapter(initialBannerPosterImages)
        bannerRecyclerView.adapter = bannerPosterAdapter
        bannerViewAllButton.setOnClickListener {
        bannerPosterAdapter.updateData(bannerPosterImages)
        bannerViewAllButton.visibility = View.GONE
        }
        return view
    }
    private fun setupDots() {
        dotsLayout.removeAllViews()
        for (i in bannerImages.indices) {
            val dot = ImageView(requireContext())
            dot.setImageResource(R.drawable.dot_unselected)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 0, 10, 0)
            dotsLayout.addView(dot, params)
        }
        updateDots(0)
    }
    private fun updateDots(position: Int) {
        for (i in 0 until dotsLayout.childCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageResource(if (i == position) R.drawable.dot_selected else R.drawable.dot_unselected)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(autoScrollRunnable)
    }
}
