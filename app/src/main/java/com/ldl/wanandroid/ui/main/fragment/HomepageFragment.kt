package com.ldl.wanandroid.ui.main.fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.fragment_homepage
import com.ldl.wanandroid.R.layout.head_banner
import com.ldl.wanandroid.base.fragment.BaseRootFragment
import com.ldl.wanandroid.contract.main.HomepageContract
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.presenter.main.HomepagePresenter
import com.ldl.wanandroid.ui.main.adapter.ArticleListAdapter
import com.ldl.wanandroid.ui.main.adapter.BannerViewHolder
import com.ldl.wanandroid.widget.ScrollTextSwitcher
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.IndicatorStyle
import com.zhpan.bannerview.constants.PageStyle.MULTI_PAGE_OVERLAP
import kotlinx.android.synthetic.main.fragment_homepage.*

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
@SuppressLint("InflateParams")
class HomepageFragment : BaseRootFragment<HomepagePresenter>(), HomepageContract.View {

    override fun getLayoutId(): Int = fragment_homepage

    private lateinit var mHeadView: View
    private var mBanner: BannerViewPager<BannerData, BannerViewHolder>? = null
    private var mTextSwitcher: ScrollTextSwitcher? = null
    private lateinit var mAdapter: ArticleListAdapter

    private var mFeedArticleDatas = ArrayList<FeedArticleData>()


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
        mHeadView = LayoutInflater.from(mActivity).inflate(head_banner, null)
        mBanner = mHeadView.findViewById(R.id.banner_view)
        mTextSwitcher = mHeadView.findViewById(R.id.textSwitcher)
        val tvOnSearch = mHeadView.findViewById<TextView>(R.id.tv_onSearch)
        mTextSwitcher?.setOnItemClickListener(object : ScrollTextSwitcher.OnItemClickListener {
            override fun onItemClick(position: Int) {
                showSnackBar(mTextSwitcher?.mTexts?.get(position))
            }
        })
        tvOnSearch.setOnClickListener {

        }
    }

    private fun initRecyclerView() {
        mAdapter = ArticleListAdapter(mFeedArticleDatas)
        recyclerView.layoutManager = LinearLayoutManager(mActivity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter
        mAdapter.addHeaderView(mHeadView)
        mAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }

    private fun initBanner(bannerDataList: List<BannerData>) {
        mBanner?.also {
            it.setIndicatorStyle(IndicatorStyle.DASH)
                .setPageStyle(MULTI_PAGE_OVERLAP)
                .setIndicatorHeight(ConvertUtils.dp2px(3f))
                .setIndicatorWidth(ConvertUtils.dp2px(3f), ConvertUtils.dp2px(10f))
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
            mAdapter.loadMoreModule?.isEnableLoadMore = false
            mPresenter?.refresh()
        }
        mAdapter.loadMoreModule?.setOnLoadMoreListener {
            mPresenter?.loadMore()
        }
    }

    override fun showArticleList(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {
        if (isRefresh) {
            swipeRefreshLayout.isRefreshing = false
            mFeedArticleDatas.clear()
            mFeedArticleDatas.addAll(feedArticleListData.datas)
            mAdapter.setNewData(feedArticleListData.datas)
            mAdapter.loadMoreModule?.isEnableLoadMore = true
        } else {
            mFeedArticleDatas.addAll(feedArticleListData.datas)
            mAdapter.addData(feedArticleListData.datas)
            mAdapter.loadMoreModule?.loadMoreComplete()
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
        mTextSwitcher?.mTexts = list
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