package io.bibuti.sflix

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<WebView>(R.id.webRoot)?.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(wView: WebView, url: String): Boolean {
                    return if (url == "https://sflix.to/") //check if that's a url you want to load internally
                    {
                        loadUrl("https://sflix.to/home")
                        true
                    } else {
                        false //Let the system handle it
                    }
                }
            }
            loadUrl("https://sflix.to/home")
        }
    }

    override fun onBackPressed() {
        findViewById<WebView>(R.id.webRoot)?.apply {
            if (canGoBack()) {
                goBack()
            } else {
                super.onBackPressed()
            }
        }
    }
}