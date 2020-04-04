package com.ldl.wanandroid.ui.main.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ObjectUtils
import com.chad.library.adapter.base.loadmore.LoadMoreStatus
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.activity.BaseRootActivity
import com.ldl.wanandroid.contract.main.SearchListContract
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.presenter.main.SearchListPresenter
import com.ldl.wanandroid.ui.main.adapter.ArticleAdapter
import kotlinx.android.synthetic.main.activity_article.swipeRefreshLayout
import kotlinx.android.synthetic.main.activity_search_list.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * 作者：LDL 创建时间：2020/3/16
 * 类说明：
 */
class SearchListActivity : BaseRootActivity<SearchListPresenter>(), SearchListContract.View {

    companion object {
        private const val KEY = "key"

        fun start(key: String) {
            val bundle = Bundle()
            bundle.putString(KEY, key)
            ActivityUtils.startActivity(bundle, SearchListActivity::class.java)
        }
    }

    private val feedArticleDataList: ArrayList<FeedArticleData> by lazy { ArrayList<FeedArticleData>() }

    private lateinit var mAdapter: ArticleAdapter

    private var key: String? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityUtils.finishActivity(this, true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_search_list

    override fun initToolbar() {
        key = intent.getStringExtra(KEY)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = key
    }

    override fun onViewCreated() {
        super.onViewCreated()
        initRecyclerView()
        initSwipeRefreshLayout()
        initLoadMore()
    }

    override fun initEventAndData() {
        mPresenter?.getSearchList(key)
    }

    private fun initRecyclerView() {
        rvSearchList.layoutManager = LinearLayoutManager(this)
        mAdapter = ArticleAdapter(feedArticleDataList)
        rvSearchList.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->
            val feedArticleData = feedArticleDataList[position]
            WebViewActivity.start(feedArticleData.title, feedArticleData.link)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.measure(0, 0)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.refresh(key)
        }
    }

    private fun initLoadMore() {
        mAdapter.loadMoreModule?.apply {
            isEnableLoadMoreIfNotFullPage = false
            setOnLoadMoreListener {
                mPresenter?.loadMore(key)
            }
        }
    }

    override fun showSearchList(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {
        if (isRefresh) {
            if (mAdapter.loadMoreModule?.loadMoreStatus == LoadMoreStatus.End) {
                mAdapter.loadMoreModule?.loadMoreComplete()
            }
            swipeRefreshLayout.isRefreshing = false
            feedArticleDataList.clear()
            mAdapter.setNewData(feedArticleListData.datas)
        } else {
            if (ObjectUtils.isNotEmpty(feedArticleListData.datas)
                && feedArticleListData.total > feedArticleListData.curPage
            ) {
                mAdapter.addData(feedArticleListData.datas)
                mAdapter.loadMoreModule?.loadMoreComplete()
            } else
                mAdapter.loadMoreModule?.loadMoreEnd()
        }
        feedArticleDataList.addAll(feedArticleListData.datas)
    }
}