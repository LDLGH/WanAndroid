package com.ldl.wanandroid.core.api

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.collect.FeedArticleListData
import io.reactivex.Observable
import retrofit2.http.GET
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
}