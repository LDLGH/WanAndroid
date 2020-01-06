package com.ldl.wanandroid.ui.main.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.fragment_homepage
import com.ldl.wanandroid.base.fragment.BaseRootFragment
import com.ldl.wanandroid.contract.main.HomepageContract
import com.ldl.wanandroid.core.bean.main.MainData
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.menu.MenuData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.presenter.main.HomepagePresenter
import com.ldl.wanandroid.ui.main.adapter.BannerViewHolder
import com.ldl.wanandroid.ui.main.adapter.MainAdapter
import com.ldl.wanandroid.ui.main.adapter.MenuAdapter
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.PageStyle.MULTI_PAGE_SCALE
import kotlinx.android.synthetic.main.fragment_homepage.*

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
@SuppressLint("InflateParams")
class HomepageFragment : BaseRootFragment<HomepagePresenter>(), HomepageContract.View {

    private var menuList = ArrayList<MenuData>()

    init {
        menuList.add(MenuData("文章", ""))
        menuList.add(MenuData("知识体系", ""))
        menuList.add(MenuData("公众号", ""))
        menuList.add(MenuData("导航", ""))
        menuList.add(MenuData("项目", ""))
    }

    private lateinit var mHeadView: View
    private var mBanner: BannerViewPager<BannerData, BannerViewHolder>? = null
    private lateinit var mAdapter: MainAdapter

    private var mainDataList = ArrayList<MainData>()

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
        val menuAdapter = MenuAdapter(menuList)
        rvMenu.adapter = menuAdapter
        menuAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }

    private fun initRecyclerView() {
        mAdapter = MainAdapter(mainDataList)
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter
        mAdapter.addHeaderView(mHeadView)
        mAdapter.setOnItemChildClickListener { adapter, view, position -> }
    }

    private fun initBanner(bannerDataList: List<BannerData>) {
        mBanner?.also {
            it.setPageStyle(MULTI_PAGE_SCALE)
                .setPageMargin(ConvertUtils.dp2px(20f))
                .setHolderCreator { BannerViewHolder() }
                .setOnPageClickListener { it1 ->
                    showSnackBar(bannerDataList[it1].title)
                }
                .create(bannerDataList)
        }
    }

    private fun initRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.refresh()
        }
    }

    override fun showArticleList(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {
        if (isRefresh) {
            swipeRefreshLayout.isRefreshing = false
            val data = MainData(
                getString(R.string.recommended_article),
                getString(R.string.carefully_selected_for_you),
                GsonUtils.toJson(feedArticleListData.datas)
            )
            mainDataList.add(data)
            val list = ArrayList<MainData>()
            list.add(data)
            mAdapter.setNewData(list)
        }
    }

    override fun showBanner(bannerDataList: List<BannerData>) {
        initBanner(bannerDataList)
    }

    override fun showTopTopSearch(topSearchDataList: List<TopSearchData>) {
        val list = arrayListOf<String>()
        topSearchDataList.forEach {
            list.add(it.name)
        }
    }

    override fun onResume() {
        super.onResume()
        mBanner?.startLoop()
    }

    override fun onPause() {
        super.onPause()
        mBanner?.stopLoop()
    }
}