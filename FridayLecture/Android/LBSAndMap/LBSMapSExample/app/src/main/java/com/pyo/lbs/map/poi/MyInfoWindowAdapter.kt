package com.pyo.lbs.map.poi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.pyo.lbs.map.R

/**
 * Created by pyo in soo on 2016-03-04.
 */
class MyInfoWindowAdapter(context: Context, private val poiResolver: Map<Marker, POIItem>) : GoogleMap.InfoWindowAdapter {

    private var infoView: View = LayoutInflater.from(context).inflate(R.layout.map_info_window, null)

    private  var titleView: TextView
    private  var subtitleView: TextView
    private  var descriptionView: TextView

    init {
        with(infoView){
            titleView = findViewById(R.id.text_title)
            subtitleView = findViewById(R.id.text_subtitle)
            descriptionView = findViewById(R.id.text_description)
        }
    }
    override fun getInfoWindow(marker: Marker): View? {
        return null
    }
    override fun getInfoContents(marker: Marker): View {
        val item = poiResolver[marker]
        titleView.text = item?.title
        subtitleView.text = item?.subtitle?.trim()
        descriptionView.text = item?.description?.trim()

        return infoView
    }
}