package com.example.assignment01.login.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment01.util.Pref
import com.example.assignment01.R
import com.example.assignment01.util.toast
import kotlinx.android.synthetic.main.activity_signup.*

@Suppress("SENSELESS_COMPARISON")
class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

    btn_signup.setOnClickListener{
        // et_name.text.toString() != null 아주 직관적으로 썼다가 혼남
        if(et_name.text.isNullOrBlank() || et_id.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
            toast("모든 정보를 기입해주세요.")
        } else{
            // SharedPreferences에 id, pw 저장
            Pref.sharedEdit.putString("id", et_id.text.toString())
            Pref.sharedEdit.putString("pw", et_pw.text.toString())
            Pref.sharedEdit.apply()

            // 로그인 액티비티에 set
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", et_id.text.toString())
            intent.putExtra("pw", et_pw.text.toString())
            toast("가입이 완료되었어요! :)")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
    }



}