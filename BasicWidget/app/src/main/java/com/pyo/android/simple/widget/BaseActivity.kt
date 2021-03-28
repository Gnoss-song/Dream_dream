package com.pyo.android.simple.widget
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.TreeMap

abstract class BaseActivity:ListActivity() {
    private val actions = TreeMap<String, Intent>()
    protected fun onCreate(savedInstanceState:Bundle) {
        super.onCreate(savedInstanceState)
        displayActivityList()
        val keys = actions.keySet()
        val keyNames = arrayOfNulls<String>(keys.size)
        keyNames = keys.toTypedArray<String>()
        setListAdapter(ArrayAdapter(this,
            android.R.layout.simple_list_item_1, keyNames))
    }
    abstract fun displayActivityList()
    fun addActionMap(keyName:String, className:Class<*>) {
        actions.put(keyName, Intent(this, className))
    }
    fun onListItemClick(listView:ListView, item:View, position:Int, id:Long) {
        val keyName = listView.getItemAtPosition(position) as String
        startActivity(actions.get(keyName))
    }
}