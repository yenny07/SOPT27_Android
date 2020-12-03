package com.example.assignment01.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글톤 객체
object SoptServiceImpl{
    private const val BASE_URL = "http://15.164.83.210:3000"

    private val retrofit : Retrofit = Retrofit.Builder() // 생성자 호출
        .baseUrl(BASE_URL)                               // 빌더 객체의 베이스 호출
        .addConverterFactory(GsonConverterFactory.create()) // gson 연동
        .build() // 레트로핏 객체 반환

    val service : SoptService = retrofit.create(SoptService::class.java)
}