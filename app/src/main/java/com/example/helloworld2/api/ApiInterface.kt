package com.example.helloworld2.api
import com.example.helloworld2.models.LogInRequest
import com.example.helloworld2.models.LogInResponse
import com.example.helloworld2.models.RegistrationRequest
import com.example.helloworld2.models.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
    fun registerStudent(@Body registrationRequest: RegistrationRequest): Call<RegistrationResponse>

    @POST("/students/login")
    fun loginStudent(@Body logInRequest: LogInRequest): Call<LogInResponse>

}