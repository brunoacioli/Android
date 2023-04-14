package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.HashMap

class SignUp : AppCompatActivity() {

    private lateinit var edtPhone: EditText
    private lateinit var edtName: EditText
    private lateinit var edtPhoto: ImageView
    private lateinit var btnSignUp: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var storage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        database = FirebaseDatabase.getInstance().getReference()
        storage = FirebaseStorage.getInstance()


        edtPhone = findViewById(R.id.edt_phone)
        edtName = findViewById(R.id.edt_name)
        btnSignUp = findViewById(R.id.btn_signup)
        edtPhoto = findViewById(R.id.app_logo)

        edtPhoto.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 45)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            if (data.data != null) {
                val uri = data.data // filePath
                val storage = FirebaseStorage.getInstance()
                val time = Date().time
                val reference = storage.reference
                    .child("Profile")
                    .child(time.toString() + "")
                reference.putFile(uri!!).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        reference.downloadUrl.addOnCompleteListener { uri ->
                            val filePath = uri.toString()
                            val obj = HashMap<String, Any>()
                            obj["image"] = filePath
//                            database!!
//                                .child("users")
//                                .child(FirebaseAuth.getInstance().uid!!)
//                                .updateChildren(obj).addOnSuccessListener { }
                        }
                    }
                }
//                binding!!.imageView.setImageURI(data.data)
//                selectedImage = data.data
            }
        }
    }
}