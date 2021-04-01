package kr.co.mapo.basicwidget

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.AbstractMap

abstract class BaseActivity : ListActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val actions = TreeMap<String,Intent>()

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        displayActivityList()
        val keys = setOf<String>(actions.toString())
        var keyNames = arrayOfNulls<String>(keys.size)
        keyNames = keys.toTypedArray<String?>()
        listAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, keyNames)
    }
    abstract fun displayActivityList()
    fun addActionMap(keyName:String, className:Class<*>) {
        actions[keyName] = Intent(this, className)
    }
    override fun onListItemClick(listView:ListView, item:View, position:Int, id:Long) {
        val keyName = listView.getItemAtPosition(position) as String
        startActivity(actions[keyName])
    }
}