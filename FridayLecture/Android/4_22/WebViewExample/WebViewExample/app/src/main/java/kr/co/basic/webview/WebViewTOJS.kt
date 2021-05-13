package kr.co.basic.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kr.co.basic.webview.databinding.ActivityWebViewJavaScript2Binding

class WebViewTOJS : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewJavaScript2Binding

    @SuppressLint("SetJavascriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_web_view_java_script2)
        binding = ActivityWebViewJavaScript2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.webView) {
            loadUrl("file:///android_asset/test_3.html")
            settings.javaScriptEnabled = true
            webChromeClient = object : WebChromeClient() {
                override fun onReceivedTitle(view: WebView, header: String) {
                    supportActionBar?.title = header
                }
            }
        }
        binding.messageED.setOnEditorActionListener { _, actionId, _ ->
            val handle = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                sendMessage()
                !handle
            }
            handle
        }
    }

    private fun sendMessage() {
        binding.webView.evaluateJavascript("""displayFromWebView("${binding.messageED.text}")""", null)
    }
}