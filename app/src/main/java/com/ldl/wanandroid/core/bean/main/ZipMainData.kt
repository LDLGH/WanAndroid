package com.ldl.wanandroid.core.bean.main

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData

/**
 * 作者：LDL 创建时间：2020/1/3
 * 类说明：
 */
data class ZipMainData(
    var topSearchBaseResponse: BaseResponse<List<TopSearchData>>,
    var bannerResponse: BaseResponse<List<BannerData>>,
    var feedArticleListResponse: BaseResponse<FeedArticleListData>,
    var usefulSiteDataResponse: BaseResponse<List<UsefulSiteData>>
)