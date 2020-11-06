package com.example.assignment01

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val title : TextView = itemView.findViewById(R.id.tv_title)
    private val subTitle : TextView = itemView.findViewById(R.id.tv_subTitle)

    fun onBind(data : ProfileData){
        title.text = data.title
        subTitle.text = data.subTitle

        itemView.setOnClickListener {
            // intent shit
            Toast.makeText(it.context, "HI", Toast.LENGTH_SHORT).show()
        }
    }
}