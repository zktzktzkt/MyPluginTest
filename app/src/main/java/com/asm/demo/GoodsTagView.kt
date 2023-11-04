package com.asm.demo

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.lpc.testgradle.R

class GoodsTagView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var anchorClicklistener: ((GoodsTagView, GoodsTagBean?) -> Unit)? = null
    private var llInfo: LinearLayout

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

    /** 当前是否展示了信息布局 */
    private var isShowingInfo: Boolean = false
        get() = field
        set(value) {
            field = value
        }

    init {
        inflate(context, R.layout.view_goodstag, this)
        llInfo = findViewById(R.id.ll_info)
        anchorLeft = findViewById(R.id.v_anchorLeft)
        anchorTop = findViewById(R.id.v_anchorTop)
        anchorRight = findViewById(R.id.v_anchorRight)
        anchorBottom = findViewById(R.id.v_anchorBottom)
        lineLeft = findViewById(R.id.line_left)
        lineTop = findViewById(R.id.line_top)
        lineRight = findViewById(R.id.line_right)
        lineBottom = findViewById(R.id.line_bottom)

        anchorWidth = dp2px(context, 10f)

        anchorLeft.setOnClickListener {
            anchorClick()
        }
        anchorTop.setOnClickListener {
            anchorClick()
        }
        anchorRight.setOnClickListener {
            anchorClick()
        }
        anchorBottom.setOnClickListener {
            anchorClick()
        }
    }

    /**
     * 数据
     */
    var data: GoodsTagBean? = null
        get() = field
        set(value) {
            field = value
            value?.let {
                if (it.isExpand) {
                    showInfoView()
                } else {
                    hideInfoView()
                }
            }
        }

    /**
     * 锚点点击
     */
    private fun anchorClick() {
        reverseShowInfoView()
        anchorClicklistener?.invoke(this, data)
    }

    /**
     * 反向处理显隐
     */
    private fun reverseShowInfoView() {
        if (llInfo.visibility == VISIBLE) {
            hideInfoView()
        } else {
            showInfoView()
        }
    }

    /**
     * 显示信息的布局
     */
    fun showInfoView() {
        if (null == data) {
            return
        }
        var lineView: View? = null
        var anchorView: View? = null
        when (data!!.direction) {
            DIRECTION.LEFT.value -> {
                lineView = lineRight
                anchorView = anchorRight
            }
            DIRECTION.TOP.value -> {
                lineView = lineBottom
                anchorView = anchorBottom
            }
            DIRECTION.RIGHT.value -> {
                lineView = lineLeft
                anchorView = anchorLeft
            }
            DIRECTION.BOTTOM.value -> {
                lineView = lineTop
                anchorView = anchorTop
            }
        }
        data?.isExpand = true
        anchorView?.visibility = VISIBLE
        lineView?.visibility = VISIBLE
        llInfo.visibility = VISIBLE
        isShowingInfo = true
    }

    /**
     * 隐藏信息的布局
     */
    fun hideInfoView() {
        if (null == data) {
            return
        }
        var lineView: View? = null
        var anchorView: View? = null
        when (data!!.direction) {
            DIRECTION.LEFT.value -> {
                lineView = lineRight
                anchorView = anchorRight
            }
            DIRECTION.TOP.value -> {
                lineView = lineBottom
                anchorView = anchorBottom
            }
            DIRECTION.RIGHT.value -> {
                lineView = lineLeft
                anchorView = anchorLeft
            }
            DIRECTION.BOTTOM.value -> {
                lineView = lineTop
                anchorView = anchorTop
            }
        }
        data?.isExpand = false
        anchorView?.visibility = VISIBLE
        lineView?.visibility = GONE
        llInfo.visibility = GONE
        isShowingInfo = false
    }

    /**
     * 设置锚点点击
     */
    fun setAnchorClickListener(listener: ((GoodsTagView, GoodsTagBean?) -> Unit)?) {
        anchorClicklistener = listener
    }

    fun dp2px(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

}