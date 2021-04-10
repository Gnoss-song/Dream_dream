@file:Suppress("DEPRECATION")
package kr.co.material.basic

import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kr.co.material.basic.materialwidget.*
import java.util.*

class MaterialBasicWidgetList : ListActivity() {
    private val actions = TreeMap<String?, Intent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.MaterialTextAndButtonActivity", MaterialTextAndButtonActivity::class.java)
        addActionMap("2.CardViewActivity", CardViewActivity::class.java)
        addActionMap("3.NavigationDrawerView", NavigationViewDrawerActivity::class.java)
        addActionMap("4.BottomNavigationView", BottomNavigationViewActivity::class.java)
        addActionMap("5.TabLayoutBasicActivity", TabLayoutBasicActivity::class.java)
        addActionMap("6.ViewPager2Activity", ViewPager2Activity::class.java)
        addActionMap("7.ViewPager2WithFragment", ViewPager2WithFragmentActivity::class.java)
        addActionMap("8.ViewPager2WithTabLayout", ViewPager2WithTabLayoutActivity::class.java)
        addActionMap("9.CoordinatorLayoutActivity", CoordinatorLayoutActivity::class.java)
        val keys: Set<String?> = actions.keys
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