package com.example.helloworld2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld2.CoursesAdapter
import com.example.helloworld2.databinding.ActivityCoursesBinding
import com.example.helloworld2.viewmodel.CourseViewModel

class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoursesBinding
    val courseViewModel:CourseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onResume() {
        super.onResume()
        courseViewModel.courses()
        var rvCourses = binding.rvcourses
        courseViewModel.courseLiveData.observe(this,{courseResponse ->
//            if (!courseResponse.courseId.isNullOrEmpty()){

                var coursesAdapter = CoursesAdapter(courseResponse)
                rvCourses.layoutManager = LinearLayoutManager(baseContext)
                rvCourses.adapter = coursesAdapter


        })
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