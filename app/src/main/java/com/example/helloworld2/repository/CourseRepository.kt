package com.example.helloworld2.repository

import com.example.helloworld2.api.ApiClient
import com.example.helloworld2.api.ApiInterface
import com.example.helloworld2.models.CourseResponse
import com.example.helloworld2.models.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CourseRepository {
    lateinit var sessionManager: SessionManager
//    sessionManager = SessionManager(this)
    var apiInterface = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun courses():Response<List<CourseResponse>> = withContext(Dispatchers.IO){
        var response = apiInterface.courses(token = "Bearer ${sessionManager.fetchAccToken()}")
        return@withContext response
    }
}