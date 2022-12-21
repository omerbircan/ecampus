package com.omerbircan.ecampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.omerbircan.ecampus.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun signupclick(view : View){

        val intent = Intent(applicationContext, SigninMail::class.java)
        startActivity(intent)
    }

    fun loginclick(view : View){

        val intent = Intent(applicationContext, HomePage::class.java)
        startActivity(intent)


    }
}