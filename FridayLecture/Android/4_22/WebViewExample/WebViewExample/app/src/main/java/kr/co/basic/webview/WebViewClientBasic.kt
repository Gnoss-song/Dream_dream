package kr.co.basic.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kr.co.basic.webview.databinding.ActivityWebViewClientBasicBinding

class WebViewClientBasic : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewClientBasicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_web_view_client_basic)
        binding = ActivityWebViewClientBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.urlAddress.setText("https://m.mapo.go.kr/")
        with(binding.webViewClient){
            webViewClient = MyWebViewClient()
            loadUrl("https://m.mapo.go.kr/")
        }
        webViewOptionSetting()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewOptionSetting() {
        val webViewSettings = binding.webViewClient.settings
        with(webViewSettings){
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            displayZoomControls = false
            setSupportZoom(false)
            setSupportMultipleWindows(false)
            useWideViewPort = true
            cacheMode = WebSettings.LOAD_NO_CACHE
        }

    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            binding.urlAddress.setText(url)
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()
            if (url.contains("mapo")) {
                return false // WebView 가 URL 을 처리한다는 의미
            }
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            return true //안드로이드 Intent 또는 다른 Activity 를 호출하여 처리가능
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.contains("mapo")) {
                return false
            }
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            return true
        }
    }
    override fun onBackPressed() {
        if (binding.webViewClient.canGoBack()) {
            binding.webViewClient.goBack()
        } else {
            super.onBackPressed()
        }
    }
}