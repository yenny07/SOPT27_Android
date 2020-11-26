package com.example.assignment01.seminar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    // 프래그먼트를 담을 리스트
    var fragments = listOf<Fragment>()

    // 리스트의 프래그먼트 인스턴스를 새 페이지에 제공공
   override fun getItem(position: Int): Fragment = fragments[position]

    // adapter에서 만들 페이지 수 반환
    override fun getCount(): Int = fragments.size
}
