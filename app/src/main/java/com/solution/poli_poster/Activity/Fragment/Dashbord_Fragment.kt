package com.solution.poli_poster.Activity.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Adapter.DashboardAdapter
import com.solution.poli_poster.R

class Dashbord_Fragment : Fragment() {

    private lateinit var recyclerViewDashboard: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashbord_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewDashboard = view.findViewById(R.id.recyclerViewDashboard)

        val images = listOf(
            R.drawable.file2,
            R.drawable.fie1,
            R.drawable.file2,
            R.drawable.fie1,
            R.drawable.file2,
            R.drawable.fie1,
            R.drawable.file2,
            R.drawable.fie1,
            R.drawable.file2,
            R.drawable.fie1,
            R.drawable.file2,
            R.drawable.fie1,
            R.drawable.file2,
            R.drawable.fie1,
            R.drawable.file2,
            R.drawable.fie1,
            // Add more image resources as needed
        )

        // Use GridLayoutManager with 2 columns
        recyclerViewDashboard.layoutManager = GridLayoutManager(context, 2)
        recyclerViewDashboard.adapter = DashboardAdapter(images)
    }
}
