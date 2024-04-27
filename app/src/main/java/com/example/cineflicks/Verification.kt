package com.example.cineflicks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_verification.*

class Verification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        verifyButton.setOnClickListener {
            val hasError = validateInput()
            if (!hasError) {
                if (verifyButton.text.toString() == "1234") {
                    showToast("Code Verified")
                    launchLoginActivity()
                } else {
                    showToast("Code Error")
                }
            }
        }
    }

    private fun validateInput(): Boolean {
        var hasError = false

        if (verificationCodeET.text.isEmpty()) {
            verificationCodeET.error = "This field cannot be empty"
            hasError = true
        }

        return hasError
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun launchLoginActivity() {
        val loginIntent = Intent(this, Login::class.java)
        startActivity(loginIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val registrationIntent = Intent(this, Registration::class.java)
        startActivity(registrationIntent)
        return super.onSupportNavigateUp()
    }
}
