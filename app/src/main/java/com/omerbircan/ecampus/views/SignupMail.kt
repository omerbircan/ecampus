package com.omerbircan.ecampus.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.omerbircan.ecampus.databinding.ActivitySignupMailBinding

class SignupMail : AppCompatActivity() {

    private lateinit var binding: ActivitySignupMailBinding

    val collectionRef = FirebaseFirestore.getInstance().collection("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupMailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)




    }

    fun nextclick(view : View){
        val email = binding.EmailText.text.toString()
        val query = collectionRef.whereEqualTo("mail", email)

        query.get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.isEmpty) {
                    Toast.makeText(this, "Not Permitted", Toast.LENGTH_SHORT).show()
                } else {
                    val document = querySnapshot.documents[0]
                    val signed = document.getBoolean("signed") ?: false

                    if (!signed) {
                        // Önceden kayıtlı kullanıcı
                        Toast.makeText(this, "Zaten Kayıtlısınız", Toast.LENGTH_SHORT).show()
                    } else {
                        // Eşleşme var ve kayıt olabilir
                        val intent = Intent(this@SignupMail, SignupPassword::class.java)
                        intent.putExtra("mail", email)
                        startActivity(intent)
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }
