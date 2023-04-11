package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.example.chatapplication.model.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mukeshsolanki.OtpView
import java.util.concurrent.TimeUnit

class OTPActivity : AppCompatActivity() {

    private lateinit var phoneLabel: TextView
    private lateinit var verificationId: String
    private lateinit var otpView: OtpView
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpactivity)
        auth = FirebaseAuth.getInstance()
        otpView = findViewById(R.id.otp_view)

        val phone = intent.getStringExtra("phone")
        val name = intent.getStringExtra("name")
        phoneLabel = findViewById(R.id.phone_label)
        phoneLabel.text = "Verify $phone"

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone!!)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("Not yet implemented")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    TODO("Not yet implemented")
                }

                override fun onCodeSent(verifyId: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(verifyId, forceResendingToken)
                    verificationId = verifyId

                }

            })          // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)

        otpView.setOtpCompletionListener {otp ->
            val credential = PhoneAuthProvider.getCredential(verificationId!!, otp)
            auth!!.signInWithCredential(credential)
                .addOnCompleteListener {task ->
                    if(task.isSuccessful) {
                        if(name != null) {
                            addUserToDatabase(name!!,phone,auth.currentUser?.uid!!)
                        }
                        val intent = Intent(this@OTPActivity, MainActivity::class.java)
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        finishAffinity()
                    } else {
                        Toast.makeText(this@OTPActivity, "Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


    private fun addUserToDatabase(name: String, phone: String, uid:String) {
        database = FirebaseDatabase.getInstance().getReference()

        database.child("user").child(uid).setValue(User(name,phone,uid))

    }
}