package com.omerbircan.ecampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.omerbircan.ecampus.databinding.ActivityMainBinding
import com.omerbircan.ecampus.databinding.ActivitySignupMailBinding

class SignupMail : AppCompatActivity() {

    private lateinit var binding: ActivitySignupMailBinding

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupMailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }

    fun nextclick(view : View){
        val email = binding.EmailText.toString()
        var ref = db.collection("users")
        val cansign = ref.whereEqualTo("mail", email).whereEqualTo("signed",false)

        if(email.isEmpty()){
            Toast.makeText( this,"enter mail", Toast.LENGTH_LONG).show()
        }
        else{
            if(cansign != null){
                        val intent = Intent(this@SignupMail, SignupPassword::class.java)
                        startActivity(intent)
                    }

                }
        }
    }
