Mid Term 1

Name : Nirav Jivani
ID : 1221633


Q1) and Q2) and Q3)

activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/textInputLayout"
        android:layout_width="268dp"
        android:layout_height="59dp"
        android:hint="Number 1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.489"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.107" />

    <EditText
        android:id="@+id/textInputLayout2"
        android:layout_width="268dp"
        android:layout_height="59dp"
        android:hint="Number 2"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.496"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textInputLayout"
        app:layout_constraintVertical_bias="0.157" />

    <Spinner
        android:id="@+id/spinner"
        android:layout_width="269dp"
        android:layout_height="59dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.492"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textInputLayout2"
        app:layout_constraintVertical_bias="0.236" />

    <FrameLayout
        android:id="@+id/FLfragment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@id/textInputLayout2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/textInputLayout"
        app:layout_constraintVertical_bias="0.5" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Select Opration:"
        app:layout_constraintBottom_toTopOf="@+id/spinner"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.224"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textInputLayout2"
        app:layout_constraintVertical_bias="0.622" />

    <Button
        android:id="@+id/button"
        android:layout_width="268dp"
        android:layout_height="59dp"
        android:text="Calculate"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.489"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/spinner"
        app:layout_constraintVertical_bias="0.23" />

    <TextView
        android:id="@+id/textView2"
        android:layout_width="268dp"
        android:layout_height="59dp"
        android:text="Result : "
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.489"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/button"
        app:layout_constraintVertical_bias="0.315" />

</androidx.constraintlayout.widget.ConstraintLayout>



Main_Activity.kt

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





Screen Shots:
























































































































