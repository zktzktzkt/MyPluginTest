package com.asm.demo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.lpc.testgradle.R

class GoodsTagView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var anchorLeft: View
    private var anchorTop: View
    private var anchorRight: View
    private var anchorBottom: View
    private var lineLeft: View
    private var lineTop: View
    private var lineRight: View
    private var lineBottom: View

    /** 锚点宽度 */
    var anchorWidth: Int

    init {
        inflate(context, R.layout.view_goodstag, this)
        anchorLeft = findViewById(R.id.v_anchorLeft)
        anchorTop = findViewById(R.id.v_anchorTop)
        anchorRight = findViewById(R.id.v_anchorRight)
        anchorBottom = findViewById(R.id.v_anchorBottom)
        lineLeft = findViewById(R.id.line_left)
        lineTop = findViewById(R.id.line_top)
        lineRight = findViewById(R.id.line_right)
        lineBottom = findViewById(R.id.line_bottom)

        anchorWidth = dp2px(context, 10f)
    }

    fun dp2px(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    var data: GoodsTagBean? = null
        get() = field
        set(value) {
            field = value
            value?.let {
                when (it.direction) {
                    "l" -> {
                        setLineVisibility(left = false, top = false, right = true, bottom = false)
                    }
                    "t" -> {
                        setLineVisibility(left = false, top = false, right = false, bottom = true)
                    }
                    "r" -> {
                        setLineVisibility(left = true, top = false, right = false, bottom = false)
                    }
                    "b" -> {
                        setLineVisibility(left = false, top = true, right = false, bottom = false)
                    }
                }
            }
        }

    /**
     * 设置线的可见
     */
    private fun setLineVisibility(left: Boolean, top: Boolean, right: Boolean, bottom: Boolean) {
        lineLeft.visibility = if (left) VISIBLE else GONE
        anchorLeft.visibility = if (left) VISIBLE else GONE

        lineTop.visibility = if (top) VISIBLE else GONE
        anchorTop.visibility = if (top) VISIBLE else GONE

        lineRight.visibility = if (right) VISIBLE else GONE
        anchorRight.visibility = if (right) VISIBLE else GONE

        lineBottom.visibility = if (bottom) VISIBLE else GONE
        anchorBottom.visibility = if (bottom) VISIBLE else GONE
    }

}