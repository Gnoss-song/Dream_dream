package com.pyo.lbs.map.polyline

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.gms.tasks.Task
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.pyo.lbs.map.R
import com.pyo.lbs.map.databinding.ActivityGoogleMapPolyLineBinding


class GoogleMapPolyLineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleMapPolyLineBinding

    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            startPolylineListenerRegistration()
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

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_google_map_poly_line)

        binding = ActivityGoogleMapPolyLineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGoogleMap()
    }

    private lateinit var locationRequest: LocationRequest
    private fun initGoogleMap() {
        val polyLineMap =
            supportFragmentManager.findFragmentById(R.id.polyLineMap)
                    as SupportMapFragment

        //OnMapReadyCallback 을 실행하여 필드에 GoogleMap 을 등록
        polyLineMap.getMapAsync {
            mMap = it
        }
        locationRequest = LocationRequest.create().apply {
            interval = 3000
            fastestInterval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            maxWaitTime = 3000
        }
        checkMyPermissionLocation()
    }

    private lateinit var currentLocation: Location
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private var marker: Marker? = null
    private lateinit var startingCoordinate: LatLng
    private lateinit var finalCoordinate: LatLng

    @SuppressLint("MissingPermission")
    private fun startPolylineListenerRegistration() {
        mFusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this@GoogleMapPolyLineActivity)
        val taskLoc = mFusedLocationClient.lastLocation
        if (taskLoc.isSuccessful) {
            currentLocation = taskLoc.result
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest,
                                        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location = locationResult.locations[0]
                val latitude: Double = location.latitude
                val longitude: Double = location.longitude

                if (marker != null) {
                    marker?.remove()
                }
                currentLocation = location
                val markerOptions = MarkerOptions()

                markerOptions.position(LatLng(latitude, longitude))
                marker = mMap.addMarker(markerOptions)

                mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            currentLocation.latitude,
                            currentLocation.longitude
                        ), 18f
                    )
                )
                finalCoordinate = LatLng(latitude, longitude)
                drawPolyline()
                startingCoordinate = LatLng(latitude, longitude)
            }

            override fun onLocationAvailability(p0: LocationAvailability) {
                super.onLocationAvailability(p0)
            }
        }, Looper.getMainLooper())
    }

    private val polylineList = mutableListOf<Polyline>()
    private fun drawPolyline() {
        if(this::startingCoordinate.isInitialized){
            var options = PolylineOptions().add(startingCoordinate).add(finalCoordinate).width(15f).color(Color.BLACK).geodesic(true)
            polylineList.add(mMap.addPolyline(options))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startingCoordinate, 18f))
        }
    }
}