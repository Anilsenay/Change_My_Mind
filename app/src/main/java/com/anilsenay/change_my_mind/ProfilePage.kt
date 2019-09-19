package com.anilsenay.change_my_mind

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
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

        //category selection

        var sortingSpinner: Spinner = findViewById(R.id.sortingSpinner)
        ArrayAdapter.createFromResource(this, R.array.Sort,android.R.layout.simple_spinner_item).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            sortingSpinner.adapter = adapter
        }
        /*
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


        simple_expandable_listview!!.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            //Toast.makeText(applicationContext, "Clicked: " + (numbersList as ArrayList<String>)[groupPosition] + " -> " + numbersList[(listHeader as ArrayList<String>)[groupPosition]]!!.get(childPosition), Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext, "Clicked: " + numbersList[childPosition], Toast.LENGTH_SHORT).show()

            false
        }


        // setting height for Expandable ListView (solution for scroll in scroll situation)
        simple_expandable_listview.setOnGroupClickListener(ExpandableListView.OnGroupClickListener { parent, v, groupPosition, id ->
            setListViewHeight(parent, groupPosition)
            false
        }) */
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // setting height for Expandable ListView (solution for scroll in scroll situation)
    private fun setListViewHeight(
        listView: ExpandableListView,
        group: Int
    ) {
        val listAdapter = listView.expandableListAdapter as ExpandableListAdapter
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(
            listView.width,
            View.MeasureSpec.EXACTLY
        )
        for (i in 0 until listAdapter.groupCount) {
            val groupItem = listAdapter.getGroupView(i, false, null, listView)
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)

            totalHeight += groupItem.measuredHeight

            if (listView.isGroupExpanded(i) && i != group || !listView.isGroupExpanded(i) && i == group) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val listItem = listAdapter.getChildView(
                        i, j, false, null,
                        listView
                    )
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)

                    totalHeight += listItem.measuredHeight

                }
            }
        }

        val params = listView.layoutParams
        var height = totalHeight + listView.dividerHeight * (listAdapter.groupCount - 1)
        if (height < 10)
            height = 200
        params.height = height
        listView.layoutParams = params
        listView.requestLayout()

    }

}
