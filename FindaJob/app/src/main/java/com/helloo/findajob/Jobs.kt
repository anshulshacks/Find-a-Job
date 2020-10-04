package com.helloo.findajob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Jobs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs)
        val user = findViewById<ImageView>(R.id.user3)

        user.setOnClickListener {
            startActivity(Intent(this, User::class.java))
        }

        val add = findViewById<ImageView>(R.id.add3)

        add.setOnClickListener {
            startActivity(Intent(this, Add::class.java))
        }
    }
}