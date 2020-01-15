package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData

/**
 * 作者：LDL 创建时间：2020/1/14
 * 类说明：
 */
interface ArticleContract {

    interface View : AbstractView {
        fun showArticleList(
            feedArticleListData: FeedArticleListData,
            isRefresh: Boolean
        )
    }

    interface Presenter : AbstractPresenter {
        fun refresh()

        fun loadMore()

        fun getFeedArticleList()
    }
}