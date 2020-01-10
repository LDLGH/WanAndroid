package com.ldl.wanandroid.core.db

import com.ldl.wanandroid.core.dao.HistoryData

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
interface DbHelper {

    /**
     * 增加历史数据
     */
    fun addHistoryData(data: String?): List<HistoryData>

    /**
     * Clear search history data
     */
    fun clearHistoryData()

    /**
     * Load all history data
     */
    fun loadAllHistoryData(): List<HistoryData>

}