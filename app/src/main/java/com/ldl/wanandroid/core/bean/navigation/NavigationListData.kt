package com.ldl.wanandroid.core.bean.navigation

import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData

/**
 * 作者：LDL 创建时间：2020/1/15
 * 类说明：
 */
data class NavigationListData(
    var articles: List<FeedArticleData>,
    var cid: Int,
    var name: String,
    var select: Boolean = false
)