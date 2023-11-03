package com.asm.demo

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.NonNull

/**
 * TODO 解决rv中的复用问题可以从数据源解决，比如给adapter设置数据的时候给数据源设置一个本地集合，其中存的就是正在显示的GoodsTagView
 *      这样复用的是view，数据源还是原来的数据源
 */
class GoodsTagLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    /** 数据 */
    private var datas: List<GoodsTagBean>? = null

    /** 显示了信息的view */
//    val infoShowingViews = mutableListOf<GoodsTagView>()

    /**
     * 设置数据
     */
    fun setGoodsTags(@NonNull datas: List<GoodsTagBean>) {
        this.datas = datas
        for (i in datas.indices) {
            datas[i].isShow = i < 3
            addView(GoodsTagView(context).apply {
                data = datas[i]
            })
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (i in 0 until childCount) {
            if (getChildAt(i) is GoodsTagView) {
                val view = getChildAt(i) as GoodsTagView
                view.measure(0, 0)

                //根据方向布局信息
                view.data?.let {
                    when (it.direction) {
                        DIRECTION.LEFT.value -> {
                            val l = it.xAxis - view.measuredWidth + view.anchorWidth
                            val t = it.yAxis - view.measuredHeight / 2 + view.anchorWidth / 2
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                        DIRECTION.TOP.value -> {
                            val l = it.xAxis - view.measuredWidth / 2 + view.anchorWidth / 2
                            val t = it.yAxis - view.measuredHeight + view.anchorWidth
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                        DIRECTION.RIGHT.value -> {
                            val l = it.xAxis
                            val t = it.yAxis - view.measuredHeight / 2 + view.anchorWidth / 2
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                        DIRECTION.BOTTOM.value -> {
                            val l = it.xAxis - view.measuredWidth / 2 + view.anchorWidth / 2
                            val t = it.yAxis
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                    }

                    //锚点点击
                    view.setAnchorClickListener { v, direction, bean ->

                    }

                }
            }
        }
    }
}