package com.omerbircan.ecampus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.omerbircan.ecampus.databinding.ActivitySignupPasswordBinding

class SignupPassword : AppCompatActivity() {

    private lateinit var binding: ActivitySignupPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
    fun finishclick(view: View){
        val intent = Intent(this@SignupPassword, HomePage::class.java)
        startActivity(intent)

    }
}