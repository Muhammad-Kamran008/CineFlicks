package com.example.cineflicks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent

import android.widget.Toast
import com.example.popularmovies.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn.setOnClickListener {
            val hasError = isLoginFormInvalid()
            if (hasError == false) {
                if (nameLogin.text.toString() == "testuser" && passwordLogin.text.toString() == "testuser") {
                    val intent = Intent(this@Login, SimpleViewListOfMoviesActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "Login Unsuccessful", Toast.LENGTH_LONG).show()
                }
            } else {
                return@setOnClickListener
            }
        }
        registerBtn.setOnClickListener {
            val intent = Intent(this@Login, Registration::class.java)
            startActivity(intent)
        }
    }

    private fun isLoginFormInvalid(): Boolean {
        var hasError = false

        if (nameLogin.text.isEmpty() || passwordLogin.text.isEmpty()) {
            if (nameLogin.text.isEmpty()) {
                nameLogin.error = "Login cannot be empty"
            }
            if (passwordLogin.text.isEmpty()) {
                passwordLogin.error = "Password cannot be empty"
            }
            hasError = true
        }

        return hasError
    }


}