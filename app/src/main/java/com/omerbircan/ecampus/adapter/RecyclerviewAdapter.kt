package com.omerbircan.ecampus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omerbircan.ecampus.databinding.RecyclerRowBinding
import com.omerbircan.ecampus.models.Posts

class RecyclerviewAdapter(val postList: ArrayList<Posts>): RecyclerView.Adapter<RecyclerviewAdapter.postHolder>() {

    class postHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return postHolder(binding)
    }

    override fun onBindViewHolder(holder: postHolder, position: Int) {
        holder.binding.usernameId.text = postList.get(position).username
        holder.binding.contentId.text = postList.get(position).content
        holder.binding.timeId.text = postList.get(position).time


        
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}