package com.example.helloworld2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CoursesAdapter (var courseList:List<Courses>):RecyclerView.Adapter<CoursesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.course_list_item,parent,false)
        return CoursesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        var currentCourse = courseList.get(position)
        holder.tvCourseName.text = currentCourse.courseName
        holder.tvDescription.text = currentCourse.description
        holder.tvInstructor.text = currentCourse.instructor
        holder.tvCode.text = currentCourse.courseCode
    }

    override fun getItemCount(): Int {
       return courseList.size
    }
}

class CoursesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvCourseName = itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    var tvInstructor = itemView.findViewById<TextView>(R.id.tvInstructor)
    var tvCode = itemView.findViewById<TextView>(R.id.tvCode)
}