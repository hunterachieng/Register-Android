package com.example.helloworld2.models

import android.content.Context
import android.content.SharedPreferences
import com.example.helloworld2.Constants
import com.example.helloworld2.CoursesAdapter

class SessionManager(context: Context) {
    var sharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.SHAREDPREFS,Context.MODE_PRIVATE)
    //function to save the accesstoken
    fun saveAccToken(token:String){
        sharedPreferences.edit().putString(Constants.ACCESS_TOKEN,token).apply()
        sharedPreferences.edit().putString(Constants.STUDENT_ID,token).apply()
        sharedPreferences.edit().putString(Constants.COURSE_ID,token).apply()
    }
    //function to get the accesstoken
    fun fetchAccToken():String?{
       return sharedPreferences.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
    }
}