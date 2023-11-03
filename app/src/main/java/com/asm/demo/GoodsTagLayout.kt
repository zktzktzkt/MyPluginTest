package com.asm.demo

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.NonNull

class GoodsTagLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {


    private var datas: List<GoodsTagBean>? = null

    fun setGoodsTags(@NonNull datas: List<GoodsTagBean>) {
        this.datas = datas
        for (i in datas.indices) {
            addView(GoodsTagView(context).apply {
                data = datas[i]
            })
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        for (i in 0 until childCount) {
            if (getChildAt(i) is GoodsTagView) {
                val view = getChildAt(i)
                view.measure(0, 0)
                (view as GoodsTagView).data?.let {
                    when (it.direction) {
                        "l" -> {
                            val l = it.yAxis - view.measuredWidth + view.anchorWidth
                            val t = it.xAxis - view.measuredHeight / 2 + view.anchorWidth / 2
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                        "t" -> {
                            val l = it.yAxis - view.measuredWidth / 2 + view.anchorWidth / 2
                            val t = it.xAxis - view.measuredHeight + view.anchorWidth
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                        "r" -> {
                            val l = it.yAxis
                            val t = it.xAxis - view.measuredHeight / 2 + view.anchorWidth / 2
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                        "b" -> {
                            val l = it.yAxis - view.measuredWidth / 2 + view.anchorWidth / 2
                            val t = it.xAxis
                            view.layout(l, t, l + view.measuredWidth, t + view.measuredHeight)
                        }
                    }
                }
            }
        }
    }
}