package kr.co.mapo.basicwidget

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kr.co.mapo.basicwidget.databinding.ActivityMainBinding
import java.util.*

abstract class BaseActivity : ListActivity() {
    private var binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val actions = TreeMap<String,Intent>()
    protected override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        displayActivityList()
        val keys = actions.keySet()
        var keyNames = arrayOfNulls<String>(keys.size)
        keyNames = keys.toTypedArray<String>()
        listAdapter(ArrayAdapter(this,android.R.layout.simple_list_item_1,keyNames))
    }
    abstract fun displayActivityList()
    fun addActionMap(keyName:String,className:Class<*>){
        actions.put(keyName, Intent(this,className))
    }
    override fun onListItemClick(listView: ListView, item: View, position:Int, id:Long){
        val keyName = listView.getItemAtPosition(position) as String
        startActivity(actions.get(keyName))
    }

    abstract fun addActionMap(keyName: String, className: EventPriorityActivity.Companion)
}