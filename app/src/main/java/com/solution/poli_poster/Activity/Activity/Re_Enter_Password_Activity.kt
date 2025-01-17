package com.solution.poli_poster.Activity.Activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.solution.poli_poster.R

class Re_Enter_Password_Activity : AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private lateinit var enterPasswordEditText: EditText
    private lateinit var strengthMessageTextView: TextView
    private lateinit var submitButton: Button
    private lateinit var passwordMismatchMessageTextView: TextView

    private lateinit var criteriaCheckLayout: LinearLayout
    private lateinit var lowercaseCheck: ImageView
    private lateinit var uppercaseCheck: ImageView
    private lateinit var digitCheck: ImageView
    private lateinit var specialCharCheck: ImageView

    private lateinit var togglePasswordVisibilityEnter: ImageView
    private lateinit var togglePasswordVisibilityReEnter: ImageView
    private var isEnterPasswordVisible = false
    private var isReEnterPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_re_enter_password)

        passwordEditText = findViewById(R.id.re_passwordEditText)
        enterPasswordEditText = findViewById(R.id.enterpassword)
        strengthMessageTextView = findViewById(R.id.strengthMessageTextView)
        submitButton = findViewById(R.id.submit)
        passwordMismatchMessageTextView = findViewById(R.id.passwordMismatchMessageTextView)

        criteriaCheckLayout = findViewById(R.id.criteriaCheckLayout)
        lowercaseCheck = findViewById(R.id.lowercaseCheck)
        uppercaseCheck = findViewById(R.id.uppercaseCheck)
        digitCheck = findViewById(R.id.digitCheck)
        specialCharCheck = findViewById(R.id.specialCharCheck)

        togglePasswordVisibilityEnter = findViewById(R.id.togglePasswdVisibility)
        togglePasswordVisibilityReEnter = findViewById(R.id.togglePasswordVisibility)

        // Password visibility toggle for "Enter Password" field
        togglePasswordVisibilityEnter.setOnClickListener {
            if (isEnterPasswordVisible) {
                enterPasswordEditText.transformationMethod =
                    android.text.method.PasswordTransformationMethod.getInstance()
                togglePasswordVisibilityEnter.setImageResource(R.drawable.closecyes) // Use your close icon here
            } else {
                enterPasswordEditText.transformationMethod =
                    android.text.method.HideReturnsTransformationMethod.getInstance()
                togglePasswordVisibilityEnter.setImageResource(R.drawable.open_eye) // Use your open eye icon here
            }
            enterPasswordEditText.setSelection(enterPasswordEditText.text.length)
            isEnterPasswordVisible = !isEnterPasswordVisible
        }

        // Password visibility toggle for "Re-enter Password" field
        togglePasswordVisibilityReEnter.setOnClickListener {
            if (isReEnterPasswordVisible) {
                passwordEditText.transformationMethod =
                    android.text.method.PasswordTransformationMethod.getInstance()
                togglePasswordVisibilityReEnter.setImageResource(R.drawable.closecyes) // Use your close icon here
            } else {
                passwordEditText.transformationMethod =
                    android.text.method.HideReturnsTransformationMethod.getInstance()
                togglePasswordVisibilityReEnter.setImageResource(R.drawable.open_eye) // Use your open eye icon here
            }
            passwordEditText.setSelection(passwordEditText.text.length)
            isReEnterPasswordVisible = !isReEnterPasswordVisible
        }

        enterPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()

                // Show criteria layout when typing starts
                if (password.isNotEmpty()) {
                    criteriaCheckLayout.visibility = View.VISIBLE
                } else {
                    criteriaCheckLayout.visibility = View.GONE
                }

                evaluatePasswordCriteria(password)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        // Submit button click listener
        submitButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val reEnteredPassword = enterPasswordEditText.text.toString()

            // Check if both password fields are empty
            if (password.isEmpty() && reEnteredPassword.isEmpty()) {
                passwordEditText.error = "Please enter your password"
                enterPasswordEditText.error = "Please re-enter your password"
                return@setOnClickListener
            }

            // Check if the "Enter Password" field is empty
            if (password.isEmpty()) {
                passwordEditText.error = "Please enter your password"
                return@setOnClickListener
            }

            // Check if the "Re-enter Password" field is empty
            if (reEnteredPassword.isEmpty()) {
                enterPasswordEditText.error = "Please re-enter your password"
                return@setOnClickListener
            }

            // Check if passwords match
            if (password != reEnteredPassword) {
                // Show error message if passwords don't match
                passwordMismatchMessageTextView.text = "Passwords don't match"
                passwordMismatchMessageTextView.setTextColor(resources.getColor(R.color.red))

                // Clear both password fields
                passwordEditText.text.clear()
                enterPasswordEditText.text.clear()
            } else {
                // Show success popup if passwords match
                showSuccessPopup()
            }
        }
    }


        private fun evaluatePasswordCriteria(password: String) {
            // Check for lowercase letters
            val hasLowercase = password.contains(Regex("[a-z]"))
            lowercaseCheck.setImageResource(if (hasLowercase) R.drawable.baseline_check_24 else R.drawable.baseline_disabled_by_default_24)

            // Check for uppercase letters
            val hasUppercase = password.contains(Regex("[A-Z]"))
            uppercaseCheck.setImageResource(if (hasUppercase) R.drawable.baseline_check_24 else R.drawable.baseline_disabled_by_default_24)

            // Check for digits
            val hasDigit = password.contains(Regex("[0-9]"))
            digitCheck.setImageResource(if (hasDigit) R.drawable.baseline_check_24 else R.drawable.baseline_disabled_by_default_24)

            // Check for special characters
            val hasSpecialChar = password.contains(Regex("[^A-Za-z0-9]"))
            specialCharCheck.setImageResource(if (hasSpecialChar) R.drawable.baseline_check_24 else R.drawable.baseline_disabled_by_default_24)

            // Evaluate password strength
            if (password.length >= 8 && hasLowercase && hasUppercase && hasDigit && hasSpecialChar) {
                strengthMessageTextView.text = "Strong Password"
                strengthMessageTextView.setTextColor(resources.getColor(R.color.green))
            } else if (password.length >= 6) {
                strengthMessageTextView.text = "Weak Password"
                strengthMessageTextView.setTextColor(resources.getColor(R.color.red))
            } else {
                // Show message if password doesn't meet the minimum criteria (6 digits)
                strengthMessageTextView.text = "Password minimum 6 digits"
                strengthMessageTextView.setTextColor(resources.getColor(R.color.yellow))
            }

            // Hide criteria layout if all conditions are satisfied
            if (hasLowercase && hasUppercase && hasDigit && hasSpecialChar) {
                criteriaCheckLayout.visibility = View.GONE
            }
        }

        fun showSuccessPopup() {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.popup_layout) // Replace with your popup layout resource
            dialog.setCancelable(false)

            val gotItButton =
                dialog.findViewById<Button>(R.id.gotItButton) // Replace with the correct button ID
            gotItButton.setOnClickListener {
                dialog.dismiss()

                // Navigate to the LoginActivity
                val intent = Intent(this, Login_Activity::class.java)
                startActivity(intent)
                finish() // Optional: finish the current activity
            }

            dialog.show()
        }
    }


