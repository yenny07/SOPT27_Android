package com.example.assignment01.login.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment01.*
import com.example.assignment01.main.ui.HomeActivity
import com.example.assignment01.util.Pref
import com.example.assignment01.util.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 저장된 id, pw가 있다면 자동 로그인
        if(Pref.sharedPref.getBoolean("auto", false))
        {
            toast("자동 로그인 되었습니다")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // 회원가입
        btn_signup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent,
                RESULT_CODE
            )
        }

        // 로그인
        btn_login.setOnClickListener{
            // id, pw 존재 -> 로그인 성공
            if(et_id.text.toString() == Pref.sharedPref.getString("id", "")
                && et_pw.text.toString() ==  Pref.sharedPref.getString("pw", "")){

                // 자동 로그인 체크 시 그 여부를 SharedPreferences에 저장
                if (switch_auto.isChecked) {
                    Pref.sharedEdit.putBoolean("auto", true)
                    Pref.sharedEdit.apply()
                }

                toast("로그인 성공 :)")
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)

            } else {
                toast("회원 정보를 다시 확인해주세요.")
            }
        }
    }

    // 가입 후 돌아왔을 때 editText 내용 세팅
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                RESULT_CODE -> {
                    et_id.setText(data!!.getStringExtra("id").toString())
                    et_pw.setText(data!!.getStringExtra("pw").toString())
                }
            }
        }
    }

    companion object {
        private const val RESULT_CODE = 0
    }
}

