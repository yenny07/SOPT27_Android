package com.example.assignment01.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.assignment01.R
import com.example.assignment01.seminar.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.properties.Delegates

class HomeActivity : AppCompatActivity() {

    // VPAdapter에서 만든 어댑터 선언
    private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 매니저를 불러와 어댑터의 변수에 프래그먼트를 생성해 넣어줌
        viewpagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            MeFragment(),
            ProfileFragment(),
            SettingFragment()
        )
        viewpager_home.adapter = viewpagerAdapter

        // 뷰페이저 슬라이드 시
        viewpager_home.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
            // 뷰페이저의 페이지 중 하나가 선택된 경우
            override fun onPageSelected(position: Int) {
                bottom_navi.menu.getItem(position).isChecked = true
            }
        })


        // 바텀 네비게이션 세팅
        bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_me -> index = 0
                R.id.menu_profile -> index = 1
                R.id.menu_setting -> index = 2
            }
            viewpager_home.currentItem = index
            true
        }
    }
}