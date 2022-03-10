package com.asm.logindemo.utils

import android.os.Handler
import android.os.Looper

object HandlerUtil {

    private val mainHandler = Handler(Looper.getMainLooper())

    fun isMainThread(): Boolean = mainHandler.looper == Looper.myLooper()

    @JvmStatic
    fun post(r: Runnable) {
        mainHandler.post(r)
    }

    @JvmStatic
    fun postDelayed(r: Runnable, delayTime: Int) {
        mainHandler.postDelayed(r, delayTime.toLong())
    }

    @JvmStatic
    fun runOnUiThread(runnable: Runnable) {
        if (isMainThread()) {
            runnable.run()
        } else {
            mainHandler.post(runnable)
        }
    }

    @JvmStatic
    fun mainHandler(): Handler {
        return mainHandler
    }

    @JvmStatic
    fun removeCallbacks(handler: Handler, callback: Runnable) {
        if (handler.looper == Looper.myLooper()) {
            handler.removeCallbacks(callback)
        } else {
            handler.post { handler.removeCallbacks(callback) }
        }
    }

    @JvmOverloads
    @JvmStatic
    fun removeMessages(handler: Handler, what: Int, obj: Any? = null) {
        if (handler.looper == Looper.myLooper()) {
            handler.removeMessages(what, obj)
        } else {
            handler.post { handler.removeMessages(what, obj) }
        }
    }
}