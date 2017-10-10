package com.zhenquan.kotlinandhtml

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private val webview by lazy { find<WebView>(R.id.webview_main) }
    private val btn1 by lazy { find<Button>(R.id.btn1) }
    private val btn2 by lazy { find<Button>(R.id.btn2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview.settings.javaScriptEnabled = true
        webview.setWebViewClient(MyWebVuewClient())
        webview.setWebChromeClient(MyWebChromeClient())


        //设置H5与kotlin之间的通信桥梁
        webview.addJavascriptInterface(JavaScriptMethods(this), "jsInterface")

        webview.loadUrl("file:///android_asset/index.html")

        btn1.onClick {
            webview.loadUrl("javascript:kotlincalljs()")
        }

        btn2.onClick {
            webview.loadUrl("javascript:kotlincalljswith(" + "'https://github.com/zicen'" + ")")
        }

    }

    inner private class MyWebVuewClient : WebViewClient() {
        //页面加载完成时调用
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            /*  //kotln调用H5方法
              var json = JSONObject()
              json.put("name", "Kotlin")
              webview.loadUrl("javascript:kotlincalljswith(" + json.toString() + ")")*/
            toast("页面加载完成！")
        }
    }

    private class MyWebChromeClient : WebChromeClient() {
        //加载进度条
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
        }
    }
}
