package com.helloo.findajob

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Firebase.firestore


        val jobList = findViewById<TextView>(R.id.jobList)
        var jobString = ""
        db.collection("jobs")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    jobString += "\n\nTitle: ${document.data["title"]}\nDescription: ${document.data["description"]}\nRate: ${document.data["rate"]}\nContact: ${document.data["contact"]}\nDate added: ${document.data["date"]}\n"
                }
                jobList.text = jobString
            }
            .addOnFailureListener { e ->
                Log.d("error", e.toString())
            }

        val add = findViewById<ImageView>(R.id.add)


        add.setOnClickListener {
//            db.collection("jobs")
//                .add(report)
//                .addOnSuccessListener { documentReference ->
//                    Log.d("db", "DocumentSnapshot added with ID: ${documentReference.id}")
//                    Toast.makeText(this, "Your report has been recorded", Toast.LENGTH_LONG).show()
//                }
//                .addOnFailureListener { e ->
//                    Log.w("db", "Error adding document", e)
//                }
            startActivity(Intent(this, Add::class.java))
        }

        val user = findViewById<ImageView>(R.id.user)

        user.setOnClickListener {
            startActivity(Intent(this, User::class.java))
        }

    }
}