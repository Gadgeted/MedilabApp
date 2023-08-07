package com.collins.medilabsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView

class Screen_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen1)

        // Find View by ID
        val skip1 = findViewById<MaterialTextView>(R.id.skip1)
        skip1.setOnClickListener {
            startActivity(Intent(applicationContext, MemberProfile::class.java))
        }//End

        val fab1 = findViewById<FloatingActionButton>(R.id.fab1)
        fab1.setOnClickListener {
            startActivity(Intent(applicationContext, Screen2::class.java))
        }//End




    }
}