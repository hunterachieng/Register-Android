package com.example.helloworld2.models

import com.google.gson.annotations.SerializedName

data class EnrolRequest(
    @SerializedName("student_id") var studentId: String?,
    @SerializedName("course_id") var courseId: String?
)
