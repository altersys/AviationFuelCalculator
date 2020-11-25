package com.example.aviationfuelcalculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlin.math.roundToInt

// app is crashing when trying to input , into int fields
// how to get fucking keyboard forced on
// change color of app main bar
class MainActivity : AppCompatActivity() {

    private lateinit var densityText: EditText
    private lateinit var litersText: EditText
    private lateinit var kilogramsText: EditText
    private lateinit var errorText: View

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // kilogram calculation mode or liters calculation mode
        var calcKilogramsMode = false
        var calcLitersMode = false


        densityText = findViewById(R.id.editTextNumberDecimalDensity)
        litersText = findViewById(R.id.editTextNumberLiters)
        kilogramsText = findViewById(R.id.editTextNumberKgs)
        errorText = findViewById(R.id.errorTextView)

        densityText.addTextChangedListener {
            calculate(calcKilogramsMode, calcLitersMode)
        }

        litersText.addTextChangedListener {
            // only if textedit has focus, activate mode
            // do not activate mode if text was changed by program
            if (litersText.hasFocus()) {
                calcKilogramsMode = true
                calcLitersMode = false
                // even if we have just set mode, remove it,
                // as it is impossible to follow because text is empty
                if (litersText.text.isEmpty()) {
                    calcKilogramsMode = false
                }
                // try to calculate anyway even if liters are empty
                calculate(calcKilogramsMode, calcLitersMode)
            }
        }


        kilogramsText.addTextChangedListener {
            // only if textedit has focus, activate mode
            // do not activate mode if text was changed by program
            if (kilogramsText.hasFocus()) {
                calcLitersMode = true
                calcKilogramsMode = false
                // even if we have just set mode, remove it,
                // as it is impossible to follow because text is empty
                if (kilogramsText.text.isEmpty()) {
                    calcLitersMode = false
                }
                // try to calculate anyway even if kilograms are empty
                calculate(calcKilogramsMode, calcLitersMode)
            }

        }
    }

    fun calculate(calcKilogramsMode: Boolean, calcLitersMode: Boolean) {

        val fuelDensityString = densityText.text.toString()
        val kilogramsString = kilogramsText.text.toString()
        val litersString = litersText.text.toString()

        // Convert value to a number
        val density = fuelDensityString.toFloatOrNull()
        val kgs = kilogramsString.toIntOrNull()
        val liters = litersString.toIntOrNull()
        if (density == null || density <= 0.0) {
            errorText.visibility = View.VISIBLE
            return
        }

        if (kgs == null && liters == null) {
            errorText.visibility = View.VISIBLE
            return
        }

        errorText.visibility = View.GONE

        Log.d("calculationMode", "in calcKilogramsMode: $calcKilogramsMode in calcLitersMode: $calcLitersMode")

        if (calcKilogramsMode) {
            //  val myToast = Toast.makeText(applicationContext , "in calcKilogramsMode", Toast.LENGTH_SHORT)
            //  myToast.show()
            val k = (liters!! * density).roundToInt()
            kilogramsText.setText(k.toString())

        } else if (calcLitersMode) {

            // val myToast = Toast.makeText(applicationContext , "in calcLitersMode", Toast.LENGTH_SHORT)
            // myToast.show()
            val l = (kgs!! / density).roundToInt()
            litersText.setText(l.toString())
        }
    }


}

