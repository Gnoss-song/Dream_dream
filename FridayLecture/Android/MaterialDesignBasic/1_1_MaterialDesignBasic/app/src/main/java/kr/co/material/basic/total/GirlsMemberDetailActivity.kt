package kr.co.material.basic.total

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout
import kr.co.material.basic.R
import java.util.*

class GirlsMemberDetailActivity : AppCompatActivity() {
    var memberImage: ImageView? = null
    var toolbar: Toolbar? = null
    var collapsingToolbar: CollapsingToolbarLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_girl_group_detail)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val girlImageRes = intent.getIntExtra("memberImage", -1)
        val memberName = intent.getStringExtra("memberName")
        collapsingToolbar = findViewById(R.id.collapsing_toolbar)
        memberImage = findViewById(R.id.member_image)
        collapsingToolbar?.title = memberName
        memberImage?.setImageResource(girlImageRes)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.sample_actions, menu)
        return true
    }
}