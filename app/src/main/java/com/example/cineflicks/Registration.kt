package com.example.cineflicks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registration.*


class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        register.setOnClickListener{
            val hasError = isFormValid()
            if (hasError == false){
                val name = nameRegister.text.toString()
                val password = passwordRegister.text.toString()
                val email = emailRegister.text.toString()
                val adminNum = adminNumRegister.text.toString()
                val pemGrp = pemgrpRegister.text.toString()

                val message:ArrayList<String> = arrayListOf(
                    "Login Name: ${name}",
                    "Password: ${password}",
                    "Email: ${email}",
                    "Admin Number: ${adminNum}",
                    "Pem Group: ${pemGrp}"
                )

                displayToast(message.joinToString("\n"))

                Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
                    override fun run() {
                        val intent = Intent(this@Registration,Verification::class.java)
                        startActivity(intent)
                    }
                },2000)


            }
        }
    }

    private fun isFormValid(): Boolean {
        var isInvalid = false

        if (nameRegister.text.isEmpty() ||
            passwordRegister.text.isEmpty() ||
            emailRegister.text.isEmpty() ||
            adminNumRegister.text.isEmpty() ||
            pemgrpRegister.text.isEmpty()
        ) {
            isInvalid = true
            displayToast("All fields must be filled.")
        }

        return isInvalid
    }

    private fun displayToast(message:String){
        Toast.makeText(this@Registration,message, Toast.LENGTH_LONG).show()
    }
}