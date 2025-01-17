package com.solution.poli_poster.Activity.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Adapter.DateContainerAdapter
import com.solution.poli_poster.R

class Fragment_Date_Contant : Fragment() {

    private var selectedDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment__date__contant, container, false)

        // Retrieve the selected date from the arguments
        selectedDate = arguments?.getString("selected_date")

        // Optionally display the selected date in a TextView
        val dateTextView: TextView = rootView.findViewById(R.id.selectedDateTextView)  // Assume you have a TextView for the date
        dateTextView.text = selectedDate

        // List of drawable resource IDs
        val imageResIds = listOf(
            R.drawable.crist1,
            R.drawable.crist2,
            R.drawable.crist3,
            R.drawable.crist1,
            R.drawable.crist2,
            R.drawable.crist3,
            R.drawable.crist1,
            R.drawable.crist2,
            R.drawable.crist3,
            R.drawable.crist1,
            R.drawable.crist2,
            R.drawable.crist3
        )

        // Initialize RecyclerView with GridLayoutManager
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Set 2 items per row

        // Set the adapter with the list of drawable IDs
        recyclerView.adapter = DateContainerAdapter(requireContext(), imageResIds)

        return rootView
    }
}
