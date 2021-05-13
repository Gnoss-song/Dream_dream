@file:Suppress("DEPRECATION")

package kr.co.pyo.rest

import android.R
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import kr.co.pyo.rest.glide.GlideViewPagerActivity
import kr.co.pyo.rest.okhttp.BloodRESTActivity
import kr.co.pyo.rest.okhttp_retrofit.BloodOkHttpRetrofitRESTActivity
import kr.co.pyo.rest.retrofit.BloodRetrofitRESTActivity
import java.util.*

class MainActivity : ListActivity() {
    private val actions = TreeMap<String, Intent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.Blood OkHttp", BloodRESTActivity::class.java)
        addActionMap("2.Blood Retrofit ", BloodRetrofitRESTActivity::class.java)
        addActionMap("3.Blood OkHttp + Retrofit", BloodOkHttpRetrofitRESTActivity::class.java)
        addActionMap("4.Girls Glide", GlideViewPagerActivity::class.java)

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