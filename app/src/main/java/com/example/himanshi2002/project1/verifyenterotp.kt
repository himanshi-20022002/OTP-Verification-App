package com.example.himanshi2002.project1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class verifyenterotp : AppCompatActivity()
{
    lateinit var inputnumber1:EditText
    lateinit var inputnumber2:EditText
    lateinit var inputnumber3:EditText
    lateinit var inputnumber4:EditText
    lateinit var inputnumber5:EditText
    lateinit var inputnumber6:EditText
    lateinit var shownumber:TextView
    lateinit var submit:Button
    lateinit var p2:ProgressBar

    lateinit var getotpbackend:String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verifyenterotp)

        inputnumber1=findViewById(R.id.inputotp1)
        inputnumber2=findViewById(R.id.inputotp2)
        inputnumber3=findViewById(R.id.inputotp3)
        inputnumber4=findViewById(R.id.inputotp4)
        inputnumber5=findViewById(R.id.inputotp5)
        inputnumber6=findViewById(R.id.inputotp6)
        shownumber=findViewById(R.id.shownumber)
        submit=findViewById(R.id.buttongetotp)
        p2=findViewById(R.id.progressbar_verify_otp)

        shownumber.setText(String.format("+91-%s",intent.getStringExtra("mobile")))
        getotpbackend= intent.getStringExtra(("backendotp")).toString()

        submit.setOnClickListener{
            val input1:String=inputnumber1.text.trim().toString()
            val input2:String=inputnumber2.text.trim().toString()
            val input3:String=inputnumber3.text.trim().toString()
            val input4:String=inputnumber4.text.trim().toString()
            val input5:String=inputnumber5.text.trim().toString()
            val input6:String=inputnumber6.text.trim().toString()
            if(input1.isNotEmpty() && input2.isNotEmpty() && input3.isNotEmpty() && input4.isNotEmpty() && input5.isNotEmpty() && input6.isNotEmpty())
            {
                val otp:String=input1+input2+input3+input4+input5+input6
                if(getotpbackend.isNotEmpty())
                {
                    p2.visibility= View.VISIBLE
                    submit.visibility=View.INVISIBLE
                    val credential = PhoneAuthProvider.getCredential(getotpbackend!!, otp)
                    signInWithPhoneAuthCredential(credential)
                    
                }
                else
                {
                    Toast.makeText(applicationContext,"Please",Toast.LENGTH_LONG).show()
                }

            }
            else
            {
                Toast.makeText(applicationContext,"Enter all numbers",Toast.LENGTH_LONG).show()
            }
        }


        Log.d("check","go to inputnumber2")
        numberotpmove()
        Log.d("check","go to inputnumber2")



    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        val auth= Firebase.auth
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                p2.visibility=View.GONE
                submit.visibility=View.VISIBLE
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val i= Intent(applicationContext,Dashboard::class.java)
                    startActivity(i)

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI

                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private fun numberotpmove()
    {
        Log.d("check","go to inputnumber2")
        inputnumber1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty())
                {
                    Log.d("check","go to inputnumber2")
                    inputnumber2.requestFocus()
                }
            }
        })

        inputnumber2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty())
                {
                    inputnumber3.requestFocus()
                }
            }
        })

        inputnumber3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty())
                {
                    inputnumber4.requestFocus()
                }
            }
        })
        inputnumber4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty())
                {
                    inputnumber5.requestFocus()
                }
            }
        })

        inputnumber5.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty())
                {
                    inputnumber6.requestFocus()
                }
            }
        })
    }
}