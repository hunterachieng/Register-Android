package com.example.helloworld2.api
import com.example.helloworld2.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/students/register")
  suspend  fun registerStudent(@Body registrationRequest: RegistrationRequest): Response<RegistrationResponse>

    @POST("/students/login")
   suspend fun loginStudent(@Body logInRequest: LogInRequest): Response<LogInResponse>

   @GET("/courses")
   suspend fun courses(@Header("Authorization")token:String):Response<List<CourseResponse>>

}