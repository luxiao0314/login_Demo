package com.asm.logindemo.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import androidx.viewpager.widget.ViewPager

/**
 * @Description
 * @Author luxiao
 * @Date 2019-09-28 10:08
 * @Version
 */
fun ViewPager.setOnPageChangeListener(init: PageChangeWrapper.() -> Unit) {
    val pageChangeDelegate = PageChangeWrapper()
    pageChangeDelegate.init()
    setOnPageChangeListener(pageChangeDelegate)
}

class PageChangeWrapper : ViewPager.OnPageChangeListener {

    var onPageScrollStateChanged: ((state: Int) -> Unit)? = null
    var onPageScrolled: ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit)? = null
    var onPageSelected: ((position: Int) -> Unit)? = null

    override fun onPageScrollStateChanged(state: Int) {
        onPageScrollStateChanged?.let { it(state) }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        onPageScrolled?.let { it(position, positionOffset, positionOffsetPixels) }
    }

    override fun onPageSelected(position: Int) {
        onPageSelected?.let { it(position) }
    }
}

fun ViewPager.addOnPageChangeListener(init: AddOnPageChangeListenerWrapper.() -> Unit) {
    val callback = AddOnPageChangeListenerWrapper()
    callback.init()
    addOnPageChangeListener(callback)
}

class AddOnPageChangeListenerWrapper : ViewPager.OnPageChangeListener {

    var onPageScrollStateChanged: ((state: Int) -> Unit)? = null
    var onPageScrolled: ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit)? = null
    var onPageSelected: ((position: Int) -> Unit)? = null

    override fun onPageScrollStateChanged(state: Int) {
        onPageScrollStateChanged?.let { it(state) }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        onPageScrolled?.let { it(position, positionOffset, positionOffsetPixels) }
    }

    override fun onPageSelected(position: Int) {
        onPageSelected?.let { it(position) }
    }
}


fun EditText.addTextChangedListener(init: AddTextWatcher.() -> Unit) {
    val callback = AddTextWatcher()
    callback.init()
    addTextChangedListener(callback)
}

class AddTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        afterTextChanged?.let { it(s) }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        beforeTextChanged?.let { it(s,start, count, after) }
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged?.let { it(s, start, before, count) }
    }

    var afterTextChanged: ((s: Editable?) -> Unit)? = null
    var beforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null
    var onTextChanged: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null
}

fun SeekBar.setOnSeekBarChangeListener(block: (p0: SeekBar?, p1: Int, p2: Boolean) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onStartTrackingTouch(p0: SeekBar?) = Unit

        override fun onStopTrackingTouch(p0: SeekBar?) = Unit

        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            block.invoke(p0, p1, p2)
        }
    })
}
