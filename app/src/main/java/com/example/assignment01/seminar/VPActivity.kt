package com.example.assignment01.seminar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.assignment01.R
import kotlinx.android.synthetic.main.activity_v_p.*
import kotlin.properties.Delegates

class VPActivity : AppCompatActivity() {

    // VPAdapter에서 만든 어댑터 선언
    private lateinit var viewpagerAdapter : ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_v_p)

        // 매니저를 불러와 어댑터의 변수에 프래그먼트를 생성해 넣어줌
        viewpagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewpagerAdapter.fragments = listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        sample_viewpager.adapter = viewpagerAdapter

        // 뷰페이저 슬라이드 시
        sample_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
            // 뷰페이저의 페이지 중 하나가 선택된 경우
            override fun onPageSelected(position: Int) {
                sample_bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        // TabLayout과 연동
        sample_tab.setupWithViewPager(sample_viewpager)
        sample_tab.apply {
            getTabAt(0 )?.text = "첫번째"
            getTabAt(1 )?.text = "두번째"
            getTabAt(2 )?.text = "세번째"
        }

        // 바텀 네비게이션 세팅
        sample_bottom_navi.setOnNavigationItemReselectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_sidebar -> index = 0
                R.id.menu_home -> index = 1
                R.id.menu_personal -> index = 2
            }
            sample_viewpager.currentItem = index
        }
    }
}