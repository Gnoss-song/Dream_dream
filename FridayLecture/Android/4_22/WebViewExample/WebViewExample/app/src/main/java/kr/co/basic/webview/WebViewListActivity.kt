@file:Suppress("DEPRECATION")

package kr.co.basic.webview

import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.*

class WebViewListActivity : ListActivity() {
    private val actions = TreeMap<String, Intent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.WebViewBasic", WebViewBasic::class.java)
        addActionMap("2.WebViewClient", WebViewClientBasic::class.java)
        addActionMap("3.WebViewLocalHtml", WebViewLocalHTML::class.java)
        addActionMap("4.WebViewFromJS", WebViewFromJS::class.java)
        addActionMap("5.WebViewTOJS", WebViewTOJS::class.java)
        val keyNames = actions.keys.toTypedArray()
        listAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, keyNames)
    }

    private fun addActionMap(keyName: String, className: Class<*>) {
        actions[keyName] = Intent(this, className)
    }

    public override fun onListItemClick(listView: ListView, item: View, position: Int, id: Long) {
        val keyName = listView.getItemAtPosition(position) as String
        startActivity(actions[keyName])
    }
}