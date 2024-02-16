package com.example.bmi_xml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextWeight: EditText
    private lateinit var editTextHeight: EditText
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextWeight = findViewById(R.id.editTextWeight)
        editTextHeight = findViewById(R.id.editTextHeight)
        textViewResult = findViewById(R.id.textViewResult)

        val buttonStart: Button = findViewById(R.id.button)
        buttonStart.setOnClickListener {
            calculateBMI()
        }

        val buttonReset: Button = findViewById(R.id.button2)
        buttonReset.setOnClickListener {
            resetInputs()
        }
    }

    private fun calculateBMI() {
        val weight = editTextWeight.text.toString().toDoubleOrNull()
        val height = editTextHeight.text.toString().toDoubleOrNull()

        if (weight != null && height != null) {
            val heightInMeters = height / 100 // Convert height from cm to meters
            val bmi = weight / (heightInMeters * heightInMeters)
            val formattedBMI = String.format("%.2f", bmi)
            var bmiStatus: String

            when {
                bmi < 18.5 -> bmiStatus = "You are considered underweight and possibly malnourished"
                bmi in 18.5..24.9 -> bmiStatus = "You are within a healthy weight range for young and middle-aged adults"
                bmi in 25.0..29.9 -> bmiStatus = "You are considered overweight"
                else -> bmiStatus = "You are considered obese"
            }

            textViewResult.text = "Your BMI is: $formattedBMI\n\n$bmiStatus"
        } else {
            textViewResult.text = "Please enter valid weight and height values."
        }
    }

    private fun resetInputs() {
        editTextWeight.setText("")
        editTextHeight.setText("")
        textViewResult.text = ""
    }
}