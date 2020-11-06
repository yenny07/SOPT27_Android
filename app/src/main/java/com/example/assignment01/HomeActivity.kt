package com.example.assignment01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        profileAdapter = ProfileAdapter(this)

        rcv_profile.adapter = profileAdapter
        rcv_profile.layoutManager = LinearLayoutManager(this)

        profileAdapter.data = mutableListOf(
            ProfileData("이름", "이예인"),
            ProfileData("나이", "24"),
            ProfileData("파트", "안드로이드"),
            ProfileData("GitHub", "www.github.com/yenny07"),
            ProfileData("Instagram", "yen.__.n")
        )

        profileAdapter.notifyDataSetChanged()

    }
}