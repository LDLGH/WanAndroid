package com.ldl.wanandroid.contract.main

import com.ldl.wanandroid.base.presenter.AbstractPresenter
import com.ldl.wanandroid.base.view.AbstractView
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.dao.HistoryData

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
interface SearchContract {

    interface View : AbstractView {

        fun showTopTopSearch(topSearchDataList: List<TopSearchData>)

        fun showHistory(historyDataList: List<HistoryData>)
    }

    interface Presenter : AbstractPresenter {

        fun getTopTopSearchData()

        /**
         * 增加历史数据
         */
        fun addHistoryData(data: String?)

        /**
         * Clear search history data
         */
        fun clearHistoryData()

        /**
         * Load all history data
         */
        fun loadAllHistoryData()
    }
}