package com.example.helloworld2.models

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    var sharedPreferences: SharedPreferences = context.getSharedPreferences("HELLO_WORLD_2",Context.MODE_PRIVATE)
    //function to save the accesstoken
    fun saveAccToken(token:String){
        sharedPreferences.edit().putString("ACCESS_TOKEN",token).apply()
    }
    //function to get the accesstoken
    fun fetchAccToken():String?{
       return sharedPreferences.getString("ACCESS_TOKEN","")
    }
}