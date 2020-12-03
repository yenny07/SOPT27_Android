package com.example.assignment01.service

import com.example.assignment01.login.model.RequestLoginData
import com.example.assignment01.login.model.RequestSignUpData
import com.example.assignment01.login.model.ResponseLoginData
import com.example.assignment01.login.model.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptService{
    @Headers("Content-Type: application/json")
    @POST("/users/signup")
    fun postSignUp(
        @Body body : RequestSignUpData
    ) : Call<ResponseSignUpData>

    @Headers("Content-Type: application/json")
    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}