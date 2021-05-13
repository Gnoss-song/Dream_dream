package kr.co.basic.webview

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebViewLocalHTML : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_settings)
        val webView = findViewById<WebView>(R.id.webView)
        webView.loadUrl("file:///android_asset/test_1.html")
    }
}