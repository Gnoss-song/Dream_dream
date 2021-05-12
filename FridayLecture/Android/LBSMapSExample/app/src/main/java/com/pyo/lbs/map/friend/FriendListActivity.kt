package com.pyo.lbs.map.friend

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pyo.lbs.map.R
import com.pyo.lbs.map.common.RESTBuilder
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class FriendListActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var friendList: ArrayList<String> = ArrayList()
    private lateinit var friendListAdapter: ArrayAdapter<String>
    private lateinit var friendItems: ListView

    override fun onMapReady(googleMap: GoogleMap) {
        val SEOUL = LatLng(37.56, 126.97)
        mMap = googleMap
        val markerOptions = MarkerOptions()
        markerOptions.position(SEOUL)
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
        mMap.addMarker(markerOptions)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_names_find_friend)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        friendItems = findViewById(R.id.friend_list)


        friendListAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1,
            friendList
        )

        friendItems.adapter = friendListAdapter

        friendItems.setOnItemClickListener { _, viewItem, _, _ ->

            val currentTV: TextView = viewItem as TextView
            val name: String = currentTV.text.toString()
            val rest = RESTBuilder.FriendBuilder.friendBuilder
            val call = rest.findFriend(name)

            call.enqueue(object : Callback<CoordinateJSON> {
                override fun onResponse(
                    call: Call<CoordinateJSON>,
                    response: Response<CoordinateJSON>
                ) {
                    if (response.isSuccessful) {
                        val coordinate = response.body() as CoordinateJSON

                        runOnUiThread {
                            mMap.addMarker(
                                MarkerOptions()
                                    .title(coordinate.name)
                                    .position(LatLng(coordinate.latitude, coordinate.longitude))
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin))
                                    .snippet(coordinate.name + "님의 현채 위치 입니다")
                            )
                            val cameraPosition: CameraPosition = CameraPosition.Builder()
                                .target(LatLng(coordinate.latitude, coordinate.longitude))
                                .zoom(15f)
                                .tilt(10f)
                                .build()
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                        }
                    }
                }

                override fun onFailure(call: Call<CoordinateJSON>, t: Throwable) {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "문제발생: $t", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        /**
         * 등록된 친구들 이름 가져오기
         */
    }

    override fun onResume() {
        super.onResume()
        val rest = RESTBuilder.FriendBuilder.friendBuilder
        val call = rest.findFriends()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val resultData = response.body()!!.string()
                    val jsonNames = JSONArray(resultData)
                    for (index in 0 until jsonNames.length()) {
                        friendList.add(jsonNames.getString(index))
                    }
                    runOnUiThread {
                        if (friendList.size > 0) {
                            Toast.makeText(this@FriendListActivity, "친구 목록 가져오기 성공", Toast.LENGTH_SHORT).show();
                            friendListAdapter.addAll(friendList)
                            friendListAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                runOnUiThread {
                    Toast.makeText(applicationContext, "문제발생: $t", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}