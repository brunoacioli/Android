package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var edtPhone: EditText
    private lateinit var edtName: EditText
    private lateinit var btnSignUp: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        edtPhone = findViewById(R.id.edt_phone)
        edtName = findViewById(R.id.edt_name)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val name = edtName.text.toString()
            val phone = edtPhone.text.toString()
            val intentSignUp = Intent(this@SignUp, OTPActivity::class.java)
            intentSignUp.putExtra("phone", phone)
            intentSignUp.putExtra("name", name)
            finish()
            startActivity(intentSignUp)
        }
    }
}