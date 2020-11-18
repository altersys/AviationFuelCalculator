package com.example.aviationfuelcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.editTextNumberDecimalDensity).addTextChangedListener {
            val editDensity   = findViewById<EditText>(R.id.editTextNumberDecimalDensity)
            val editKilograms = findViewById<EditText>(R.id.editTextNumberKgs)
            val editLiters    = findViewById<EditText>(R.id.editTextNumberLiters)

            val fuelDensityString   = editDensity.text.toString()
            val kilogramsString     = editKilograms.text.toString()
            val litersString        = editLiters.text.toString()

            // Convert value to a number
            var density = fuelDensityString.toFloatOrNull()
            var kgs     = kilogramsString.toIntOrNull()
            var liters  = litersString.toIntOrNull()

            // create a Toast with some text, to appear for a short time
            //val myToast = Toast.makeText(applicationContext , fuelDensityString, Toast.LENGTH_SHORT)
            //myToast.show()

            // if (age < 10) {
            //    println("You're too young to watch this movie")
            // } else if (age < 13) {
            //    println("You can watch this movie with a parent")
            // }  else {
            //    println("You can watch this movie")
            // }

            // if kilograms   is null and liters is not null        : kgs = liters/density
            if (kgs == null && liters != null) {
                val myToast = Toast.makeText(applicationContext , "kgs == null && liters != null", Toast.LENGTH_SHORT)
                myToast.show()
                val k = (liters*density!!).roundToInt()
                editKilograms.setText(k.toString())

            } else if (liters == null && kgs != null) {
                // else if liters is null and kilograms is not null : liters = kgs*density
                val myToast = Toast.makeText(applicationContext , "liters == null && kgs != null", Toast.LENGTH_SHORT)
                myToast.show()
                val l = (kgs/density!!).roundToInt()
                editLiters.setText(l.toString())

            }
            // else if liters is not null and kilograms is not null : kgs = liters/density

            // editKilograms.setText(fuelDensityString)
        }
    }




}