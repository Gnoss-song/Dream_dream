package com.pyo.lbs.map.poi

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.pyo.lbs.map.R
import com.pyo.lbs.map.databinding.ActivityGoogleMapAndSkpoiBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.net.URLEncoder
import java.util.*
import java.util.concurrent.TimeUnit

class GoogleMapAndSKPOIActivity : AppCompatActivity(), OnMapReadyCallback, OnMarkerClickListener,
    OnInfoWindowClickListener {

    inner class FindTMapPOI(var keyword: String) : Thread(){
        override fun run() {
            val targetURL: String
            val httpClient: OkHttpClient
            val response: Response
            //TMap 포이정보를 가져오는 URL
            val urlTMapPoi = "https://apis.openapi.sk.com/tmap/pois?version=1&searchKeyword=%s"
            var poiSearchResult: TMapPOISearchResult
            try {
                targetURL = String.format(urlTMapPoi, URLEncoder.encode(keyword, "UTF-8"))
                httpClient = OkHttpClient.Builder()
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .build()
                val request: Request = Request.Builder().url(targetURL)
                    .header("Accept", "application/json")
                    .header("appKey", resources.getString(R.string.sk_api_key))
                    .build()
                response = httpClient.newCall(request).execute()
                if (response.isSuccessful) {
                    poiSearchResult = Gson().fromJson(response.body!!.charStream(), TMapPOISearchResult::class.java)

                    runOnUiThread{
                        displayPOI(poiSearchResult)
                        val manager = LinearLayoutManager(applicationContext)
                        with(binding.poiRV){
                            layoutManager = manager
                            addItemDecoration(DividerItemDecoration(applicationContext, manager.orientation))
                            adapter = POIRecyclerViewAdapter(this@GoogleMapAndSKPOIActivity, poiList)
                        }
                    }
                } else {
                    Log.e("--------------", "요청실패")
                }
            } catch (e: Exception) {
                Log.e("--------------", e.toString())
            }
        }
    }
    private fun displayPOI(poiResult:TMapPOISearchResult){
        //현재 어댑터에 마커가 있다면 지운다
        clearAllMarker()

        for (item in poiResult.searchPoiInfo.pois.poi) {
            item.updatePOIData()
            val latLng = LatLng(item.latitude, item.longitude)

            //지도 및 어댑터에 마커 추가
            addMapPoiMarker(latLng, item)
        }
    }
    private val permissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            initPoiMap()
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
    private lateinit var binding: ActivityGoogleMapAndSkpoiBinding
    private lateinit var map: GoogleMap
    fun getCurrentMap() = map

    private var poiList = mutableListOf<POIItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_google_map_and_skpoi)
        binding = ActivityGoogleMapAndSkpoiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkMyPermissionLocation()
        } else {
            initPoiMap()
        }
    }
    private fun initPoiMap(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment?
        mapFragment!!.getMapAsync(this@GoogleMapAndSKPOIActivity)

        binding.searchBtn.setOnClickListener {
            val keyword = binding.keywordET.text.toString()
            if (keyword.isNotEmpty()) {
                FindTMapPOI(keyword).start()
            }else{
                binding.keywordET.requestFocus()
            }
        }
    }

    //리스트 뷰에 등록된 검색 정보를 모두 없앤다.
    private fun clearAllMarker() {
        for (currentIndex in 0 until poiList.size) {
            val item = poiList[currentIndex]
            val marker = markerResolver[item]
            marker!!.remove()
        }
        poiList.clear()
    }

    //포이 정보를 이용해 마커를 찾는다.
    var markerResolver: MutableMap<POIItem, Marker> = HashMap()

    //마커를 이용해 포이정보를 찾는다
    var poiResolver: MutableMap<Marker, POIItem> = HashMap()

    //마커 추가
    private fun addMapPoiMarker(position: LatLng, data: POIItem) {
        val options = MarkerOptions()
        with(options){
            position(position)
            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            title(data.title)
            snippet(data.subtitle)
            draggable(true)
        }
        val marker = map.addMarker(options)
        markerResolver[data] = marker
        poiResolver[marker] = data
        poiList.add(data)
    }

    private fun moveMap(lat: Double, lng: Double) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 15f))
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        with(map){
            uiSettings.isZoomControlsEnabled = true
            setOnMarkerClickListener(this@GoogleMapAndSKPOIActivity)
            setOnInfoWindowClickListener(this@GoogleMapAndSKPOIActivity)
            setInfoWindowAdapter(MyInfoWindowAdapter(this@GoogleMapAndSKPOIActivity, poiResolver))
        }
        moveMap(37.477027, 126.963684)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        marker.showInfoWindow()
        return true
    }

    override fun onInfoWindowClick(marker: Marker) {
        Toast.makeText(this, "Info window 클릭함!", Toast.LENGTH_SHORT).show()
        marker.hideInfoWindow()
    }
}