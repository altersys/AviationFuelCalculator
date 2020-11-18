package com.example.aviationfuelcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

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
            val myToast = Toast.makeText(applicationContext , fuelDensityString, Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()

            // if (a > b) {
            //    max = a
            //} else {
            //    max = b
            //}

            // if kilograms   is null and liters is not null        : kgs = liters/density
            if (kgs == null && liters != null) {
                val k = liters*density!!
                editKilograms.setText(k.toString())
            }
            // else if liters is null and kilograms is not null     : liters = kgs*density
            // else if liters is not null and kilograms is not null : kgs = liters/density

            // editKilograms.setText(fuelDensityString)
        }
    }




}