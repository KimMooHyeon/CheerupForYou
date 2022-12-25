package com.sabuzak.yeonamplace.cheerupforyou.presentation.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.sabuzak.yeonamplace.cheerupforyou.R

class CheerUpCustomView : AppCompatTextView {
    private var stroke = false
    private var strokeWidth = 3.0f
    private var strokeColor = 0

    constructor(context: Context?) : super(context!!)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context, attrs)
    }

    private fun initView(context: Context, attrs: AttributeSet?) {
        val type = context.obtainStyledAttributes(attrs, R.styleable.CheerUpCustomView)
        stroke = type.getBoolean(R.styleable.CheerUpCustomView_textStroke, false) // 외곽선 유무
        strokeWidth = type.getFloat(R.styleable.CheerUpCustomView_textStrokeWidth, 10.0f) // 외곽선 두께
        strokeColor = type.getColor(R.styleable.CheerUpCustomView_textStrokeColor, 0x12000000) // 외곽선
        type.recycle()
    }

    public override fun onDraw(canvas: Canvas) {
        if (stroke) {
            val states = textColors
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = strokeWidth
            setTextColor(strokeColor)
            super.onDraw(canvas)
            paint.style = Paint.Style.FILL
            setTextColor(states)
        }
        super.onDraw(canvas)
    }

    fun setStroke(judge: Boolean) {
        stroke = judge
    }
}