package com.anilsenay.change_my_mind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()

        supportActionBar!!.hide()

        supportActionBar?.hide() //hide default bar

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        mTitle.setText("Register")
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationIcon(R.drawable.back) //set icon

        //Click listener when click back button
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    fun signUp(view: View){
        if(reg_password_editText.text.toString().compareTo(reg_confirm_password_editText.text.toString()) == 0){
            val userEmail = reg_email_editText.text.toString()
            val userPass = reg_password_editText.text.toString()
            mAuth.createUserWithEmailAndPassword(userEmail, userPass).addOnCompleteListener { task ->

                if(task.isSuccessful){
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish() // to kill this activity. So user can not go back this page again when pressed back button.
                }
            }.addOnFailureListener { e ->

                if(e != null){
                    Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }
        }else{
            Toast.makeText(applicationContext, "Passwords do not match!", Toast.LENGTH_LONG).show()
        }
    }
}
