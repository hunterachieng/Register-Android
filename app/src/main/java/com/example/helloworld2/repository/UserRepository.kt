package com.example.helloworld2.repository

import com.example.helloworld2.api.ApiClient
import com.example.helloworld2.api.ApiInterface
import com.example.helloworld2.models.LogInRequest
import com.example.helloworld2.models.LogInResponse
import com.example.helloworld2.models.RegistrationRequest
import com.example.helloworld2.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun registerStudent(registrationRequest: RegistrationRequest):
            Response<RegistrationResponse> = withContext(Dispatchers.IO){
            var response =  apiInterface.registerStudent(registrationRequest)
        return@withContext response
    }
    suspend fun login(logInRequest: LogInRequest):
            Response<LogInResponse> = withContext(Dispatchers.IO){
                var response = apiInterface.loginStudent(logInRequest)
                 return@withContext response
    }

}