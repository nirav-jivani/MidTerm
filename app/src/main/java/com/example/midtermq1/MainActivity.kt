package com.example.midtermq1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner: Spinner = findViewById(R.id.spinner)
        val options = arrayOf("Addition", "Subtraction", "Multiplication", "Division")
        var flag = "Addition"
        val AddtionFragment = AddtionFragment()
        val SubtractionFragment = SubtractionFragment()
        val DivisionFragment = DivisionFragment()
        val MultiplicationFragment = MultiplicationFragment()
        val button: Button = findViewById(R.id.button)
        val input1: EditText = findViewById(R.id.textInputLayout)
        val input2: EditText = findViewById(R.id.textInputLayout2)
        val res: TextView = findViewById(R.id.textView2)

        button.setOnClickListener {
            val input1Text = input1.text.toString()
            val input2Text = input2.text.toString()
            if (input1Text.isBlank() || input2Text.isBlank()) {
                res.text = "Result: Invalid input!"
            } else {
                    val x1 = input1Text.toDouble()
                    val x2 = input2Text.toDouble()
                    val ans = when (flag) {
                        "Addition" -> x1 + x2
                        "Subtraction" -> x1 - x2
                        "Multiplication" -> x1 * x2
                        "Division" -> if (x2 != 0.0) x1 / x2 else Double.NaN
                        else -> Double.NaN
                    }
                val message = "Result: $ans"
                val intent = Intent(this, MessageDisplay::class.java).apply {
                    putExtra("MESSAGE", message)
                }
                startActivity(intent)
                    res.text = "Result: $ans"
            }
        }

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                flag = options[position]
                val ans : Fragment = when (flag) {
                    "Addition" -> AddtionFragment
                    "Subtraction" -> SubtractionFragment
                    "Multiplication" -> MultiplicationFragment
                    else ->DivisionFragment
                }
                supportFragmentManager.beginTransaction().replace(R.id.FLfragment,ans).commit()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
}
