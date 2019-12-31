package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.collect.FeedArticleListData

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
interface HomepageContract {

    interface View : AbstractView {
        /**
         * Show content
         *
         * @param feedArticleListData FeedArticleListData
         * @param isRefresh If refresh
         */
        fun showArticleList(
            feedArticleListData: FeedArticleListData,
            isRefresh: Boolean
        )
    }

    interface Presenter : AbstractPresenter {

        /**
         * Get feed article list
         *
         * @param isShowError If show error
         */
        fun getFeedArticleList(isShowError: Boolean)
    }
}