package com.example.helloworld2.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.helloworld2.Constants
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
        redirect()

    }
    fun redirect(){
        var accessToken = sessionManager.sharedPreferences.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        if (accessToken!!.isNotEmpty()){
            startActivity(Intent(baseContext,CoursesActivity::class.java))
        }
        else{
            startActivity(Intent(baseContext,LoginActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btLogInButton.setOnClickListener {
            var error = false



            var email = binding.etLogInEmail.text.toString()
            if (email.isEmpty() || email.isBlank()){
                binding.etLogInEmail.setError("Email required")
                error = true
            }
            var password = binding.etLogInPassword.text.toString()
            if (password.isEmpty() || email.isBlank()){
                binding.etLogInPassword.setError("Password required")
                error = true
            }
            if(!error){
                binding.pbLogin.visibility = View.VISIBLE
                var logInRequest = LogInRequest(
                    email=email,
                    password = password
                )
                loginViewModel.login(logInRequest)
            }



        }
        loginViewModel.loginLIveData.observe(this,{loginResponse ->
            binding.pbLogin.visibility = View.GONE
            Toast.makeText(baseContext,loginResponse.message,Toast.LENGTH_LONG).show()
            var accessToken = loginResponse.accessToken
            var studentId = loginResponse.studentId
            sessionManager.saveAccToken(accessToken)
            sessionManager.saveAccToken(studentId)
            startActivity(Intent(baseContext, CoursesActivity::class.java))



        })
        //observing login error live data
        loginViewModel.loginFailedLiveData.observe(this,{error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
//            binding.tvLoginError.visibility = View.VISIBLE
//            BINDING.tvLoginError.text = error

        })
    }


}