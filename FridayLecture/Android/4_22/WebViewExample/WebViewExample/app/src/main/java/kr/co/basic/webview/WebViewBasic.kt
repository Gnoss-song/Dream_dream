package kr.co.basic.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kr.co.basic.webview.databinding.ActivityWebViewBasicBinding

class WebViewBasic : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBasicBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBasicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.webView){
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            //loadUrl("https://m.kbinsure.co.kr/")
            loadUrl("https://m.mapo.go.kr");
        }
    }
}