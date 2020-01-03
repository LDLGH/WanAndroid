package com.ldl.wanandroid.ui.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData

/**
 * 作者：LDL 创建时间：2020/1/2
 * 类说明：
 */
class ArticleListAdapter(datas: ArrayList<FeedArticleData>) :
    BaseQuickAdapter<FeedArticleData, BaseViewHolder>(R.layout.item_article, datas),
    LoadMoreModule {
    override fun convert(helper: BaseViewHolder, item: FeedArticleData?) {
    }
}