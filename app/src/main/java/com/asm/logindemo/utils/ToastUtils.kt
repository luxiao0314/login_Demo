package com.asm.logindemo.utils

import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import com.asm.logindemo.BaseApplication
import com.asm.logindemo.BuildConfig
import java.util.*

object ToastUtils {

    @JvmStatic
    fun showShortMessage(msg: String?) {
        showMessage(msg, Toast.LENGTH_SHORT)
    }

    @JvmStatic
    fun showShortMessage(msg: String?, gravity: Int) {
        showMessage(msg, Toast.LENGTH_SHORT, gravity)
    }

    @JvmStatic
    fun showLongMessage(msg: String?) {
        showMessage(msg, Toast.LENGTH_LONG)
    }

    @JvmStatic
    fun showLongMessage(msg: String?, gravity: Int) {
        showMessage(msg, Toast.LENGTH_LONG, gravity)
    }

    @JvmStatic
    fun showDebugLongMessage(msg: String?) {
        if (BuildConfig.DEBUG) {
            showMessage(msg, Toast.LENGTH_LONG)
        }
    }

    @JvmStatic
    fun showDebugToast(msg: String?) {
        if (BuildConfig.DEBUG && !TextUtils.isEmpty(msg)) {
            showMessage(msg, Toast.LENGTH_LONG)
        }
    }

    private fun showMessage(msg: String?, len: Int, gravity: Int = Gravity.CENTER) {
        HandlerUtil.post {
            val context = BaseApplication.context
            val toast = Toast.makeText(context, msg, len)
            toast.setGravity(gravity, 0, 0)
            toast.show()
        }
    }

    fun showToastWithTime(toast: Toast, cnt: Long) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                toast.show()
            }
        }, 0, 3000)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                toast.cancel()
                timer.cancel()
            }
        }, cnt)
    }
}