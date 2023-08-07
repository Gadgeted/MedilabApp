package com.collins.medilabsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView

class Screen3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen3)

        // Find View by ID
        val skip3 = findViewById<MaterialTextView>(R.id.skip3)
        skip3.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }//End

        val fab3 = findViewById<FloatingActionButton>(R.id.fab3)
        fab3.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }//End
    }
}