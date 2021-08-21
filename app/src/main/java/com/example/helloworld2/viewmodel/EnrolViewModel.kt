package com.example.helloworld2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld2.models.EnrolRequest
import com.example.helloworld2.models.EnrolResponse
import com.example.helloworld2.repository.CourseRepository
import kotlinx.coroutines.launch

class EnrolViewModel:ViewModel() {
    var enrolLiveData = MutableLiveData<EnrolResponse>()
    var enrolFailedLiveData = MutableLiveData<String>()
    var courseRepository = CourseRepository()

    fun enrol(accessToken: EnrolRequest, enrolRequest: EnrolRequest){
        viewModelScope.launch {
            var response = courseRepository.enrol(accessToken,enrolRequest)
            if (response.isSuccessful){
                enrolLiveData.postValue(response.body())
            }
            else{
                enrolFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}