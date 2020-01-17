package com.ldl.wanandroid.contract.wx

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
interface WXListPagerContract {

    interface View : AbstractView {
        fun showWxSumData(
            feedArticleListData: FeedArticleListData,
            isRefresh: Boolean
        )
    }

    interface Presenter : AbstractPresenter {

        fun refresh(cid: Int)

        fun loadMore(cid: Int)

        fun getWxSumData(id: Int, page: Int)
    }

}