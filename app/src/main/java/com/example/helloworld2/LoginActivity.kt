package com.example.helloworld2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.helloworld2.api.ApiClient
import com.example.helloworld2.api.ApiInterface
import com.example.helloworld2.models.LogInRequest
import com.example.helloworld2.models.LogInResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class LoginActivity : AppCompatActivity() {
    lateinit var etLogInEmail : EditText
    lateinit var etLogInPassword :EditText
    lateinit var btLogInButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        castViews()
        clickLogIn()
    }
    fun castViews(){
        etLogInEmail = findViewById(R.id.etLogInEmail)
        etLogInPassword = findViewById(R.id.etLogInPassword)
        btLogInButton = findViewById(R.id.btLogInButton)

    }
    fun clickLogIn(){
        btLogInButton.setOnClickListener {
            var email = etLogInEmail.text.toString()
            if (email.isEmpty()){
                etLogInEmail.setError("Email required")
            }
            var password = etLogInPassword.text.toString()
            if (password.isEmpty()){
                etLogInPassword.setError("Password required")
            }

            var logInRequest = LogInRequest(
                email = email,
                password = password
            )

            val retrofit = ApiClient.buildApiClient(ApiInterface ::class.java)
            var request = retrofit.loginStudent(logInRequest)
            request.enqueue(object : Callback<LogInResponse>{
                override fun onResponse(
                    call: Call<LogInResponse>,
                    response: Response<LogInResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(baseContext,"Login Successful", Toast.LENGTH_LONG).show()
                        var intent = Intent(baseContext,CoursesActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        try {
                            val error = JSONObject(response.errorBody()!!.string())
                            Toast.makeText(baseContext,error.toString(), Toast.LENGTH_LONG).show()
                        }
                        catch (e: Exception){
                            Toast.makeText(baseContext,e.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message,Toast.LENGTH_LONG).show()
                }
            })

        }

    }
}