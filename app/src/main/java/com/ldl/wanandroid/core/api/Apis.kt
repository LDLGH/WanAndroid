package com.ldl.wanandroid.core.api

import com.ldl.wanandroid.core.bean.BaseResponse
import com.ldl.wanandroid.core.bean.knowledge.KnowledgeHierarchyData
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.login.LoginData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData
import com.ldl.wanandroid.core.bean.navigation.NavigationListData
import com.ldl.wanandroid.core.bean.project.ProjectClassifyData
import com.ldl.wanandroid.core.bean.wx.WxAuthor
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
interface Apis {

    companion object {
        var HOST = "https://www.wanandroid.com/"
    }

    /**
     * 搜索
     * http://www.wanandroid.com/article/query/0/json
     * @param page page
     * @param k POST search key
     * @return 搜索数据
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    fun getSearchList(@Path("page") page: Int, @Field("k") k: String?): Observable<BaseResponse<FeedArticleListData>>

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

    /**
     * 常用网站
     * http://www.wanandroid.com/friend/json
     *
     * @return 常用网站数据
     */
    @GET("friend/json")
    fun getUsefulSites(): Observable<BaseResponse<List<UsefulSiteData>>>

    /**
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    fun getProjectClassifyData(): Observable<BaseResponse<List<ProjectClassifyData>>>

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目类别数据
     */
    @GET("project/list/{page}/json")
    fun getProjectListData(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<FeedArticleListData>>

    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    fun getNavigationListData(): Observable<BaseResponse<ArrayList<NavigationListData>>>

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
    @GET("tree/json")
    fun getKnowledgeHierarchyData(): Observable<BaseResponse<List<KnowledgeHierarchyData>>>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid second page id
     * @return 知识体系feed文章数据
     */
    @GET("article/list/{page}/json")
    fun getKnowledgeHierarchyDetailData(
        @Path("page") page: Int, @Query(
            "cid"
        ) cid: Int
    ): Observable<BaseResponse<FeedArticleListData>>

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    fun getWxAuthorListData(): Observable<BaseResponse<List<WxAuthor>>>

    /**
     * 获取当前公众号某页的数据
     * http://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号某页的数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    fun getWxSumData(@Path("id") id: Int, @Path("page") page: Int): Observable<BaseResponse<FeedArticleListData>>

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return 登陆数据
     */
    @POST("user/login")
    @FormUrlEncoded
    fun getLoginData(
        @Field("username") username: String?, @Field("password") password: String?
    ): Observable<BaseResponse<LoginData>>

    /**
     * 注册
     * http://www.wanandroid.com/user/register
     *
     * @param username user name
     * @param password password
     * @param repassword re password
     * @return 注册数据
     */
    @POST("user/register")
    @FormUrlEncoded
    fun getRegisterData(
        @Field("username") username: String?, @Field("password") password: String?, @Field("repassword") repassword: String?
    ): Observable<BaseResponse<LoginData>>

    /**
     * 退出登录
     * http://www.wanandroid.com/user/logout/json
     *
     * @return 登陆数据
     */
    @GET("user/logout/json")
    fun logout(): Observable<BaseResponse<LoginData>>
}