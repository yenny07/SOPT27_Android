package com.example.assignment01.seminar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment01.R
import kotlinx.android.synthetic.main.activity_frag_exer.*

class FragExerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var code = 1

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag_exer)

        val fragment1 = FirstFragment()
        val fragment2 = SecondFragment()


        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment1).commit()

        btn_change.setOnClickListener{
            val transAction = supportFragmentManager.beginTransaction()
            when(code){
                1 -> {
                    transAction.replace(R.id.fragment_container, fragment2)
                    code = 2
                }
                2 -> {
                    transAction.replace(R.id.fragment_container, fragment1)
                    code = 1
                }

            }
            transAction.commit()
        }
    }
}