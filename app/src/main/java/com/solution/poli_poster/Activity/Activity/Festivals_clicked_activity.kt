package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Adapter.FestivalAdapter
import com.solution.poli_poster.Activity.Data_Class.Festival
import com.solution.poli_poster.R

class Festivals_clicked_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_festivals_clicked)

        // Initialize the RecyclerView
        val festivalList = listOf(
            Festival("25 Dec", "Christmas"),
            Festival("31 Oct", "Halloween"),
            Festival("1 Jan", "New Year"),
            Festival("14 Feb", "Valentine's Day")
        )

        val recyclerView: RecyclerView = findViewById(R.id.Festival_date_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = FestivalAdapter(festivalList)

        // Set up the back button click listener
        val backToDashboard: LinearLayout = findViewById(R.id.backtodashbord)
        backToDashboard.setOnClickListener {
            val intent = Intent(this,Dashbord::class.java)
            startActivity(intent)
            finish() // Close the current activity
        }
    }
}
