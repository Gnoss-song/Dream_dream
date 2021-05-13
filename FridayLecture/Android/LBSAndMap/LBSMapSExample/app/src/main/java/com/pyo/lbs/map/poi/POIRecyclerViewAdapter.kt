package com.pyo.lbs.map.poi

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.pyo.lbs.map.R

class POIRecyclerViewAdapter(private val owner: GoogleMapAndSKPOIActivity, private val poiList: MutableList<POIItem>): RecyclerView.Adapter<POIRecyclerViewAdapter.PoiHolder>() {

    inner class PoiHolder(view: View): RecyclerView.ViewHolder(view){
        var root: View = view
        var poiName: TextView = view.findViewById(R.id.poiName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.poi_item_layout, parent, false)
        return PoiHolder(view)
    }

    override fun onBindViewHolder(holder: PoiHolder, position: Int) {
        val poiItem = poiList[position]
        holder.poiName.text = poiItem.toString()

        holder.root.setOnClickListener {
            val marker = owner.markerResolver[poiItem]
            val update = CameraUpdateFactory.newLatLngZoom(marker!!.position, 15f)
            owner.getCurrentMap().animateCamera(update, object : GoogleMap.CancelableCallback {
                override fun onFinish() {
                    marker.showInfoWindow()
                }
                override fun onCancel() {}
            })
        }
    }

    override fun getItemCount() = poiList.size
}