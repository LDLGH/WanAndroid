package com.ldl.wanandroid.core.http

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.collect.FeedArticleListData
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
}