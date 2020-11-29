package com.example.assignment01.util

import android.content.Context
import android.widget.Toast

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

/*
fun String.toast(context : Context){
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}
 */