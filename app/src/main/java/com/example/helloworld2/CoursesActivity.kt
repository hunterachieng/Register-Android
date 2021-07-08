package com.example.helloworld2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoursesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
        displayCourses()


    }
    fun displayCourses(){
        var rvCourses = findViewById<RecyclerView>(R.id.rvcourses)
        var courseList = listOf(
            Courses("MB101","Mobile Development","Introduction to Android development with Kotlin", "John Owuor"),
            Courses("BE101","Backend Development","Introduction to Backend development with Python", "James Mwai"),
            Courses("IOT101","IOT","IOT implementation", "Barre Yasin"),
            Courses("FE101","Frontend Development","Introduction to Frontend development with JavaScript", "Purity Maina"),
            Courses("ID101","Industrial Design","Design for everyday life", "Barre Yasin"),
            Courses("UX-R101","UX Research","UX and UI field research", "Joy Wambui")
        )
        var coursesAdapter = CoursesAdapter(courseList)
        rvCourses.layoutManager = LinearLayoutManager(baseContext)
        rvCourses.adapter = coursesAdapter
    }
}