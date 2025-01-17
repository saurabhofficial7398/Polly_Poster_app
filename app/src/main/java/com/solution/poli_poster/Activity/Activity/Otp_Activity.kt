package com.solution.poli_poster.Activity.Activity
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.solution.poli_poster.R

class Otp_Activity : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null
    private val otpTimeout = 30000L // 30 seconds
    private var isResendEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val otpBoxes = arrayOf(
            findViewById<EditText>(R.id.otpBox1),
            findViewById<EditText>(R.id.otpBox2),
            findViewById<EditText>(R.id.otpBox3),
            findViewById<EditText>(R.id.otpBox4),
            findViewById<EditText>(R.id.otpBox5),
            findViewById<EditText>(R.id.otpBox6)
        )

        val resendOtpText = findViewById<TextView>(R.id.Resend_otp)
        setupOtpInputs(otpBoxes)

        // Setup Resend OTP functionality
        resendOtpText.setOnClickListener {
            if (isResendEnabled) {
                startOtpTimer()
            }
        }

        val submitButton = findViewById<View>(R.id.submit_button) // Update with actual ID
        submitButton.setOnClickListener {
            if (isOtpEntered(otpBoxes)) {
                val intent = Intent(this, Re_Enter_Password_Activity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter the OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupOtpInputs(otpBoxes: Array<EditText>) {
        for (i in otpBoxes.indices) {
            otpBoxes[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && i < otpBoxes.size - 1) {
                        otpBoxes[i + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            otpBoxes[i].setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (otpBoxes[i].text.isEmpty() && i > 0) {
                        otpBoxes[i - 1].requestFocus()
                    }
                }
                false
            }
        }
    }

    private fun startOtpTimer() {
        isResendEnabled = false
        val resendOtpText = findViewById<TextView>(R.id.Resend_otp)
        resendOtpText.text = "Resend OTP in 30s"
        resendOtpText.paintFlags = 0 // Remove underline while countdown is active

        countDownTimer = object : CountDownTimer(otpTimeout, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = (millisUntilFinished / 1000).toInt()
                resendOtpText.text = "Resend OTP in $secondsLeft sec"
            }

            override fun onFinish() {
                isResendEnabled = true
                resendOtpText.text = "Resend OTP again"
                resendOtpText.paintFlags = resendOtpText.paintFlags or Paint.UNDERLINE_TEXT_FLAG // Underline when it's available to resend
            }
        }
        countDownTimer?.start()
    }

    private fun isOtpEntered(otpBoxes: Array<EditText>): Boolean {
        return otpBoxes.all { it.text.isNotEmpty() }
    }
}
