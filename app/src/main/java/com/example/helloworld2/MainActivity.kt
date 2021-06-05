package com.example.helloworld2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    //casting:allows you to use variables in other functions
    //Used when not immediately passing the values
    lateinit var etName: EditText
    lateinit var etDob: EditText
    lateinit var etIDNumber: EditText
    lateinit var   etEmail:EditText
    lateinit var  etPhoneNumber:EditText
    lateinit var btnRegister:Button
    lateinit var spNationality:Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        castViews()
        clickRegister()
    }

    fun castViews() {
        etName = findViewById(R.id.etName)
        etDob = findViewById(R.id.etDob)
        etIDNumber = findViewById(R.id.etIDNumber)
        etEmail = findViewById(R.id.etEmail)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        btnRegister = findViewById(R.id.btnRegisterLbl)
        spNationality = findViewById(R.id.spNationality)
        val nationalities = arrayOf("Kenyan", "Rwandan", "South Sudanese", "Ugandan")
        var adapter = ArrayAdapter<String>(baseContext,android.R.layout.simple_spinner_item,nationalities)
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spNationality.adapter=adapter
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
            var nationality = spNationality.selectedItem.toString()
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