package com.anilsenay.change_my_mind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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

    fun signUp(){

    }
}
