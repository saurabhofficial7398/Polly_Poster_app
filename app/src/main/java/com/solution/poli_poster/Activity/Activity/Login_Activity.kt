package com.solution.poli_poster.Activity.Activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.solution.poli_poster.R

class Login_Activity : AppCompatActivity() {

    private lateinit var mobileNumberEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var togglePasswordVisibility: ImageView
    private var isPasswordVisible = false
    private val dummyPassword = "Polly123"
    private lateinit var loaderDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI elements
        mobileNumberEditText = findViewById(R.id.PhoneEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility)
        val openProfileButton: Button = findViewById(R.id.login_button)

        // Underline reset password link
        val resetPasswordTextView: TextView = findViewById(R.id.reset_password)
        resetPasswordTextView.paintFlags = resetPasswordTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        resetPasswordTextView.setOnClickListener {
            val intent = Intent(this, Reset_Password_Activity::class.java)
            startActivity(intent)
        }

        // Handle Create Account link
        val createAccountTextView: TextView = findViewById(R.id.create_account)
        createAccountTextView.setOnClickListener {
            // Navigate to Profile Basic Detail Activity
            val intent = Intent(this,Creat_An_Account::class.java)
            startActivity(intent)
        }

        // Set up loader dialog
        setupLoaderDialog()

        // Open Profile on login button click
        openProfileButton.setOnClickListener {
            val mobileNumber = mobileNumberEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Show loader
            showLoader()
            Handler(Looper.getMainLooper()).postDelayed({
                if (validateMobileNumber(mobileNumber) && validatePassword(password)) {
                    hideLoader()
                    val intent = Intent(this, Dashbord::class.java)
                    startActivity(intent)
                } else {
                    hideLoader()
                }
            }, 2000) // Simulate a delay for demonstration
        }
        togglePasswordVisibility.setOnClickListener {
            if (isPasswordVisible) {
                passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                togglePasswordVisibility.setImageResource(R.drawable.closecyes)
            } else {
                passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                togglePasswordVisibility.setImageResource(R.drawable.open_eye)
            }
            passwordEditText.setSelection(passwordEditText.text.length)
            isPasswordVisible = !isPasswordVisible
        }
    }
    private fun validateMobileNumber(mobileNumber: String): Boolean {
        return when {
            mobileNumber.isEmpty() -> {
                mobileNumberEditText.error = "Please enter mobile number"
                false
            }
            mobileNumber.length != 10 -> {
                mobileNumberEditText.error = "Mobile number must be 10 digits"
                false
            }
            else -> true
        }
    }
    private fun validatePassword(password: String): Boolean {
        return when {
            password.isEmpty() -> {
                passwordEditText.error = "Please enter password"
                false
            }
            password != dummyPassword -> {
                passwordEditText.error = "Enter correct password"
                false
            }
            else -> true
        }
    }
    private fun setupLoaderDialog() {
        loaderDialog = Dialog(this)
        loaderDialog.setContentView(R.layout.loder)
        loaderDialog.setCancelable(false)
        loaderDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
    private fun showLoader() {
        loaderDialog.show()
    }
    private fun hideLoader() {
        loaderDialog.dismiss()
    }
}
