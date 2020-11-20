package com.example.aviationfuelcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // kilogram calculation mode or liters calculation mode
        var calcKilogramsMode = false
        var calcLitersMode = false


        val densityText = findViewById<EditText>(R.id.editTextNumberDecimalDensity)
        val litersText = findViewById<EditText>(R.id.editTextNumberLiters)
        val kilogramsText = findViewById<EditText>(R.id.editTextNumberKgs)

        // setting mode of calculation: liters to kgs or kgs to liters
        // fun setMode(){
        //
        // }

        fun calculate() {

            val editDensity = findViewById<EditText>(R.id.editTextNumberDecimalDensity)
            val editKilograms = findViewById<EditText>(R.id.editTextNumberKgs)
            val editLiters = findViewById<EditText>(R.id.editTextNumberLiters)

            val fuelDensityString = editDensity.text.toString()
            val kilogramsString = editKilograms.text.toString()
            val litersString = editLiters.text.toString()

            // Convert value to a number
            var density = fuelDensityString.toFloatOrNull()
            var kgs = kilogramsString.toIntOrNull()
            var liters = litersString.toIntOrNull()

            val myToast = Toast.makeText(applicationContext, "in calcKilogramsMode: " + calcKilogramsMode + " in calcLitersMode:" + calcLitersMode, Toast.LENGTH_SHORT)
            myToast.show()
            if (calcKilogramsMode) {
                //  val myToast = Toast.makeText(applicationContext , "in calcKilogramsMode", Toast.LENGTH_SHORT)
                //  myToast.show()
                val k = (liters!! * density!!).roundToInt()
                editKilograms.setText(k.toString())

            } else if (calcLitersMode) {

                // val myToast = Toast.makeText(applicationContext , "in calcLitersMode", Toast.LENGTH_SHORT)
                // myToast.show()
                val l = (kgs!! / density!!).roundToInt()
                editLiters.setText(l.toString())

            }
        }

        densityText.addTextChangedListener {
            calculate()
        }

        litersText.addTextChangedListener {
            // only if textedit has focus, activate mode
            // do not activate mode if text was changed by program
            if (litersText.hasFocus()) {
                calcKilogramsMode = true
                calcLitersMode    = false
                // even if we have just set mode, remove it,
                // as it is impossible to follow because text is empty
                if (litersText.text.isEmpty()) {
                    calcKilogramsMode = false
                }
            }
        }


        kilogramsText.addTextChangedListener {
            // only if textedit has focus, activate mode
            // do not activate mode if text was changed by program
            if (kilogramsText.hasFocus()) {
                calcLitersMode    = true
                calcKilogramsMode = false
                // even if we have just set mode, remove it,
                // as it is impossible to follow because text is empty
                if (kilogramsText.text.isEmpty()) {
                    calcLitersMode = false
                }
            }
        }
    }
}

