package com.omerbircan.ecampus.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.omerbircan.ecampus.adapter.RecyclerviewAdapter
import com.omerbircan.ecampus.databinding.ActivityHomePageBinding
import com.omerbircan.ecampus.models.Posts


class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var viewAdapter: RecyclerviewAdapter
    private var username : String? = null
    private var postArrayList = ArrayList<Posts>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        username = intent.getStringExtra("username")

        getPost()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewAdapter = RecyclerviewAdapter(postArrayList)
        binding.recyclerView.adapter = viewAdapter

    }

    fun newClick(view: View){
        val intent = Intent(this@HomePage, NewPost::class.java)
        intent.putExtra("username", username)
        startActivity(intent)

    }

    fun getPost(){
        val db = FirebaseFirestore.getInstance()
        db.collection("posts").addSnapshotListener { value, error ->

            if(error != null){
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()

            }
            if(value != null){
                if(!value.isEmpty){

                    //getting data from firstore collections
                    val documents = value.documents
                    for(document in documents){

                        val username = document.get("username") as String
                        val content = document.get("content")   as String
                        val posts = Posts(username, content)
                        postArrayList.add(posts)
                    }
                    viewAdapter.notifyDataSetChanged()
                }
            }

        }
        

    }
}