package com.example.assignment01.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment01.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title  = intent.getStringExtra("title")
        val subTitle = intent.getStringExtra("subtitle")
        val date = intent.getStringExtra("date")
        val extra = intent.getStringExtra("extra")

        tv_title.text = title
        tv_subtitle.text = subTitle
        tv_date.text = date
        tv_extra.text = extra
    }
}