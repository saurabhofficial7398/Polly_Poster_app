package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.R
import com.solution.poli_poster.Activity.Adapter.SearchPageAdapter

class Search_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchPageAdapter
    private lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // Initialize the filter icon
        val filterIcon: ImageView = findViewById(R.id.filter_icon)
        filterIcon.setOnClickListener {
            val bottomSheetFragment = FilterBottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        // Initialize the RecyclerView for displaying images
        imageList = listOf(
            R.drawable.image1, R.drawable.image2, R.drawable.image1,
            R.drawable.image2, R.drawable.image1, R.drawable.image2
        )

        recyclerView = findViewById(R.id.image_grid)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = SearchPageAdapter(imageList)
        recyclerView.adapter = adapter

        // Initialize the search input
        val searchInput: EditText = findViewById(R.id.search_input)
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterImages(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Set up the back and title container to navigate to the dashboard
        val backAndTitleContainer: LinearLayout = findViewById(R.id.back_and_title_container)
        backAndTitleContainer.setOnClickListener {
            // Navigate to DashboardActivity
            val intent = Intent(this,Dashbord::class.java)
            startActivity(intent)
        }
    }

    private fun filterImages(searchText: String) {
        val filteredList = imageList.filter { image ->
            val imageName = getImageName(image)
            imageName.contains(searchText, ignoreCase = true)
        }
        adapter.updateData(filteredList)
    }

    private fun getImageName(imageResId: Int): String {
        return when (imageResId) {
            R.drawable.image1 -> "Image 1"
            R.drawable.image2 -> "Image 2"
            else -> "Unknown"
        }
    }
}
