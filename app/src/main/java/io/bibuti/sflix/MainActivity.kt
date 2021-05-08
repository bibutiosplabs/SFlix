package io.bibuti.sflix

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<WebView>(R.id.webRoot)?.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            settings.domStorageEnabled = true
            settings.loadsImagesAutomatically = true
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            settings.useWideViewPort = true
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(wView: WebView, url: String): Boolean {
                    Log.d(TAG, "shouldOverrideUrlLoading: -> $url")
                    return if (url == "https://sflix.to/") //check if that's a url you want to load internally
                    {
                        loadUrl("https://sflix.to/home")
                        true
                    } else {
                        false //Let the system handle it
                    }
                }
            }
            webChromeClient = WebChromeClient()
            loadUrl("https://sflix.to/home")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        findViewById<WebView>(R.id.webRoot)?.apply {
            saveState(outState)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        findViewById<WebView>(R.id.webRoot)?.apply {
            restoreState(savedInstanceState)
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