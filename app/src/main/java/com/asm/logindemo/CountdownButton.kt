package com.asm.logindemo

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class CountdownButton(mContext: Context, mAttributes: AttributeSet? = null, mDefStyleAttr: Int = R.attr.buttonStyle) : AppCompatButton(mContext, mAttributes, mDefStyleAttr) {

    constructor(context: Context, attributes: AttributeSet) : this(mContext = context, mAttributes = attributes)

    constructor(context: Context) : this(mContext = context)

    var count: Int = 10

    lateinit var subscribe: Disposable//保存订阅者

    fun start() {
        isClickable = false//禁用点击,防止重复操作
        text = "${count + 1}s"
        subscribe = Observable.interval(1, TimeUnit.SECONDS)//按时间间隔发送整数的Observable
                .observeOn(AndroidSchedulers.mainThread())//切换到主线程修改UI
                .subscribe {
                    val show = count - it
                    if (show < 0.toLong()) {//当倒计时小于0,计时结束
                        stop()
                        return@subscribe//使用标记跳出方法
                    }
                    text = "${show}s"
                }
    }

    /**
     * 结束计时,重新开始
     */
    fun stop() {
        subscribe.dispose()//取消订阅
        text = "重新获取"
        isClickable = true//重新开启点击事件
        return
    }
}