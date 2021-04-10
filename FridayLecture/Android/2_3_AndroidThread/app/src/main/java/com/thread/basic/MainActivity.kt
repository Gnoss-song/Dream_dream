@file:Suppress("DEPRECATION")

package com.thread.basic

import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*


class MainActivity : ListActivity() {

    private val actions: TreeMap<String, Intent> = TreeMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.SimpleThread(Thread)", ThreadSimpleActivity::class.java)
        addActionMap("2.MessageHandlerThread", HandlerMessageThreadActivity::class.java)
        addActionMap("3.PostHandlerThread", HandlerPostThreadActivity::class.java)

        val keys: Set<String> = actions.keys
        val keyNames: Array<String> = keys.toTypedArray()
        listAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, keyNames)
    }

    private fun addActionMap(keyName: String, className: Class<*>) {
        actions[keyName] = Intent(this, className)
    }

    override fun onListItemClick(listView: ListView, item: View, position: Int, id: Long) {
        val keyName = listView.getItemAtPosition(position) as String
        startActivity(actions[keyName])
    }
}
