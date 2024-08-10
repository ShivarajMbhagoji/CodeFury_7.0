package com.hackathon.util

import android.content.Context

object SharedPref {
    fun saveData(name:String, email: String, context: Context){
        val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("name", name)
        editor.putString("email", email)
        editor.apply()
    }
    fun getName(context: Context):String {
        val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        return sharedPref.getString("name","")!!
    }
    fun getEmail(context: Context):String {
        val sharedPref = context.getSharedPreferences("users", Context.MODE_PRIVATE)
        return sharedPref.getString("email","")!!
    }
}