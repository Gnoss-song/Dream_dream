package com.pyo.lbs.map.friend

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.pyo.lbs.map.R
import com.pyo.lbs.map.common.RESTBuilder
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LocationRegistrationActivity : AppCompatActivity() {


    private lateinit var currentLocationBtn: Button
    private lateinit var insertBtn: Button
    private lateinit var latitudeET: EditText
    private lateinit var longitudeET: EditText
    private lateinit var nameET: EditText

    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            currentLocation()
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            Toast.makeText(applicationContext, "위치제공 허락을 해야 앱이 정상적으로 작동합니다", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
    }

    private fun checkMyPermissionLocation() {
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setRationaleMessage("맵을 사용하기 위해서는 위치제공 허락이 필요합니다")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .check()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_insert_layout)
        nameET = findViewById(R.id.myName)
        currentLocationBtn = findViewById(R.id.currentLocationBtn)
        latitudeET = findViewById(R.id.lagitudeValue)
        longitudeET = findViewById(R.id.longitudeValue)

        currentLocationBtn.setOnClickListener {
            if (!this::currentLoc.isInitialized) {
                checkMyPermissionLocation()
            }
        }
        insertBtn = findViewById(R.id.insertBtn)
        insertBtn.setOnClickListener {
            val myName = nameET.text.toString().trim()
            val lat = latitudeET.text.toString().trim()
            val lng = longitudeET.text.toString().trim()

            val rest = RESTBuilder.FriendBuilder.friendBuilder

            val call = rest.friendInsert(myName, lat, lng)

            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val responseJSON = response.body()!!.string()
                        runOnUiThread {
                            val resultJSON = JSONObject(responseJSON)
                            Toast.makeText(
                                applicationContext,
                                resultJSON.getString("insertResult"),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("Fail", t.stackTraceToString())
                    runOnUiThread {
                        Toast.makeText(applicationContext, "문제발생: $t", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
    private val listener = object : LocationListener {
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
        override fun onLocationChanged(location: Location) {
            currentLoc = location
            latitudeET.setText(currentLoc.latitude.toString())
            longitudeET.setText(currentLoc.longitude.toString())
        }
    }
    private lateinit var locationManager: LocationManager
    private lateinit var currentLoc: Location
    @SuppressLint("MissingPermission")
    private fun currentLocation() {
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if(lastLocation != null){
            latitudeET.setText(lastLocation.latitude.toString())
            longitudeET.setText(lastLocation.longitude.toString())
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 3f, listener)
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 3f, listener)
    }
    override fun onDestroy() {
        super.onDestroy()
        if (this::locationManager.isInitialized) locationManager.removeUpdates(listener)
    }
}