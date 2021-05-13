package com.example.practice02

import android.Manifest
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PictureActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@PictureActivity, LinearLayoutManager.HORIZONTAL, false)

    }

    fun goGallary(v : View) {
        var writePermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        var readPermission =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (writePermission == PackageManager.PERMISSION_DENIED
            || readPermission == PackageManager.PERMISSION_DENIED) { // 권한 없어서 요청
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        } else { // 권한 있음
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
                data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            }
            startActivityForResult(intent, 200)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val list = mutableListOf<Uri>().apply {
            if (requestCode == 200) {
                if (data?.clipData == null) {
                    if(data != null) {
                        this.add(Uri.parse(data.dataString))
                    }
                } else {
                    for(i in 0 until data.clipData!!.itemCount) {
                        this.add(data.clipData!!.getItemAt(i).uri)
                    }
                }
            }
        }
        recyclerView.adapter = PictureAdapter(list)
    }

    private inner class PictureAdapter(private val list: List<Uri>) : RecyclerView.Adapter<PictureAdapter.HolderView>() {

        inner class HolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView : ImageView = itemView.findViewById(R.id.imageView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView {
            return HolderView(LayoutInflater.from(parent.context).inflate(R.layout.image, parent, false))
        }

        override fun onBindViewHolder(holder: HolderView, position: Int) {
            holder.imageView.setImageURI(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

    }

}