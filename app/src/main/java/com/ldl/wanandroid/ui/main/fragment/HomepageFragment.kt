package com.ldl.wanandroid.ui.main.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.fragment_homepage
import com.ldl.wanandroid.base.fragment.BaseRootFragment
import com.ldl.wanandroid.contract.main.HomepageContract
import com.ldl.wanandroid.core.bean.main.HomepageMultiData
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData
import com.ldl.wanandroid.presenter.main.HomepagePresenter
import com.ldl.wanandroid.ui.knowledge.activity.KnowledgeActivity
import com.ldl.wanandroid.ui.main.activity.ArticleActivity
import com.ldl.wanandroid.ui.main.activity.WebViewActivity
import com.ldl.wanandroid.ui.main.adapter.BannerViewHolder
import com.ldl.wanandroid.ui.main.adapter.HomepageAdapter
import com.ldl.wanandroid.ui.main.adapter.MenuAdapter
import com.ldl.wanandroid.ui.navigation.activity.NavigationActivity
import com.ldl.wanandroid.ui.project.activity.ProjectActivity
import com.ldl.wanandroid.ui.wx.activity.WXListActivity
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.IndicatorSlideMode
import com.zhpan.bannerview.constants.PageStyle.MULTI_PAGE_SCALE
import kotlinx.android.synthetic.main.fragment_homepage.*

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
@SuppressLint("InflateParams")
class HomepageFragment : BaseRootFragment<HomepagePresenter>(), HomepageContract.View {

    private lateinit var mHeadView: View
    private var mBanner: BannerViewPager<BannerData, BannerViewHolder>? = null
    private lateinit var mAdapter: HomepageAdapter

    private val mHomepageMultiData: ArrayList<HomepageMultiData> by lazy { ArrayList<HomepageMultiData>() }

    override fun onResume() {
        super.onResume()
        mBanner?.startLoop()
    }

    override fun onPause() {
        super.onPause()
        mBanner?.stopLoop()
    }

    override fun getLayoutId(): Int = fragment_homepage

    override fun initView() {
        initLoadingStatusViewIfNeed(swipeRefreshLayout)
        initHead()
        initRecyclerView()
        initRefresh()
    }

    override fun initEventAndData() {
        mPresenter?.getAllData(true)
    }

    private fun initHead() {
        mHeadView = LayoutInflater.from(mActivity).inflate(R.layout.head_banner, null)
        mBanner = mHeadView.findViewById(R.id.banner_view)
        val rvMenu = mHeadView.findViewById<RecyclerView>(R.id.rv_menu)
        rvMenu.setHasFixedSize(true)
        rvMenu.layoutManager = GridLayoutManager(mActivity, 5)
        val menuAdapter = MenuAdapter(mPresenter!!.getMenuList())
        rvMenu.adapter = menuAdapter
        menuAdapter.setOnItemClickListener { _, _, position ->
            jumpToActivity(position)
        }
    }

    private fun initRecyclerView() {
        mAdapter = HomepageAdapter(mHomepageMultiData)
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter
        mAdapter.addHeaderView(mHeadView)
    }

    private fun initBanner(bannerDataList: List<BannerData>) {
        mBanner?.apply {
            setPageStyle(MULTI_PAGE_SCALE)
                .setPageMargin(ConvertUtils.dp2px(20f))
                .setIndicatorSlideMode(IndicatorSlideMode.SMOOTH)
                .setHolderCreator { BannerViewHolder() }
                .setOnPageClickListener {
                    val bannerData = bannerDataList[it]
                    WebViewActivity.start(bannerData.title, bannerData.url)
                }
                .create(bannerDataList)
        }
    }

    private fun initRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.refresh()
        }
    }

    private fun jumpToActivity(position: Int) {
        when (position) {
            0 -> {
                ActivityUtils.startActivity(ArticleActivity::class.java)
            }
            1 -> {
                ActivityUtils.startActivity(NavigationActivity::class.java)
            }
            2 -> {
                ActivityUtils.startActivity(KnowledgeActivity::class.java)
            }
            3 -> {
                ActivityUtils.startActivity(WXListActivity::class.java)
            }
            4 -> {
                ActivityUtils.startActivity(ProjectActivity::class.java)
            }
            else -> {
            }
        }
    }

    override fun onLoginEvent() {
        mAdapter.remove(3)
    }

    override fun reload() {
        super.reload()
        mPresenter?.getAllData(true)
    }

    override fun showArticleList(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {
        if (isRefresh) {
            swipeRefreshLayout.isRefreshing = false
            val data = HomepageMultiData(
                HomepageMultiData.ARTICLE,
                getString(R.string.recommended_article),
                getString(R.string.carefully_selected_for_you),
                GsonUtils.toJson(feedArticleListData.datas)
            )
            mHomepageMultiData.clear()
            mHomepageMultiData.add(data)
        }
    }

    override fun showBanner(bannerDataList: List<BannerData>) {
        initBanner(bannerDataList)
    }

    override fun showTopTopSearch(topSearchDataList: List<TopSearchData>) {
        val data = HomepageMultiData(
            HomepageMultiData.HOT_SEARCH,
            getString(R.string.hot_search),
            getString(R.string.discover_more),
            GsonUtils.toJson(topSearchDataList)
        )
        mHomepageMultiData.add(data)
    }

    override fun showHotSearch(usefulSiteDataList: List<UsefulSiteData>) {
        val data = HomepageMultiData(
            HomepageMultiData.USEFUL_SITES,
            getString(R.string.useful_sites),
            getString(R.string.website_collection),
            GsonUtils.toJson(usefulSiteDataList)
        )
        mHomepageMultiData.add(data)
        if (!mPresenter!!.getLoginStatus()) {
            val loginData = HomepageMultiData(
                HomepageMultiData.LOGIN,
                getString(R.string.login_immediately),
                getString(R.string.login_collect_articles),
                ""
            )
            mHomepageMultiData.add(loginData)
        }
        val bottomLineData = HomepageMultiData(
            HomepageMultiData.BOTTOM_LINE, "", "", ""
        )
        mHomepageMultiData.add(bottomLineData)
        mAdapter.setNewData(mHomepageMultiData)
    }

}