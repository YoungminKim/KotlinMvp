package com.thejaljal.jaljal.sns

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.*
import test.ym.kotilnpj.utils.DebugUtils

/**
 * Created by no.1 on 2017-09-01.
 */
class Instagram: Activity() {
    companion object {
        private val TAG = "Instagram"
        private val CLIENT_ID = "cf42454ae6214e6a9f7254d674a33b44"
        private val CLIENT_SECRET = "0b515f1cfd654dbfa2dc1da44a54cc67"
        private val REDIRECT_URL ="http://www.thejaljal.com"

        @JvmStatic val INSTATGRAM = 9090
    }

    lateinit var mAuthUrl: String
    lateinit var wv: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mAuthUrl =  "https://api.instagram.com/oauth/authorize/?client_id=" + CLIENT_ID +"&redirect_uri=" + REDIRECT_URL + "&response_type=token"
        wv = WebView(this)
        setContentView(wv)

        val set:WebSettings = wv.settings
        set.javaScriptEnabled = true // javascript를 실행할 수 있도록 설정
        set.javaScriptCanOpenWindowsAutomatically = true
        set.loadsImagesAutomatically = true
        set.setSupportZoom(false) // 확대,축소 기능을 사용할 수 있도록 설정
        set.setSupportMultipleWindows(false)
        set.cacheMode = WebSettings.LOAD_NO_CACHE
        set.pluginState = WebSettings.PluginState.ON_DEMAND
        set.domStorageEnabled = true
        set.javaScriptCanOpenWindowsAutomatically = false
        wv.webViewClient = OAuthWebViewClient()
        wv.loadUrl(mAuthUrl)


    }

    inner class OAuthWebViewClient : WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
            DebugUtils.setLog(TAG, "Redirecting URL : " + url)

            if(url.startsWith(REDIRECT_URL)){
                val urls = url.split("=")
                val token = urls[1]
                val intent = Intent()
                intent.putExtra("token", token)
                setResult(Activity.RESULT_OK, intent)
                finish()
                return true
            }

            return false
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
            DebugUtils.setLog(TAG, "Page Error : " + error.toString())
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            DebugUtils.setLog(TAG, "Loading URL : "+ url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            DebugUtils.setLog(TAG, "onPageFinished URL : " + url)
        }
    }
}