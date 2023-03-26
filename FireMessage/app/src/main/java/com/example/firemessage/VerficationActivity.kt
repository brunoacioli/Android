package com.example.firemessage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firemessage.databinding.ActivityVerficationBinding
import com.google.firebase.auth.FirebaseAuth

class VerficationActivity : AppCompatActivity() {

    var binding : ActivityVerficationBinding? = null

    var auth:FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVerficationBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        auth = FirebaseAuth.getInstance()
        if(auth!!.currentUser != null) {
            val intent = Intent(this@VerficationActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        supportActionBar!!.hide()
        binding!!.editNumber.requestFocus()
        binding!!.continueBtn.setOnClickListener {
            val intent = Intent(this@VerficationActivity, OTPActivity::class.java)
            intent.putExtra("phoneNumber", binding!!.editNumber.text.toString())
            startActivity(intent)

        }
    }
}