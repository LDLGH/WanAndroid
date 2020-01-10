package com.ldl.wanandroid.core.db

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.app.WanAndroidApp
import com.ldl.wanandroid.core.dao.DaoSession
import com.ldl.wanandroid.core.dao.HistoryData
import com.ldl.wanandroid.core.dao.HistoryDataDao
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
class DbHelperImpl @Inject constructor() : DbHelper {

    private var mDaoSession: DaoSession? = null

    private lateinit var mHistoryDataList: ArrayList<HistoryData>

    init {
        mDaoSession = WanAndroidApp.getInstance?.getDaoSession()
    }

    override fun addHistoryData(data: String?): ArrayList<HistoryData> {
        mHistoryDataList = ArrayList()
        val allHistoryData = loadAllHistoryData()
        val list = allHistoryData.filter {
            it.data != data
        }
        mHistoryDataList.addAll(list)
        val historyData = HistoryData()
        historyData.data = data
        historyData.date = System.currentTimeMillis()
        mHistoryDataList.add(historyData)
        clearHistoryData()
        getHistoryDao().insertInTx(mHistoryDataList)
        return mHistoryDataList
    }

    override fun clearHistoryData() {
        getHistoryDao().deleteAll()
    }

    override fun loadAllHistoryData(): List<HistoryData> = getHistoryDao().loadAll()

    private fun getHistoryDao(): HistoryDataDao = mDaoSession!!.historyDataDao

}