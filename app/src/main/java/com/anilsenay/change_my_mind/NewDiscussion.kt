package com.anilsenay.change_my_mind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_new_discussion.*

class NewDiscussion : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_discussion)

        supportActionBar?.hide() //hide default bar

        //TOOLBAR
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        mTitle.setText("New Discussion")
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationIcon(R.drawable.back) //set icon

        //Click listener when click back button
        toolbar.setNavigationOnClickListener {
            finish()
        }
        //TOOLBAR END

        //category selection
        var categorySpinner: Spinner = findViewById(R.id.categorySpinner)
        ArrayAdapter.createFromResource(this, R.array.Categories,android.R.layout.simple_spinner_item).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter

        }

        //rounds selection
        var roundsSpinner: Spinner = findViewById(R.id.roundsSpinner)
        ArrayAdapter.createFromResource(this, R.array.Rounds,android.R.layout.simple_spinner_item).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            roundsSpinner.adapter = adapter

        }


    }

    fun clickButton(view: View){
        //This is how we got selected item
        //Toast.makeText(applicationContext, categorySpinner.selectedItem.toString(), Toast.LENGTH_LONG).show()
        print("Topic: ${topicEditText.text.toString()} \nCategory: ${categorySpinner.selectedItem.toString()} \nRounds: ${roundsSpinner.selectedItem.toString()} \nRespond Limit: ${hoursEditText.text.toString()} hours ${hoursEditText2.text.toString()} minutes\nVoting Period: ${daysEditText.text.toString()} days\nArgument: ${argumentTextArea.text.toString()}")
    }
}
