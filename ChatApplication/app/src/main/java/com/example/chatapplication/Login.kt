package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var edtPhone: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        edtPhone = findViewById(R.id.edt_phone)
        btnLogin = findViewById(R.id.btn_login)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val intentSignUp = Intent(this, SignUp::class.java)
            startActivity(intentSignUp)
        }

        btnLogin.setOnClickListener {
            //code for loggin in
            val phone = edtPhone.text.toString()
            val intentLogin = Intent(this@Login, OTPActivity::class.java)
            intentLogin.putExtra("phone",phone)
            finish()
            startActivity(intentLogin)
        }

    }
}