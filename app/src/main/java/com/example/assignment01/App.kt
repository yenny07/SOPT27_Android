package com.example.assignment01

import android.app.Application

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Pref.init(this)
    }
}