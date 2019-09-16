package com.anilsenay.change_my_mind

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_profile_page.*

class ProfilePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        supportActionBar?.hide() //hide default bar

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val mTitle = toolbar.findViewById(R.id.toolbar_title) as TextView
        mTitle.setText("Profile")
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setNavigationIcon(R.drawable.back) //set icon

        //Click listener when click back button
        toolbar.setNavigationOnClickListener {
            finish()
        }

        //inflate the option menu
        toolbar.inflateMenu(R.menu.main)
        //change 3 dots icon
        toolbar.overflowIcon = ContextCompat.getDrawable(applicationContext, R.drawable.option_dots)

        //sort expandable list
        val listHeader = listOf("Started: New to Old")
        val numbersList = listOf(
            "Started: New to Old","Started: Old to New",
            "Updated: New to Old","Updated: Old to New",
            "Votes: Most to Least","Votes: Least to Most",
            "Popular: Most to Least","Popular: Least to Most")


        val listChild = HashMap<String, List<String>>()
        listChild.put(listHeader[0], numbersList)

        val expandableListAdapter = ExpandableListAdapter(this, listHeader, listChild)
        simple_expandable_listview.setAdapter(expandableListAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
