package com.ldl.wanandroid.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatTextView
import com.blankj.utilcode.util.ConvertUtils

/**
 * 作者：LDL 创建时间：2020/1/21
 * 类说明：
 */
class CountDownTextView(context: Context, attributeSet: AttributeSet) :
    AppCompatTextView(context, attributeSet) {

    // 倒计时动画时间
    var duration = 3000L
    // 动画扫过的角度
    private var mSweepAngle = 360
    // 属性动画
    private lateinit var animator: ValueAnimator
    // 矩形用来保存位置大小信息
    private val mRectF = RectF()
    // 圆弧的画笔
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = Color.WHITE
        mPaint.strokeWidth = ConvertUtils.dp2px(2f).toFloat()
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
    }

    /**
     * 开始倒计时
     */
    fun start() {
        // 在动画中
        if (mSweepAngle != 360) return
        // 初始化属性动画
        animator = ValueAnimator.ofInt(mSweepAngle).setDuration(duration)
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            // 获取属性动画返回的动画值
            mSweepAngle = 360 - animator.animatedValue as Int
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        val padding = ConvertUtils.dp2px(4f).toFloat()
        mRectF.top = padding
        mRectF.left = padding
        mRectF.right = width - padding
        mRectF.bottom = height - padding

        // 画倒计时线内圆
        canvas?.drawArc(
            mRectF,
            -90f,
            mSweepAngle.toFloat(),
            false,
            mPaint
        )
        super.onDraw(canvas)
    }

}