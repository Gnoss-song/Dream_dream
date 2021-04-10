@file:Suppress("DEPRECATION")
package kr.co.material.basic

import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kr.co.material.basic.remainder.DiffUtilAndSwipeRefreshActivity
import kr.co.material.basic.remainder.PreferenceSettingsOneActivity
import kr.co.material.basic.remainder.PreferenceSettingsTwoActivity
import java.util.*

class RemainderListActivity : ListActivity() {
    private val actions = TreeMap<String, Intent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.DiffUtilAndSwipeRefresh", DiffUtilAndSwipeRefreshActivity::class.java)
        addActionMap("2.PreferenceSettingsOne", PreferenceSettingsOneActivity::class.java)
        addActionMap("3.PreferenceSettingsTwo", PreferenceSettingsTwoActivity::class.java)
        val keys = actions.keys
        val keyNames = keys.toTypedArray()
        listAdapter = ArrayAdapter<Any?>(this, R.layout.simple_list_item_1, keyNames)
    }

    private fun addActionMap(keyName: String, className: Class<*>) {
        actions[keyName] = Intent(this, className)
    }

    override fun onListItemClick(listView: ListView, v: View, position: Int, id: Long) {
        val intentName = listView.getItemAtPosition(position) as String
        startActivity(actions[intentName])
    }
}