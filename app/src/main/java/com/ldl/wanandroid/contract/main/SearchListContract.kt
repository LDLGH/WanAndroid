package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData

/**
 * 作者：LDL 创建时间：2020/3/16
 * 类说明：
 */
interface SearchListContract {

    interface View : AbstractView {
        fun showSearchList(
            feedArticleListData: FeedArticleListData,
            isRefresh: Boolean
        )
    }

    interface Presenter : AbstractPresenter {

        fun refresh(k: String?)

        fun loadMore(k: String?)

        fun getSearchList(k: String?)
    }
}