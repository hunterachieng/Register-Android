package com.example.helloworld2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld2.models.CourseResponse
import com.example.helloworld2.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel:ViewModel(){
    var courseLiveData = MutableLiveData<List<CourseResponse>>()
    var courseFailedLiveData = MutableLiveData<String>()
    var courseRepository = CourseRepository()

    fun courses(accessToken:String){
        viewModelScope.launch {
            var response = courseRepository.courses(accessToken)
            if (response.isSuccessful){
                courseLiveData.postValue(response.body())
            }
            else{
                courseFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}