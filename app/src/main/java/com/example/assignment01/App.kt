package com.example.assignment01

import android.app.Application
import com.example.assignment01.util.Pref

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        Pref.init(this)
    }
}