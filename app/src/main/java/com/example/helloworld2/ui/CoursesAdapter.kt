package com.example.helloworld2

import  android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld2.Constants.Companion.STUDENT_ID
import com.example.helloworld2.databinding.CourseListItemBinding
import com.example.helloworld2.models.CourseResponse
import com.example.helloworld2.models.EnrolRequest
import com.example.helloworld2.models.SessionManager
import com.example.helloworld2.viewmodel.EnrolViewModel


class CoursesAdapter(var courseList: List<CourseResponse>):RecyclerView.Adapter<CoursesViewHolder>()
     {
   private lateinit var enrolViewModel: EnrolViewModel
   private lateinit var sessionManager: SessionManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var itemView = LayoutInflater.from(parent.context)
        var binding = CourseListItemBinding.inflate(itemView,parent,false)

        return CoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        var currentCourse = courseList.get(position)
        holder.binding.tvCourseName.text = currentCourse.courseName
        holder.binding.tvDescription.text = currentCourse.description
        holder.binding.tvInstructor.text = currentCourse.instructor
        holder.binding.tvCode.text = currentCourse.courseCode
        holder.binding.btEnrol.setOnClickListener {
            sessionManager = SessionManager(context = this)
            var studentId = sessionManager.fetchAccToken(STUDENT_ID)
            var courseId = sessionManager.fetchAccToken(Constants.COURSE_ID)
            var accessToken = sessionManager.fetchAccToken(Constants.ACCESS_TOKEN)
            var enrolRequest = EnrolRequest(
                studentId = studentId,
                courseId = courseId
            )
            enrolViewModel.enrol(accessToken, enrolRequest)
        }

    }

    override fun getItemCount(): Int {
       return courseList.size
    }
}

class CoursesViewHolder(var binding: CourseListItemBinding):RecyclerView.ViewHolder(binding.root){
//    var tvCourseName = itemView.findViewById<TextView>(R.id.tvCourseName)
//    var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
//    var tvInstructor = itemView.findViewById<TextView>(R.id.tvInstructor)
//    var tvCode = itemView.findViewById<TextView>(R.id.tvCode)
}