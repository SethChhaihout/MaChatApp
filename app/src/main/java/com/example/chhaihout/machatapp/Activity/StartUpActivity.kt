package com.example.chhaihout.machatapp.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.chhaihout.machatapp.R
import kotlinx.android.synthetic.main.activity_start_up.*

class StartUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_up)

        //Button SignIn
        btnSignIn.setOnClickListener{
            var intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        //Button Register
        btnRegister.setOnClickListener{
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
