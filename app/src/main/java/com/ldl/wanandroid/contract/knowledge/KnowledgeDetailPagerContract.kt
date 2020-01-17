package com.ldl.wanandroid.contract.knowledge

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
interface KnowledgeDetailPagerContract {

    interface View : AbstractView {
        fun showKnowledgeHierarchyDetailList(
            feedArticleListData: FeedArticleListData,
            isRefresh: Boolean
        )
    }

    interface Presenter : AbstractPresenter {
        fun refresh(cid: Int)

        fun loadMore(cid: Int)

        fun getKnowledgeHierarchyDetailData(
            page: Int,
            cid: Int
        )
    }
}