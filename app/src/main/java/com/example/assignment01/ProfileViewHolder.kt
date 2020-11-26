package com.example.assignment01

import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.tv_title)
    private val subTitle: TextView = itemView.findViewById(R.id.tv_subTitle)

    fun onBind(data: ProfileData) {
        title.text = data.title
        subTitle.text = data.subTitle

        itemView.setOnClickListener {
            // intent shit
            val intent = Intent(itemView.context, DetailActivity::class.java)
            intent.putExtra("title", data.title)
            intent.putExtra("subtitle", data.subTitle)
            intent.putExtra("date", data.date)
            intent.putExtra("extra", data.extra)
            startActivity(itemView.context, intent, null)
            Toast.makeText(it.context, "HI", Toast.LENGTH_SHORT).show()
        }
    }
}