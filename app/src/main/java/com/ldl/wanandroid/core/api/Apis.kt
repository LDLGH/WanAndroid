package com.ldl.wanandroid.core.api

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
interface Apis {

    companion object {
        var HOST = "https://www.wanandroid.com/"
    }

    /**
     * 获取feed文章列表
     *
     * @param num 页数
     * @return feed文章列表数据
     */
    @GET("article/list/{num}/json")
    fun getFeedArticleList(@Path("num") num: Int): Observable<BaseResponse<FeedArticleListData>>


    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    fun getBannerData(): Observable<BaseResponse<List<BannerData>>>

    /**
     * 热搜
     * http://www.wanandroid.com//hotkey/json
     *
     * @return 热门搜索数据
     */
    @GET("hotkey/json")
    @Headers("Cache-Control: public, max-age=36000")
    fun getTopSearchData(): Observable<BaseResponse<List<TopSearchData>>>
}