package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
interface HomepageContract {

    interface View : AbstractView {

        fun showArticleList(
            feedArticleListData: FeedArticleListData,
            isRefresh: Boolean
        )

        fun showBanner(bannerDataList: List<BannerData>)

        fun showTopTopSearch(topSearchDataList: List<TopSearchData>)
    }

    interface Presenter : AbstractPresenter {

        fun refresh()

        fun loadMore()

        fun getFeedArticleList()

        fun getBannerData()

        fun getTopTopSearchData()

        fun getAllData(isShowLoading: Boolean)
    }
}