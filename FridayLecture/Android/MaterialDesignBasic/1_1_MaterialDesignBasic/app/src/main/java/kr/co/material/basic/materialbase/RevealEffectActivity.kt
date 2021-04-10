package kr.co.material.basic.materialbase

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import kr.co.material.basic.R
import kotlin.math.max

class RevealEffectActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var revealLayout: LinearLayout
    private lateinit var searchBox: SearchView
    private var hidden = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reveal_effect)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Reveal Effect"
        revealLayout = findViewById(R.id.revealLayout)

        revealLayout.visibility = View.INVISIBLE

        searchBox = findViewById(R.id.searchBox)

        searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(inputQuery: String): Boolean {
                handleSoftKeyboard(searchBox)
                searchBox.setQuery("", false)
                Toast.makeText(this@RevealEffectActivity, inputQuery, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    fun handleSoftKeyboard(view: View?) {
        val imeManager = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
        imeManager.hideSoftInputFromWindow(view!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menus, menu)
        if (hidden) {
            menu.findItem(R.id.action_search).setIcon(R.drawable.ic_baseline_search_24)
        } else {
            menu.findItem(R.id.action_search).setIcon(R.drawable.ic_close)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val cx = revealLayout.right
                val cy = revealLayout.top
                displayEffect(revealLayout, cx, cy)
                return true
            }
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayEffect(layout: LinearLayout, cx: Int, cy: Int) {
        val radius = max(layout.width, layout.height)
        if (hidden) {
            val circularReveal = ViewAnimationUtils.createCircularReveal(layout, cx, cy, 0f, radius.toFloat())
            layout.visibility = View.VISIBLE
            circularReveal.start()
            invalidateOptionsMenu()
            hidden = false
        } else {
            val circularReveal = ViewAnimationUtils.createCircularReveal(layout, cx, cy, radius.toFloat(), 0f)
            circularReveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    layout.visibility = View.INVISIBLE
                    hidden = true
                    invalidateOptionsMenu()
                }
            })

            circularReveal.start()
        }
    }
}