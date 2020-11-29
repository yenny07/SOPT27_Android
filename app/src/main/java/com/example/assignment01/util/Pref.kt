package com.example.assignment01.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

object Pref {
    private val PREF_NAME = "pref"

    lateinit var sharedPref : SharedPreferences
    lateinit var sharedEdit : SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    fun init(context: Context){
        sharedPref = context.getSharedPreferences(
            PREF_NAME, Context.MODE_PRIVATE)
        sharedEdit = sharedPref.edit()
        sharedEdit.apply()
    }
}