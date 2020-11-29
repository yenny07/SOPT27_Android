package com.example.assignment01.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment01.R
import com.example.assignment01.main.model.ProfileData

class ProfileAdapter (private val context: Context) : RecyclerView.Adapter<ProfileViewHolder>(){
    var data = mutableListOf<ProfileData>()

    // ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rcv_item_list, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}