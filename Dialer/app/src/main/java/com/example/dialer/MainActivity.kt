package com.example.dialer

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener

class MainActivity : AppCompatActivity() {

    lateinit var b1: Button
    lateinit var b2: Button
    lateinit var b3: Button
    lateinit var b4: Button
    lateinit var b5: Button
    lateinit var b6: Button
    lateinit var b7: Button
    lateinit var b8: Button
    lateinit var b9: Button
    lateinit var b0: Button
    lateinit var bstar: Button
    lateinit var bhash: Button
    lateinit var bcall: Button

    lateinit var bclear: ImageButton

    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1 = findViewById(R.id.b1)
        b2 = findViewById(R.id.b2)
        b3 = findViewById(R.id.b3)
        b4 = findViewById(R.id.b4)
        b5 = findViewById(R.id.b5)
        b6 = findViewById(R.id.b6)
        b7 = findViewById(R.id.b7)
        b8 = findViewById(R.id.b8)
        b9 = findViewById(R.id.b9)
        b0 = findViewById(R.id.b0)
        bstar = findViewById(R.id.bstar)
        bhash = findViewById(R.id.bhash)
        bcall = findViewById(R.id.bcall)
        bclear = findViewById(R.id.ibtn)
        tv = findViewById(R.id.txtphone)

        Dexter.withContext(this).withPermission(Manifest.permission.CALL_PHONE).withListener(object: PermissionListener {
            override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

            }

            override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

            }

            override fun onPermissionRationaleShouldBeShown(
                p0: PermissionRequest?,
                p1: PermissionToken?
            ) {

            }
        }).check()

        b1.setOnClickListener {
            tv.text = tv.text.toString() + "1"
        }

        b2.setOnClickListener {
            tv.text = tv.text.toString() + "2"
        }

        b3.setOnClickListener {
            tv.text = tv.text.toString() + "3"
        }

        b4.setOnClickListener {
            tv.text = tv.text.toString() + "4"
        }

        b5.setOnClickListener {
            tv.text = tv.text.toString() + "5"
        }

        b6.setOnClickListener {
            tv.text = tv.text.toString() + "6"
        }

        b7.setOnClickListener {
            tv.text = tv.text.toString() + "7"
        }

        b8.setOnClickListener {
            tv.text = tv.text.toString() + "8"
        }

        b9.setOnClickListener {
            tv.text = tv.text.toString() + "9"
        }

        b0.setOnClickListener {
            tv.text = tv.text.toString() + "0"
        }

        bstar.setOnClickListener {
            tv.text = tv.text.toString() + "*"
        }

        bhash.setOnClickListener {
            tv.text = tv.text.toString() + "#"
        }

        bclear.setOnClickListener {
            val numberStr: String = tv.text.toString()
            tv.text = numberStr.dropLast(1)
        }

        bcall.setOnClickListener {
            makePhoneCall()
        }


    }

    private fun makePhoneCall() {
        val numberStr :String  = tv.text.toString()
        val dial: String = "tel:" + numberStr

        startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
    }
}