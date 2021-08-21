package com.example.helloworld2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.helloworld2.databinding.ActivityLoginBinding
import com.example.helloworld2.models.LogInRequest
import com.example.helloworld2.models.SessionManager
import com.example.helloworld2.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var sessionManager:SessionManager

    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sessionManager = SessionManager(this)




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