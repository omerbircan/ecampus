package com.omerbircan.ecampus.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.omerbircan.ecampus.databinding.ActivityNewPostBinding

class NewPost : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        username = intent.getStringExtra("username")
    }


    fun submitClick(view: View) {
        val content = binding.postText.text.toString()
        postUpload(username!!, content)

    }

        fun postUpload(username: String, content: String) {

            val db = FirebaseFirestore.getInstance()
            val postsCollectionRef = db.collection("posts")

            val data = hashMapOf(
                "likes" to 0,
                "username" to username,
                "content" to content,
                "timestamp" to FieldValue.serverTimestamp()
            )

            val postDocumentRef = postsCollectionRef.document()
            postDocumentRef.set(data)
                .addOnSuccessListener {

                    val intent = Intent(this@NewPost, HomePage::class.java)
                    intent.putExtra("username", username)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {

                    Toast.makeText(this, "gönderi oluşturulamadı", Toast.LENGTH_SHORT).show()
                }


        }
    }
