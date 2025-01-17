package com.solution.poli_poster.Activity.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Adapter.CategoryAdapter
import com.solution.poli_poster.R

class Category_Fragment : Fragment() {

    private lateinit var recyclerViewCategory: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewCategory = view.findViewById(R.id.recyclerViewcategory)

        val categoryList = listOf(
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

        recyclerViewCategory.layoutManager = GridLayoutManager(context, 3)
        recyclerViewCategory.adapter = CategoryAdapter(categoryList)
    }
}
