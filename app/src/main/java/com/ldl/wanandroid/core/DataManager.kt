package com.ldl.wanandroid.core

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
import com.ldl.wanandroid.core.dao.HistoryData
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
    var dbHelper: DbHelper,
    var preferenceHelper: PreferenceHelper
) : HttpHelper, DbHelper, PreferenceHelper {

    override fun getFeedArticleList(pageNum: Int): Observable<BaseResponse<FeedArticleListData>> =
        httpHelper.getFeedArticleList(pageNum)

    override fun getSearchList(
        pageNum: Int,
        k: String?
    ): Observable<BaseResponse<FeedArticleListData>> = httpHelper.getSearchList(pageNum, k)

    override fun getBannerData(): Observable<BaseResponse<List<BannerData>>> =
        httpHelper.getBannerData()

    override fun getTopSearchData(): Observable<BaseResponse<List<TopSearchData>>> =
        httpHelper.getTopSearchData()

    override fun getUsefulSites(): Observable<BaseResponse<List<UsefulSiteData>>> =
        httpHelper.getUsefulSites()

    override fun getProjectClassifyData(): Observable<BaseResponse<List<ProjectClassifyData>>> =
        httpHelper.getProjectClassifyData()

    override fun getProjectListData(
        page: Int,
        cid: Int
    ): Observable<BaseResponse<FeedArticleListData>> = httpHelper.getProjectListData(page, cid)

    override fun getNavigationListData(): Observable<BaseResponse<ArrayList<NavigationListData>>> =
        httpHelper.getNavigationListData()

    override fun getKnowledgeHierarchyData(): Observable<BaseResponse<List<KnowledgeHierarchyData>>> =
        httpHelper.getKnowledgeHierarchyData()

    override fun getKnowledgeHierarchyDetailData(
        page: Int,
        cid: Int
    ): Observable<BaseResponse<FeedArticleListData>> =
        httpHelper.getKnowledgeHierarchyDetailData(page, cid)

    override fun getWxAuthorListData(): Observable<BaseResponse<List<WxAuthor>>> =
        httpHelper.getWxAuthorListData()

    override fun getWxSumData(id: Int, page: Int): Observable<BaseResponse<FeedArticleListData>> =
        httpHelper.getWxSumData(id, page)

    override fun getLoginData(
        username: String?,
        password: String?
    ): Observable<BaseResponse<LoginData>> = httpHelper.getLoginData(username, password)

    override fun getRegisterData(
        username: String?,
        password: String?,
        rePassword: String?
    ): Observable<BaseResponse<LoginData>> =
        httpHelper.getRegisterData(username, password, rePassword)

    override fun logout(): Observable<BaseResponse<LoginData>> = httpHelper.logout()

    override fun addHistoryData(data: String?): List<HistoryData> = dbHelper.addHistoryData(data)

    override fun clearHistoryData() = dbHelper.clearHistoryData()

    override fun loadAllHistoryData(): List<HistoryData> = dbHelper.loadAllHistoryData()

    override fun setPassword(password: String) = preferenceHelper.setPassword(password)

    override fun getPassword(): String = preferenceHelper.getPassword()

    override fun setAccount(account: String) = preferenceHelper.setAccount(account)

    override fun getAccount(): String = preferenceHelper.getAccount()

    override fun setLoginStatus(loginStatus: Boolean) = preferenceHelper.setLoginStatus(loginStatus)

    override fun getLoginStatus(): Boolean = preferenceHelper.getLoginStatus()
}