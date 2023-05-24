package com.omerbircan.ecampus.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.omerbircan.ecampus.databinding.ActivitySignupPasswordBinding

class SignupPassword : AppCompatActivity() {

    private var mail: String? = null
    private lateinit var binding: ActivitySignupPasswordBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mail = intent.getStringExtra("mail")
    }
    fun next(view: View) {
        val password1 = binding.PasswordText1.text.toString()
        val password2 = binding.PasswordText2.text.toString()

        if (password1.isEmpty() || password2.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
        } else if (password1.length < 8 || password2.length < 8) {
            Toast.makeText(this, "Şifreler en az 8 karakter olmalıdır", Toast.LENGTH_SHORT).show()
        } else if (password1 != password2) {
            Toast.makeText(this, "Şifreler eşleşmiyor", Toast.LENGTH_SHORT).show()
        } else {
            // Şartlar sağlandı, sonraki sayfaya geçiş yap
            val intent = Intent(this@SignupPassword, SignupUsername::class.java)
            intent.putExtra("mail", mail)
            intent.putExtra("password", password1)
            startActivity(intent)
        }
    }

}