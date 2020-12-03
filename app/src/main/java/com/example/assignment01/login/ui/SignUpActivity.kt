package com.example.assignment01.login.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment01.R
import com.example.assignment01.login.model.RequestSignUpData
import com.example.assignment01.login.model.ResponseSignUpData
import com.example.assignment01.service.SoptServiceImpl
import com.example.assignment01.util.toast
import kotlinx.android.synthetic.main.activity_signup.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("SENSELESS_COMPARISON")
class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

    btn_signup.setOnClickListener{
        // et_name.text.toString() != null 아주 직관적으로 썼다가 혼남
        if(et_name.text.isNullOrBlank() || et_signup_id.text.isNullOrBlank() || et_signup_pw.text.isNullOrBlank()) {
            toast("모든 정보를 기입해주세요.")
        } else{
            val email = et_signup_id.text.toString()
            val password = et_signup_pw.text.toString()
            val name = et_name.text.toString()
            val call : Call<ResponseSignUpData> = SoptServiceImpl.service.postSignUp(
                RequestSignUpData(email = email, password = password, userName = name)
            )
            call.enqueue(object : Callback<ResponseSignUpData>{
                override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                    toast("서버가 불안정합니다.")
                }

                override fun onResponse(
                    call: Call<ResponseSignUpData>,
                    response: Response<ResponseSignUpData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let{ it ->
                            // 로그인 액티비티에 set
                            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                            intent.putExtra("id", et_signup_id.text.toString())
                            intent.putExtra("pw", et_signup_pw.text.toString())
                            toast("가입이 완료되었어요! :)")
                            setResult(Activity.RESULT_OK, intent)
                            finish()
                        }
                }

            })
        }
    }
    }



}