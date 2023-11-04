package com.asm.demo

class GoodsTagsData {

    /**
     * 数据集
     */
    var datas: List<GoodsTagBean>? = null

    /**
     * 【本地变量】正显示的tagview。必须赋值，不允许设置为null
     */
    val showingDatas = mutableListOf<GoodsTagBean>()
}