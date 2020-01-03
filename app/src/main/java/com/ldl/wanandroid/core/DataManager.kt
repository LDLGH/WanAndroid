package com.ldl.wanandroid.core

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.db.DbHelper
import com.ldl.wanandroid.core.http.HttpHelper
import com.ldl.wanandroid.core.prefs.PreferenceHelper
import io.reactivex.Observable

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
class DataManager constructor(
    var httpHelper: HttpHelper,
    dbHelper: DbHelper,
    preferenceHelper: PreferenceHelper
) : HttpHelper, DbHelper, PreferenceHelper {

    override fun getFeedArticleList(pageNum: Int): Observable<BaseResponse<FeedArticleListData>> =
        httpHelper.getFeedArticleList(pageNum)

    override fun getBannerData(): Observable<BaseResponse<List<BannerData>>> =
        httpHelper.getBannerData()

    override fun getTopSearchData(): Observable<BaseResponse<List<TopSearchData>>> =
        httpHelper.getTopSearchData()
}