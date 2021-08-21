package com.example.helloworld2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld2.CoursesAdapter
import com.example.helloworld2.databinding.ActivityCoursesBinding
import com.example.helloworld2.models.SessionManager
import com.example.helloworld2.viewmodel.CourseViewModel
import com.example.helloworld2.viewmodel.EnrolViewModel

class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    val courseViewModel:CourseViewModel by viewModels()
    val enrolViewModel:EnrolViewModel by viewModels()
    lateinit var coursesAdapter: CoursesAdapter
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)




    }

    override fun onResume() {
        super.onResume()
        var accessToken = sessionManager.fetchAccToken()
        if (accessToken!!.isNotEmpty()){
            courseViewModel.courses(accessToken = "Bearer ${accessToken}")
        }
        else{
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }

        var rvCourses = binding.rvcourses

        rvCourses.layoutManager = LinearLayoutManager(baseContext)
        courseViewModel.courseLiveData.observe(this,{courseResponse ->

            coursesAdapter = CoursesAdapter(courseResponse)
            rvCourses.adapter = coursesAdapter





        })
        courseViewModel.courseFailedLiveData.observe(this,{error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
        courseViewModel.courses(accessToken)
    }





//        var courseList = listOf(
//            Courses("MB101","Mobile Development","Introduction to Android development with Kotlin", "John Owuor"),
//            Courses("BE101","Backend Development","Introduction to Backend development with Python", "James Mwai"),
//            Courses("IOT101","IOT","IOT implementation", "Barre Yasin"),
//            Courses("FE101","Frontend Development","Introduction to Frontend development with JavaScript", "Purity Maina"),
//            Courses("ID101","Industrial Design","Design for everyday life", "Barre Yasin"),
//            Courses("UX-R101","UX Research","UX and UI field research", "Joy Wambui")
//        )

}