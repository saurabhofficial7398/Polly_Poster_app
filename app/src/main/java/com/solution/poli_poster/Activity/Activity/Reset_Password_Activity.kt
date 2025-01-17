package com.solution.poli_poster.Activity.Activity


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.solution.poli_poster.R

class Reset_Password_Activity : AppCompatActivity() {

    private lateinit var phoneEditText: EditText
    private lateinit var getOtpButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        phoneEditText = findViewById(R.id.PhoneEditText)
        getOtpButton = findViewById(R.id.getotp)

        getOtpButton.setOnClickListener {
            val phoneNumber = phoneEditText.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                phoneEditText.error = "Enter a valid 10-digit mobile number"
                phoneEditText.requestFocus()
            } else if (!isValidPhoneNumber(phoneNumber)) {
                phoneEditText.error = "Enter a valid 10-digit mobile number"
                phoneEditText.requestFocus()
            } else {
                // Navigate to OTP activity
                val intent = Intent(this,Otp_Activity::class.java)
                intent.putExtra("phone_number", phoneNumber)
                startActivity(intent)
            }
        }
    }

    private fun isValidPhoneNumber(phoneNumber: String): Boolean {
        // Check if phone number is exactly 10 digits and contains only numbers
        return phoneNumber.length == 10 && phoneNumber.all { it.isDigit() }
    }
}
