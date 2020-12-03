package com.example.assignment01.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment01.R
import com.example.assignment01.main.adapter.ProfileAdapter
import com.example.assignment01.main.model.ProfileData
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        profileAdapter = ProfileAdapter(requireContext())

        view.rcv_profile.apply{
            adapter = profileAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        profileAdapter.data = mutableListOf(
            ProfileData(
                "이름",
                "이예인",
                "2020-11-25",
                "이름이에요~"
            ),
            ProfileData(
                "나이",
                "24",
                "2020-11-25",
                "970727"
            ),
            ProfileData(
                "파트",
                "안드로이드",
                "2020-11-25",
                "안드안드안드"
            ),
            ProfileData(
                "GitHub",
                "www.github.com/yenny07",
                "2020-11-25",
                "ㅎㅎㅎ"
            ),
            ProfileData(
                "Instagram",
                "yen.__.n",
                "2020-11-25",
                "잘 안해요"
            )
        )

        profileAdapter.notifyDataSetChanged()

        return view
    }


}