package com.asm.demo

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.NonNull

/**
 *  解决rv中的复用问题可以从数据源解决，比如给adapter设置数据的时候给数据源设置一个本地集合，其中存的就是正在显示的GoodsTagView
 *  这样复用的是view，数据源还是原来的数据源
 */
class GoodsTagLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    /**
     * 唯一数据源，指向外界传进来的，保证数据的唯一性，后续始终操作的都是传进来的此数据源，
     * mExpandingDatas和GoodsTagView存的都是此数据集中的对象
     */
    private lateinit var mDatas: List<GoodsTagBean>

    /** 显示了信息的view */
    var mExpandingDatas: MutableList<GoodsTagBean>? = null

    /**
     * 设置数据
     */
    fun setGoodsTags(@NonNull goodsTagsData: GoodsTagsData) {
        if (null == goodsTagsData.datas || null == goodsTagsData.showingDatas) {
            return
        }
        mDatas = goodsTagsData.datas!!
        mExpandingDatas = goodsTagsData.showingDatas
        //如果是第一次进来，是没有展开的数据的，需要主动往里加
        if (mExpandingDatas!!.isEmpty()) {
            for (i in mDatas.indices) {
                if (mExpandingDatas!!.size >= 3) {
                    mDatas[i].isExpand = false
                } else {
                    mDatas[i].isExpand = true
                    mExpandingDatas!!.add(mDatas[i])
                }
            }
        }
        removeAllViews()
        for (i in mDatas.indices) {
            addView(GoodsTagView(context).apply {
                data = mDatas[i]
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
                    view.setAnchorClickListener { _v, _bean ->
                        if (!::mDatas.isInitialized || null == mExpandingDatas || null == _bean) {
                            return@setAnchorClickListener
                        }
                        //是否为展开状态。如果是展开状态则需要添加进维护的展开集合中
                        if (_bean.isExpand) {
                            mExpandingDatas!!.add(_bean)
                            //超过3个则删除最早的那个
                            if (mExpandingDatas!!.size > 3) {
                                mExpandingDatas!!.removeAt(0)
                            }
                        } else {
                            if (mExpandingDatas!!.contains(_bean)) {
                                mExpandingDatas!!.remove(_bean)
                            }
                        }

                        //1.重置是否展开的标识。操作mDatas其实操作的就是视图中的数据
                        for (i in mDatas.indices) {
                            mDatas[i].isExpand = mExpandingDatas!!.contains(mDatas[i])
                        }
                        //2.根据上面操作后的数据，直接判断是否展开或隐藏就可以
                        for (i in 0..childCount) {
                            if (getChildAt(i) is GoodsTagView) {
                                val view = getChildAt(i) as GoodsTagView
                                if (view.data!!.isExpand) {
                                    view.showInfoView()
                                } else {
                                    view.hideInfoView()
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}