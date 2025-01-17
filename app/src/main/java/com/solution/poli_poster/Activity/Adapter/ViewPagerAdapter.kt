package com.solution.poli_poster.Activity.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solution.poli_poster.Activity.Fragment.Fourth_Fragment_Screen
import com.solution.poli_poster.Activity.Fragment.Frist_screen
import com.solution.poli_poster.Activity.Fragment.Second_fragment_screen
import com.solution.poli_poster.Activity.Fragment.Thired_Fragment_screen

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Frist_screen()
            1 -> Second_fragment_screen()
            2 -> Thired_Fragment_screen()
            3 -> Fourth_Fragment_Screen()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
