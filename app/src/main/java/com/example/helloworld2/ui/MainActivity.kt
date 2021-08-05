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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        binding.btnRegisterLbl.setOnClickListener {
            var registrationRequest = RegistrationRequest(
                name=binding.etName.text.toString(),
                phoneNumber=binding.etPhoneNumber.text.toString(),
                email= binding.etEmail.text.toString(),
                nationality= binding.spNationality.selectedItem.toString(),
                dateOfBirth = binding.etDob.text.toString(),
                password = binding.etPassword.text.toString()
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



//    fun clickRegister() {
//        var error = false
//        btnRegister.setOnClickListener {
//            var name = etName.text.toString() //validation
//            if (name.isEmpty()) {
//                error = true
//                etName.setError("Name is required")
//            }
//            var dob = etDob.text.toString()
//            if (dob.isEmpty()) {
//                error = true
//                etDob.setError("Dob is required")
//            }
//            var nationality = spNationality.selectedItem.toString()
//
//            var password= etPassword.text.toString()
//            if (password.isEmpty()){
//                error = true
//                etPassword.setError("Password required")
//            }
//            var email = etEmail.text.toString()
//            if (email.isEmpty()){
//                error = true
//                etEmail.setError("Email required")
//            }
//            var phoneNumber = etPhoneNumber.text.toString()
//            if (phoneNumber.isEmpty()){
//                error = true
//                etPhoneNumber.setError("PhoneNumber required")
//            }
//
//           var registrationRequest = RegistrationRequest(
//               name=name,
//               phoneNumber=phoneNumber,
//               email=email,
//               nationality=nationality,
//               dateOfBirth = dob,
//               password = password
//           )
//     val retrofit = ApiClient.buildApiClient(ApiInterface::class.java)
//            var request = retrofit.registerStudent(registrationRequest)
//            request.enqueue(object :Callback<RegistrationResponse>{
//                override fun onResponse(
//                    call: Call<RegistrationResponse>,
//                    response: Response<RegistrationResponse>
//                ) {
//                  if (response.isSuccessful){
////                      var posts = response.body()
//                      Toast.makeText(baseContext,"Registration Successful",Toast.LENGTH_LONG).show()
//                      var intent = Intent(baseContext, LoginActivity::class.java)
//                      startActivity(intent)
//
//                  }
//                    else{
//                        try {
//                            val error = JSONObject(response.errorBody()!!.string())
//                            Toast.makeText(baseContext,error.toString(), Toast.LENGTH_LONG).show()
//                        }
//                        catch (e:Exception){
//                            Toast.makeText(baseContext,e.message, Toast.LENGTH_LONG).show()
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
//                   Toast.makeText(baseContext, t.message,Toast.LENGTH_LONG).show()
//                }
//            })
//
//       }
//}
}
data class ApiError(var errors:List<String>)


