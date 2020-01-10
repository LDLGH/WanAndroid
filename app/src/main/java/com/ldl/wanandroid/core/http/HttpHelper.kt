package com.ldl.wanandroid.core.http

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.login.LoginData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData
import io.reactivex.Observable
import retrofit2.http.GET

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

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
    fun getUsefulSites(): Observable<BaseResponse<List<UsefulSiteData>>>

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 项目类别数据
     */
    fun getLoginData(
        username: String?,
        password: String?
    ): Observable<BaseResponse<LoginData>>

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param rePassword re password
     * @return 登陆数据
     */
    fun getRegisterData(
        username: String?,
        password: String?,
        rePassword: String?
    ): Observable<BaseResponse<LoginData>>

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     */
    fun logout(): Observable<BaseResponse<LoginData>>
}