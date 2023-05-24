package com.omerbircan.ecampus.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.omerbircan.ecampus.databinding.ActivitySignupUsernameBinding

class SignupUsername : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivitySignupUsernameBinding
    private  var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignupUsernameBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        email = intent.getStringExtra("mail")
        password = intent.getStringExtra("password")
    }
    fun Finishclick(view: View){

        val username = binding.UserNameText.text.toString()

        if(username.isEmpty()){
            Toast.makeText(this, "Alan boş", Toast.LENGTH_SHORT).show()
        } else if(username.length < 4){
            Toast.makeText(this, "min 4 char", Toast.LENGTH_SHORT).show()
        }
        else{
            auth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Kullanıcı kaydı başarılı, veritabanında güncelleme yap
                        updateInFirestore(email!!, username)
                    } else {
                        // Kullanıcı kaydı başarısız, hata mesajını göster
                        Toast.makeText(this, "Kullanıcı kaydı hatası", Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }

    private fun updateInFirestore(email: String, username: String) {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("users")
        val query = collectionRef.whereEqualTo("mail", email)

        query.get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents[0]
                    val documentId = document.id

                    // Belgeyi güncelle
                    val updateData = hashMapOf(
                        "username" to username,
                        "signed"   to false

                    )

                    db.collection("users").document(documentId)
                        .update(updateData as Map<String, Any>)
                        .addOnSuccessListener {
                            // Güncelleme başarılı
                            val intent = Intent(this@SignupUsername, HomePage::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener { exception ->
                            // Güncelleme başarısız, hata mesajını göster
                            Toast.makeText(this, "Belge güncelleme hatası", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    // Eşleşen belge bulunamadı, hata mesajını göster
                    Toast.makeText(this, "Eşleşen belge bulunamadı", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // Sorgu hatası, hata mesajını göster
                Toast.makeText(this, "Sorgu hatası", Toast.LENGTH_SHORT).show()
            }
    }
}