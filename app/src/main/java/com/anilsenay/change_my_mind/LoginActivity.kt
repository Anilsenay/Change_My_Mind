package com.anilsenay.change_my_mind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*


class LoginActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        //checking if user already logged in
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        supportActionBar!!.hide()

    }

    fun signUp(view: View){
        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun signIn(view: View){
        val userEmail = email_editText.text.toString()
        val userPass = password_editText.text.toString()
        mAuth.signInWithEmailAndPassword(userEmail,userPass).addOnCompleteListener { task ->

            if(task.isSuccessful){

                Toast.makeText(applicationContext, "Signed Successful.", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish() // to kill this activity. So user can not go back this page again when pressed back button.

            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}
