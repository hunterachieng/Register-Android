package com.example.helloworld2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworld2.models.LogInRequest
import com.example.helloworld2.models.LogInResponse
import com.example.helloworld2.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    var loginLIveData = MutableLiveData<LogInResponse>()
    var loginFailedLiveData = MutableLiveData<String>()
    var userRepository = UserRepository() //create instance of the repository

    fun login(logInRequest: LogInRequest){
        viewModelScope.launch {
            var response = userRepository.login(logInRequest)
            if(response.isSuccessful){
                loginLIveData.postValue(response.body())
            }
            else{
                loginFailedLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}