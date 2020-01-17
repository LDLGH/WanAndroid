package com.ldl.wanandroid.core.http

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
     * 项目分类
     * http://www.wanandroid.com/project/tree/json
     *
     * @return 导航数据
     */
    fun getProjectClassifyData(): Observable<BaseResponse<List<ProjectClassifyData>>>

    /**
     * 项目类别数据
     * http://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid second page id
     * @return 项目分类数据
     */
    fun getProjectListData(
        page: Int,
        cid: Int
    ): Observable<BaseResponse<FeedArticleListData>>

    /**
     * 导航
     * http://www.wanandroid.com/navi/json
     *
     * @return 知识体系feed文章数据
     */
    fun getNavigationListData(): Observable<BaseResponse<ArrayList<NavigationListData>>>

    /**
     * 知识体系
     * http://www.wanandroid.com/tree/json
     *
     * @return 广告栏数据
     */
    fun getKnowledgeHierarchyData(): Observable<BaseResponse<List<KnowledgeHierarchyData>>>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0?cid=60
     *
     * @param page page num
     * @param cid second page id
     * @return 知识体系数据
     */
    fun getKnowledgeHierarchyDetailData(
        page: Int,
        cid: Int
    ): Observable<BaseResponse<FeedArticleListData>>

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    fun getWxAuthorListData(): Observable<BaseResponse<List<WxAuthor>>>

    /**
     * http://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号某页的数据
     */
    fun getWxSumData(
        id: Int,
        page: Int
    ): Observable<BaseResponse<FeedArticleListData>>

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