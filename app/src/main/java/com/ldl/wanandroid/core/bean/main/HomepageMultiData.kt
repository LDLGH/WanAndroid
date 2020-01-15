package com.ldl.wanandroid.core.bean.main

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
data class HomepageMultiData(
    override val itemType: Int,
    var title: String,
    var desc: String,
    var data: String
) : MultiItemEntity {
    companion object {

        const val ARTICLE = 0

        const val HOT_SEARCH = 1

        const val USEFUL_SITES = 2

        const val LOGIN = 3

        const val BOTTOM_LINE = 4
    }
}