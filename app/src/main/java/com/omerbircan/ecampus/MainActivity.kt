package com.omerbircan.ecampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omerbircan.ecampus.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth
    }

    fun signupclick(view : View){

        val intent = Intent(applicationContext, SignupMail::class.java)
        startActivity(intent)
    }

    fun loginclick(view : View){

        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        
        if(email=="" || password == ""){
            Toast.makeText( this,"enter username and password", Toast.LENGTH_LONG).show()
        }
        else{
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                val intent = Intent(this@MainActivity, HomePage::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {

                Toast.makeText( this@MainActivity,it.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }


    }
}