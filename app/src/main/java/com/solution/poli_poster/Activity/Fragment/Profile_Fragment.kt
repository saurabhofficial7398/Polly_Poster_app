package com.solution.poli_poster.Activity.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ncorti.slidetoact.SlideToActView
import com.solution.poli_poster.Activity.Activity.Login_Activity
import com.solution.poli_poster.R

class Profile_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_, container, false)
        val logoutButton: SlideToActView = view.findViewById(R.id.logout_button)
        logoutButton.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                handleLogout()
            }
        }
        return view
    }
    private fun handleLogout() {
        val intent = Intent(requireContext(),Login_Activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
