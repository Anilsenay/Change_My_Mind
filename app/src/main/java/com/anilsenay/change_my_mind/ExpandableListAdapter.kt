package com.anilsenay.change_my_mind

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.appcompat.widget.AppCompatTextView

class ExpandableListAdapter(val context: Context, val listOfHeaderData: List<String>, val listOfChildData: HashMap<String, List<String>>): BaseExpandableListAdapter(){
    override fun getGroup(p0: Int): Any {
        return listOfHeaderData[p0]
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val headerTitle = getGroup(p0) as String
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_header, p3, false)
        val listHeaderText = view.findViewById<AppCompatTextView>(R.id.list_header_list) as AppCompatTextView

        listHeaderText.setTypeface(null, Typeface.BOLD)
        listHeaderText.text = headerTitle

        return view
    }

    override fun getChildrenCount(p0: Int): Int {
        return listOfChildData[listOfHeaderData[p0]]!!.size
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return listOfChildData[listOfHeaderData[p0]]!![p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val childText = getChild(p0, p1) as String
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item, p4, false)
        val listItemText = view.findViewById<AppCompatTextView>(R.id.list_item_text) as AppCompatTextView

        listItemText.text = childText

        return view
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun getGroupCount(): Int {
        return listOfHeaderData.size
    }

}