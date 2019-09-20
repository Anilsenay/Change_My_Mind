package com.anilsenay.change_my_mind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_discussion_page.*

class DiscussionPage : AppCompatActivity() {

    var round1 = false //for testing layout visibilities
    var round2 = false
    var round3 = false
    var round4 = false
    var round5 = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discussion_page)

        // --> toolbar
        supportActionBar?.hide() //hide default bar

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        mTitle.setText("Discussion")
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationIcon(R.drawable.back) //set icon

        //Click listener when click back button
        toolbar.setNavigationOnClickListener {
            finish()
        }
        // --> end of toolbar

        //checkRounds()

    }
    //testing layout visibilities
     /*
    fun checkRounds(){
        if(!round1)

        if(!round2)
            round2_layout.visibility = View.GONE
        if(!round3)
            round3_layout.visibility = View.GONE
        if(!round4)
            round4_layout.visibility = View.GONE
        if(!round5)
            round5_layout.visibility = View.GONE
    }*/
}
