package com.example.helloworld2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.example.helloworld2.api.ApiClient
import com.example.helloworld2.api.ApiInterface
import com.example.helloworld2.databinding.ActivityMainBinding
import com.example.helloworld2.models.RegistrationRequest
import com.example.helloworld2.models.RegistrationResponse
import com.example.helloworld2.ui.LoginActivity
import com.example.helloworld2.viewmodel.UserViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    //casting:allows you to use variables in other functions
    //Used when not immediately passing the values

    lateinit var binding: ActivityMainBinding
    val userViewModel:UserViewModel by viewModels()
     var spinner = arrayOf("KENYAN","UGANDAN","RWANDAN","SUDAN","SOUTH SUDAN")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        var error = false
        binding.btLgIn.setOnClickListener {
            var intent = Intent(baseContext,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegisterLbl.setOnClickListener {
            var name = binding.etName.text.toString() //validation
            if (name.isEmpty()) {
                error = true
                binding.etName.setError("Name is required")
            }
            var dob = binding.etDob.text.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDob.setError("Dob is required")
            }
            var nationalities = binding.spNationality
            var natadapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinner)
            natadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            nationalities.adapter = natadapter

            var nationality = nationalities.selectedItem.toString()





            var password= binding.etPassword.text.toString()
            if (password.isEmpty()){
                error = true
                binding.etPassword.setError("Password required")
            }
            var email = binding.etEmail.text.toString()
            if (email.isEmpty()){
                error = true
                binding.etEmail.setError("Email required")
            }
            var phoneNumber = binding.etPhoneNumber.text.toString()
            if (phoneNumber.isEmpty()){
                error = true
                binding.etPhoneNumber.setError("PhoneNumber required")
            }

            var registrationRequest = RegistrationRequest(
                name=name,
                phoneNumber=phoneNumber,
                email= email,
                nationality= nationality,
                dateOfBirth = dob,
                password = password
            )
            userViewModel.registerUser(registrationRequest)
        }
        userViewModel.registrationLiveData.observe(this,{registrationResponse ->
            if(!registrationResponse.studentId.isNullOrEmpty()){
                var intent = Intent(baseContext,LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(baseContext,"Registration Successful",Toast.LENGTH_LONG).show()
            }
        })
        userViewModel.regFailedLiveData.observe(this,{error ->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }



}
data class ApiError(var errors:List<String>)


