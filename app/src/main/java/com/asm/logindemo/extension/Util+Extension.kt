package com.asm.logindemo.extension

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 计时器
 */
fun countDown(period: Long = 0, func: (time: Long) -> Unit) = countDown(period, func, null)

fun countDown(period: Long, func: (time: Long) -> Unit, fail: ((err: Throwable) -> Unit)? = null) = countDown(0, period, func, fail)

fun countDown(initialDelay: Long, period: Long, func: (time: Long) -> Unit, fail: ((err: Throwable) -> Unit)? = null): Disposable? {
    return Flowable.interval(initialDelay, period, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .subscribe({ func(it) }, { fail?.invoke(it) })
}

//主线程,防止线程嵌套
fun countDownOnUI(initialDelay: Long, period: Long, func: (time: Long) -> Unit, fail: ((err: Throwable) -> Unit)? = null): Disposable? {
    return Flowable.interval(initialDelay, period, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ func(it) }, { fail?.invoke(it) })
}