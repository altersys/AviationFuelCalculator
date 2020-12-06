package com.example.aviationfuelcalculator

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlin.math.roundToInt

class FuelCalculator(calcKilogramsMode: Boolean, calcLitersMode: Boolean,fuelDensityString: String,kilogramsString: String,litersString: String) {

    // Convert value to a number
    val density = fuelDensityString.toFloatOrNull()
    val kgs = kilogramsString.toIntOrNull()
    val liters = litersString.toIntOrNull()
    var k = kgs
    var l = liters


    init {
        Log.d("FuelCalc", "initializing with: $calcKilogramsMode, $calcLitersMode,$fuelDensityString,$kilogramsString,$litersString")
        calculate(calcKilogramsMode,calcLitersMode)
    }

    private fun calculate(calcKilogramsMode: Boolean, calcLitersMode: Boolean) {
        Log.d("calculationMode","Invoking calculate. calcKilogramsMode: $calcKilogramsMode, calcLitersMode: $calcLitersMode")

        if (calcKilogramsMode == calcLitersMode) {
            Log.d("invalidData", "Invalid data: calcKilogramsMode == calcLitersMode")
            return
        }

        if (density == null || density <= 0.0) {
            Log.d("invalidData", "Invalid data: density == null || density <= 0.0")
            return
        }

        if (kgs == null && liters == null) {
            Log.d("invalidData", "Invalid data: kgs == null && liters == null")
            return
        }

        if (calcKilogramsMode) {
            if (liters == null) {
                Log.d("invalidData", "Invalid data: liters == null")
                return
            } else {
                Log.d("okData", "Data entered is ok")
                k = (liters * density).roundToInt()
                //kilogramsText.setText(k.toString())
                return
            }
        }

        if (calcLitersMode) {
            if (kgs == null) {
                Log.d("invalidData", "Invalid data: kgs == null")
                return
            } else {
                Log.d("okData", "Data entered is ok")
                l = (kgs / density).roundToInt()
                //litersText.setText(l.toString())
                return
            }
        }
        Log.d("end", "End of calculate")
    }

}




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
            calculate(calcKilogramsMode,
                    calcLitersMode,
                    densityText,
                    kilogramsText,
                    litersText)
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
                calculate(calcKilogramsMode,
                        calcLitersMode,
                        densityText,
                        kilogramsText,
                        litersText)
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
                calculate(calcKilogramsMode,
                        calcLitersMode,
                        densityText,
                        kilogramsText,
                        litersText)
            }
        }
    }
    // invokes FuelCalculator class to make calculations
    // changes text in UI elements
    private fun calculate(calcKilogramsMode: Boolean,
                          calcLitersMode: Boolean,
                          densityText: EditText,
                          kilogramsText: EditText,
                          litersText: EditText) {
        var fuelDensityString = densityText.text.toString()
        var kilogramsString = kilogramsText.text.toString()
        var litersString = litersText.text.toString()

        var fuelCalc =  FuelCalculator(
                calcKilogramsMode,
                calcLitersMode,
                fuelDensityString,
                kilogramsString,
                litersString)
        if (calcKilogramsMode) {
            Log.d("calcMode", "Returning k")
            kilogramsText.setText(fuelCalc.k.toString())
            return
        }
        if (calcLitersMode) {
            Log.d("calcMode", "Returning l")
            litersText.setText(fuelCalc.l.toString())
            return
        }
        Log.d("calcMode", "No mode provided")
        return
    }
}


