package com.example.assignment01

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 저장된 id, pw가 있다면 자동 로그인
        if(Pref.sharedPref.getString("id", "") != null
            && Pref.sharedPref.getString("pw", "") != null)
        {
            toast("자동 로그인 성공")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // 회원가입
        btn_signup.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 0)
        }

        // 로그인
        btn_login.setOnClickListener{
            if (switch_auto.isChecked) {
                // SharedPreferences에 저장
                Pref.sharedEdit.putString("id", et_id.text.toString())
                Pref.sharedEdit.putString("pw", et_pw.text.toString())
                Pref.sharedEdit.apply()
            }

            toast("로그인 성공 :)")
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    // 가입 후 돌아왔을 때 editText 내용 세팅
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                0 -> {
                    et_id.setText(data!!.getStringExtra("id").toString())
                    et_pw.setText(data!!.getStringExtra("pw").toString())
                }
            }
        }
    }
}

