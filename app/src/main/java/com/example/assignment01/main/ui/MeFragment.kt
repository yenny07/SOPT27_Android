package com.example.assignment01.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.assignment01.R
import com.example.assignment01.seminar.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_me.view.*


class MeFragment : Fragment() {

    // HomeActivity에서 만든 어댑터 선언
    private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_me, container, false)

        // 매니저를 불러와 어댑터의 변수에 프래그먼트를 생성해 넣어줌
        viewpagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewpagerAdapter.fragments = listOf(
            MeHeyFragment(),
            MeYouFragment()
        )
        view.viewpager_heyyou.adapter = viewpagerAdapter


        // TabLayout과 연동
        view.tab_me.setupWithViewPager(view.viewpager_heyyou)
        view.tab_me.apply {
            getTabAt(0 )?.text = "Hey"
            getTabAt(1 )?.text = "You"
        }

        return view
    }

}