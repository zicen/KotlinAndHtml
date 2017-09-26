package com.zhenquan.kotlinandhtml

import android.content.Context
import android.webkit.JavascriptInterface
import org.jetbrains.anko.toast

/**
 * Created by zhenquan on 2017/9/26.
 * kotlin与H5通信桥梁类
 */
class JavaScriptMethods {
    private var mContext :Context?=null
    constructor(context:Context){
        this.mContext = context
    }


    /**
     * android4.2之后，必须加上@JavascriptInterface注解，H5才能调用kotlin的方法
     */
    @JavascriptInterface
    fun showToast(json:String){
        mContext?.let {
            it.toast(json)
        }
    }
}