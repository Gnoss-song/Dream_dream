package kr.co.basic.webview

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kr.co.basic.webview.databinding.ActivityWebViewJavaScriptBinding

class WebViewFromJS : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewJavaScriptBinding

    @SuppressLint("SetJavascriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_web_view_java_script)
        binding = ActivityWebViewJavaScriptBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //WebView 자바 스크립트 허용
        with(binding.webView){
            loadUrl("file:///android_asset/test_2.html")
            settings.javaScriptEnabled = true
            //WebView 에 Java Object Injecting
            addJavascriptInterface(JavaScriptToAndroid(), "clientObject")
        }

    }
    inner class JavaScriptToAndroid {
        private var alertDialog: AlertDialog? = null
        @JavascriptInterface
        fun showAndroidDialog(fromJSMessage: String?) {
            alertDialog = AlertDialog.Builder(this@WebViewFromJS)
                    .setTitle("JS To Android")
                    .setMessage(fromJSMessage)
                    //.setPositiveButton(android.R.string.ok) { dialog: DialogInterface?, which: Int -> alertDialog!!.dismiss() }.create()
                    .setPositiveButton(android.R.string.ok) { _, _ -> alertDialog!!.dismiss() }.create()
            alertDialog!!.show()
        }
    }

    /**
     * 자바 스크립트 해제 onStop 에서도 가능
     */
    override fun onDestroy() {
        //웹뷰에서 자바 스크립트 삭제
        binding.webView.removeJavascriptInterface("clientObject")
        super.onDestroy()
    }
}