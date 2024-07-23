package com.example.midtermq1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Navigation : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val openCalculatorButton: Button = findViewById(R.id.Button1)
        val openMessageButton: Button = findViewById(R.id.Button2)

        openCalculatorButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        openMessageButton.setOnClickListener {
            val message = "Hello from MainActivity!"
            val intent = Intent(this, MessageDisplay::class.java).apply {
                putExtra("MESSAGE", message)
            }
            startActivity(intent)
        }
    }
}
