package com.example.helloworld2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld2.models.RegistrationRequest
import com.example.helloworld2.models.RegistrationResponse
import com.example.helloworld2.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    var registrationLiveData = MutableLiveData<RegistrationResponse>()
    var regFailedLiveData = MutableLiveData<String>() //mutable workd like AJAX
    var userRepository = UserRepository()

    fun registerUser(registrationRequest: RegistrationRequest){
        viewModelScope.launch {
            var response = userRepository.registerStudent(registrationRequest)
            if(response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                regFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}