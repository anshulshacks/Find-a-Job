package com.helloo.findajob

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class User : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val db = Firebase.firestore
        val currentUser = FirebaseAuth.getInstance().currentUser
        val loginBtn = findViewById<Button>(R.id.loginPage)
        val signout = findViewById<Button>(R.id.signOut)



        val add = findViewById<ImageView>(R.id.add6)

        add.setOnClickListener {
            startActivity(Intent(this, Add::class.java))
        }

        val jobs = findViewById<ImageView>(R.id.list6)

        jobs.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        if (currentUser == null) {
            signout.isEnabled = false
        }
        else {
            loginBtn.isEnabled = false

        }

        loginBtn.setOnClickListener {
            startActivity(Intent(this, Login ::class.java))
        }



        signout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show()
        }





    }
}