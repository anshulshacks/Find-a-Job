package com.helloo.findajob

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Add : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val db = Firebase.firestore

        val currentUser = FirebaseAuth.getInstance().currentUser
        var title = findViewById<EditText>(R.id.title)
        var description = findViewById<EditText>(R.id.description)
        var rate = findViewById<EditText>(R.id.rate)
        var contact = findViewById<EditText>(R.id.contact)
        val postBtn = findViewById<Button>(R.id.post)

        if (currentUser == null) {
            title.isFocusable = false
            description.isFocusable = false
            rate.isFocusable = false
            contact.isFocusable = false
            title.setOnClickListener {
                Toast.makeText(this, "You must be logged in to create a job listing", Toast.LENGTH_SHORT).show()
            }

            description.setOnClickListener {
                Toast.makeText(this, "You must be logged in to create a job listing", Toast.LENGTH_SHORT).show()
            }

            rate.setOnClickListener {
                Toast.makeText(this, "You must be logged in to create a job listing", Toast.LENGTH_SHORT).show()
            }

            contact.setOnClickListener {
                Toast.makeText(this, "You must be logged in to create a job listing", Toast.LENGTH_SHORT).show()
            }
            postBtn.isEnabled = false
        }

        val user = findViewById<ImageView>(R.id.user2)

        user.setOnClickListener {
            startActivity(Intent(this, User::class.java))
        }

        val list = findViewById<ImageView>(R.id.list2)

        list.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }




        postBtn.setOnClickListener {
            val currentTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
            val formattedTime = currentTime.format(formatter)
            val job = hashMapOf(
                "title" to title.text.toString(),
                "description" to description.text.toString(),
                "rate" to rate.text.toString(),
                "contact" to contact.text.toString(),
                "date" to formattedTime,
                "uuid" to currentUser?.uid
            )
            db.collection("jobs")
                .add(job)
                .addOnSuccessListener { documentReference ->
                    Log.d("firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
                    Toast.makeText(this, "Your job listing has been recorded", Toast.LENGTH_LONG).show()
                    title.text.clear()
                    description.text.clear()
                    rate.text.clear()
                    contact.text.clear()


                }
                .addOnFailureListener { e ->
                    Log.w("firestore", "Error adding document", e)
                }
        }

    }
}