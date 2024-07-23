package com.example.midtermq1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MessageDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_message_display)

            val messageTextView : TextView = findViewById(R.id.messageTextView)
            val message = intent.getStringExtra("MESSAGE") ?: "No message received"
            messageTextView.text = message
    }
}