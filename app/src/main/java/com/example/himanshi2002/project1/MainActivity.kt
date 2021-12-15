package com.example.himanshi2002.project1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity()
{
    lateinit var e1:EditText

    lateinit var b1:Button
    lateinit var p1:ProgressBar

    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        e1=findViewById(R.id.edit1)
        if(e1.text.toString().isEmpty())
        {
            Log.d("string","is empty")
        }
        b1=findViewById(R.id.button1)
        p1=findViewById(R.id.progressbar_sending_otp)
        Log.d("string",e1.text.length.toString())

        var str1:String=e1.text.trim().toString()

        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {

            override fun onVerificationCompleted(credential: PhoneAuthCredential)
            {
                p1.visibility= View.GONE
                b1.visibility=View.VISIBLE

            }

            override fun onVerificationFailed(e: FirebaseException)
            {
                p1.visibility= View.GONE
                b1.visibility=View.VISIBLE
                Log.d("my error",e.message.toString())
                Toast.makeText(applicationContext,e.message,Toast.LENGTH_LONG).show()

            }

            override fun onCodeSent(verificationId: String,token: PhoneAuthProvider.ForceResendingToken )
            {
                p1.visibility= View.GONE
                b1.visibility=View.VISIBLE
                var i= Intent(applicationContext,verifyenterotp::class.java)
                i.putExtra("mobile",str1)
                i.putExtra("backendotp",verificationId)
                startActivity(i)

            }
        }

        Log.d("string",e1.text.toString())

        b1.setOnClickListener{
            str1=e1.text.trim().toString()

            Log.d("str1=",str1.length.toString())
            if(str1.isNotEmpty())
            {
                Log.d("str1=",str1)
                Log.d("check inside","the if statement of empty editbox")
                if(str1.length==10)
                {
                    Log.d("check inside","the if statement of empty editbox")
                    p1.visibility= View.VISIBLE
                    b1.visibility=View.INVISIBLE

                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91"+e1.text.toString())       // Phone number to verify
                        .setTimeout(300L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)


                }
                else
                {
                    Toast.makeText(applicationContext,"Please Enter correct mobile number",Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(applicationContext,"Enter mobile number",Toast.LENGTH_LONG).show()
            }
        }
    }
}