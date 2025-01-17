package com.solution.poli_poster.Activity.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solution.poli_poster.Activity.Adapter.TodaySpecialAdapter
import com.solution.poli_poster.Activity.Data_Class.Event
import com.solution.poli_poster.R
import com.solution.poli_poster.Activity.Fragment.Fragment_Date_Contant

class Today_Special_Clicked_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventList: List<Event>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_special_clicked)
        val backToDashboard: LinearLayout = findViewById(R.id.backtodashbord)
        backToDashboard.setOnClickListener {
            val intent = Intent(this, Dashbord::class.java)
            startActivity(intent)
        }
        recyclerView = findViewById(R.id.today_special_date_view)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        eventList = generateDummyEvents()

        if (eventList.isEmpty()) {
            Toast.makeText(this, "No events available", Toast.LENGTH_SHORT).show()
        } else {
            val adapter = TodaySpecialAdapter(eventList) { event ->
                showPostersForDate(event.date)
            }
            recyclerView.adapter = adapter

            if (eventList.isNotEmpty()) {
                showPostersForDate(eventList[0].date)
            }
        }
    }
    private fun generateDummyEvents(): List<Event> {
        return listOf(
            Event("25 Dec", "Christmas Celebration"),
            Event("31 Dec", "New Year's Eve Party"),
            Event("14 Feb", "Valentine's Day"),
            Event("1 May", "Labor Day"),
            Event("15 Aug", "Independence Day")
        )
    }
    private fun showPostersForDate(date: String) {
        Toast.makeText(this, "Show posters for $date", Toast.LENGTH_SHORT).show()
        val fragment = Fragment_Date_Contant().apply {
            arguments = Bundle().apply {
                putString("selected_date", date)  // Pass the selected date
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }
}
