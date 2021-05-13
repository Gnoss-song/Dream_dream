package kr.co.ssong.lbs

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : ListActivity() {
    private val actionMap = mutableMapOf<String, Intent>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addActionMap("1.네트웍 및 LBS 검증 후 현재위치 찾기", SplashActivity::class.java)
//        addActionMap("2.POI 정보 얻기", GoogleMapAndSKPOIActivity::class.java)
//        addActionMap("3.친구찾기", FindFriendMainActivity::class.java)
//        addActionMap("4.GoogleMap Circle Draw", GoogleMapCircleActivity::class.java)

        val keys = actionMap.keys
        val keyNames = keys.toTypedArray()

        listAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, keyNames)
    }
    private fun addActionMap(keyName: String, className: Class<*>) {
        actionMap[keyName] = Intent(this, className)
    }

    override fun onListItemClick(listView: ListView, v: View?, position: Int, id: Long) {
        val intentName = listView.getItemAtPosition(position) as String
        startActivity(actionMap.get(key = intentName))
    }
    private lateinit var permissionCheck: MapoPermissionCheckActivity
    override fun onResume() {
        super.onResume()
        if (!MapoPreferenceManager.getInstance().isPermission) {
            permissionCheck()
        }
    }


    private fun permissionCheck() {
        permissionCheck = MapoPermissionCheckActivity(applicationContext, this)
        if (!permissionCheck.checkPermission()) {
            permissionCheck.requestPermission()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (!permissionCheck.permissionResult(requestCode,grantResults)) {
            permissionCheck.requestPermission()
        }else{
            MapoPreferenceManager.getInstance().isPermission = true
        }
    }
}
