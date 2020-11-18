package com.example.aviationfuelcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.editTextNumberDecimalDensity).addTextChangedListener {
            // create a Toast with some text, to appear for a short time
            val myToast = Toast.makeText(applicationContext , "Hello Toast!", Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()
        }
    }


}