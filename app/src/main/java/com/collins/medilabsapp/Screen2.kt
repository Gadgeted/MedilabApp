package com.collins.medilabsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView

class Screen2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2)

        // Find View by ID
        val skip2 = findViewById<MaterialTextView>(R.id.skip2)
        skip2.setOnClickListener {
            startActivity(Intent(applicationContext, SignInActivity
            ::class.java))
        }//End

        val fab2 = findViewById<FloatingActionButton>(R.id.fab2)
        fab2.setOnClickListener {
            startActivity(Intent(applicationContext, Screen3::class.java))
        }//End
    }
}