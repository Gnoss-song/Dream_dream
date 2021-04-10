@file:Suppress("DEPRECATION")

package kr.co.material.basic


import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kr.co.material.basic.materialbase.MaterialBasicListActivity
import kr.co.material.basic.total.MaterialGirlGroupActivity
import java.util.*

class MainListActivity : ListActivity() {
    private val actions = TreeMap<String, Intent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.MaterialBasic", MaterialBasicListActivity::class.java)
        addActionMap("2.MaterialBasicWidgetUsing", MaterialBasicWidgetList::class.java)
        addActionMap("3.RemainderListActivity", RemainderListActivity::class.java)
        addActionMap("4.MaterialGirlsGroup", MaterialGirlGroupActivity::class.java)
        val keys: Set<String> = actions.keys
        val keyNames = keys.toTypedArray()
        listAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, keyNames)
    }
    private fun addActionMap(keyName: String, className: Class<*>) {
        actions[keyName] = Intent(this, className)
    }
    override fun onListItemClick(listView: ListView, v: View, position: Int, id: Long) {
        val intentName = listView.getItemAtPosition(position) as String
        startActivity(actions[intentName])
    }
}