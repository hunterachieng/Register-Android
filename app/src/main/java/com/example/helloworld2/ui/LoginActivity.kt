package com.example.helloworld2.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.helloworld2.R
import com.example.helloworld2.api.ApiClient
import com.example.helloworld2.api.ApiInterface
import com.example.helloworld2.databinding.ActivityLoginBinding
import com.example.helloworld2.models.LogInRequest
import com.example.helloworld2.models.LogInResponse
import com.example.helloworld2.models.SessionManager
import com.example.helloworld2.viewmodel.LoginViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var sessionManager:SessionManager

    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)


      binding.btLogInButton.setOnClickListener {

          var intent = Intent(baseContext, CoursesActivity::class.java)
          startActivity(intent)

          var email = binding.etLogInEmail.text.toString()
          if (email.isEmpty()){
              binding.etLogInEmail.setError("Email required")
          }
          var password = binding.etLogInPassword.text.toString()
          if (password.isEmpty()){
              binding.etLogInPassword.setError("Password required")
          }
          var logInRequest = LogInRequest(
             email=email,
              password = password
          )
          loginViewModel.login(logInRequest)
      }
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.loginLIveData.observe(this,{loginResponse ->
            Toast.makeText(baseContext,loginResponse.message,Toast.LENGTH_LONG).show()
            var accessToken = loginResponse.accessToken
            sessionManager.saveAccToken(accessToken)



        })
        loginViewModel.loginFailedLiveData.observe(this,{error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })
    }


}