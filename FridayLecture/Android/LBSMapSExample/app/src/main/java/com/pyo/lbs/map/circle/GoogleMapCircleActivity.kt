package com.pyo.lbs.map.circle

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.pyo.lbs.map.R


class GoogleMapCircleActivity : AppCompatActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map_circle)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.circleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    companion object {
        var centerLatLng = LatLng(37.4950058, 127.0635781)
        val seoulStation: CameraPosition = CameraPosition.builder()
            .target(centerLatLng)
            .zoom(12f)
            .build()

    }
    override fun onMapReady(map: GoogleMap) {
        map.moveCamera(CameraUpdateFactory.newCameraPosition(seoulStation))
        map.addCircle(
            CircleOptions()
                .center(centerLatLng)
                .radius(5000.0)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(64, 0, 255, 0))
        )
    }
}