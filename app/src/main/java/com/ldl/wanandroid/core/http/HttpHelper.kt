package com.ldl.wanandroid.core.http

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import io.reactivex.Observable

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
interface HttpHelper {

    /**
     * 获取feed文章列表
     *
     * @param pageNum 页数
     * @return feed文章列表数据
     */
    fun getFeedArticleList(pageNum: Int): Observable<BaseResponse<FeedArticleListData>>

    /**
     * 广告栏
     * http://www.wanandroid.com/banner/json
     *
     * @return 取消收藏页面站内文章数据
     */
    fun getBannerData(): Observable<BaseResponse<List<BannerData>>>

    /**
     * 热搜
     * http://www.wanandroid.com//hotkey/json
     *
     * @return 热门搜索数据
     */
    fun getTopSearchData(): Observable<BaseResponse<List<TopSearchData>>>
}