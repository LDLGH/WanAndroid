package com.ldl.wanandroid.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.ViewSwitcher
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.R


/**
 * 作者：LDL 创建时间：2020/1/3
 * 类说明：
 */
@SuppressLint("HandlerLeak")
class ScrollTextSwitcher constructor(
    context: Context, attrs: AttributeSet
) : TextSwitcher(context, attrs), ViewSwitcher.ViewFactory {

    companion object {
        const val FLAG_START_AUTO_SCROLL = 0
        const val FLAG_STOP_AUTO_SCROLL = 1

        const val STATE_PAUSE = 2
        const val STATE_SCROLL = 3
    }

    private var mScrollState = STATE_PAUSE
    private var currentId = 0

    var mAnimDuration = 500f
    var mTime = 3000

    var mTexts: ArrayList<String>? = null
        set(value) {
            field = value
            currentId = -1
            startAutoScroll()
        }

    private var mItemClickListener: OnItemClickListener? = null

    init {
        val animIn = TranslateAnimation(0f, 0f, mAnimDuration, 0f)
        animIn.duration = mAnimDuration.toLong()
        animIn.interpolator = AccelerateInterpolator()
        val animOut = TranslateAnimation(0f, 0f, 0f, -mAnimDuration)
        animOut.duration = mAnimDuration.toLong()
        animOut.interpolator = AccelerateInterpolator()
        inAnimation = animIn
        outAnimation = animOut
        setFactory(this)
    }

    override fun makeView(): View {
        val textView = TextView(context)
        textView.textSize = 16f
        textView.setTextColor(ColorUtils.getColor(R.color.colorTextGray))
        textView.setOnClickListener {
            if (mTexts!!.size > 0 && currentId != -1) {
                mItemClickListener?.onItemClick(currentId % mTexts!!.size)
            }
        }
        return textView
    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                FLAG_START_AUTO_SCROLL -> {
                    mTexts?.also {
                        if (it.size > 0) {
                            currentId++
                            setText(it[currentId % it.size])
                        }
                    }
                    sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL, mTime.toLong())
                }
                FLAG_STOP_AUTO_SCROLL -> {
                    removeMessages(FLAG_START_AUTO_SCROLL)
                }
                else -> {
                }
            }
        }
    }

    fun startAutoScroll() {
        if (ObjectUtils.isNotEmpty(mTexts)) {
            mHandler.removeCallbacksAndMessages(null)
            mScrollState = STATE_SCROLL
            mHandler.sendEmptyMessage(FLAG_START_AUTO_SCROLL)
        }
    }

    fun stopAutoScroll() {
        mScrollState = STATE_PAUSE
        mHandler.removeMessages(FLAG_STOP_AUTO_SCROLL)
    }

    fun isScroll(): Boolean {
        return mScrollState == STATE_SCROLL
    }

    fun isPause(): Boolean {
        return mScrollState == STATE_PAUSE
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mHandler.removeCallbacksAndMessages(null)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAutoScroll()
    }

    /**
     * set onclick listener
     *
     * @param itemClickListener listener
     */
    fun setOnItemClickListener(itemClickListener: OnItemClickListener?) {
        this.mItemClickListener = itemClickListener
    }

    /**
     * item click listener
     */
    interface OnItemClickListener {
        /**
         * callback
         *
         * @param position position
         */
        fun onItemClick(position: Int)
    }
}