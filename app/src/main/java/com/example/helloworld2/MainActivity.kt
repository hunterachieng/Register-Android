package com.example.helloworld2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //casting:allows you to use variables in other functions
    //Used when not immediately passing the values
    lateinit var etName: EditText
    lateinit var etDob: EditText
    lateinit var etNationality: EditText
    lateinit var etIDNumber: EditText
    lateinit var   etEmail:EditText
    lateinit var  etPhoneNumber:EditText
    lateinit var btnRegister:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castViews()
        clickRegister()
    }

    fun castViews() {
        etName = findViewById(R.id.etName)
        etDob = findViewById(R.id.etDob)
        etNationality = findViewById(R.id.etNationality)
        etIDNumber = findViewById(R.id.etIDNumber)
        etEmail = findViewById(R.id.etEmail)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        btnRegister = findViewById(R.id.btnRegisterLbl)

    }

    fun clickRegister() {
        btnRegister.setOnClickListener {
            var name = etName.text.toString() //validation
            if (name.isEmpty()) {
                etName.setError("Name is required")
            }
            var dob = etDob.text.toString()
            if (dob.isEmpty()) {
                etDob.setError("Dob is required")
            }
            var nationality = etNationality.text.toString()
            var idNumber = etIDNumber.text.toString()
            var email = etEmail.text.toString()
            var phoneNumber = etPhoneNumber.text.toString()

            var userDetails = UserDetails(
                name = name, dob = dob, nationality = nationality, idNumber = idNumber,
                email = email, phoneNumber = phoneNumber
            )

            Toast.makeText(baseContext, userDetails.toString(), Toast.LENGTH_LONG).show()
            val intent =  Intent(baseContext,CoursesActivity::class.java)
            startActivity(intent)
       }
}
}


data class UserDetails(
    var name:String,
    var dob:String,
    var nationality:String,
    var idNumber:String,
    var email:String,
    var phoneNumber:String)