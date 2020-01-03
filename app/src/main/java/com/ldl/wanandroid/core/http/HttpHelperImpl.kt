package com.ldl.wanandroid.core.http

import com.ldl.wanandroid.core.api.Apis
import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import io.reactivex.Observable
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
class HttpHelperImpl @Inject constructor(var apis: Apis) : HttpHelper {

    override fun getFeedArticleList(pageNum: Int): Observable<BaseResponse<FeedArticleListData>> =
        apis.getFeedArticleList(pageNum)

    override fun getBannerData(): Observable<BaseResponse<List<BannerData>>> =
        apis.getBannerData()

    override fun getTopSearchData(): Observable<BaseResponse<List<TopSearchData>>> =
        apis.getTopSearchData()

}