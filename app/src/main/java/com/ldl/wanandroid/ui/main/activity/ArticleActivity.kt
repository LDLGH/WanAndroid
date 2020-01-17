package com.ldl.wanandroid.ui.main.activity

import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_article
import com.ldl.wanandroid.base.activity.BaseRootActivity
import com.ldl.wanandroid.contract.main.ArticleContract
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.presenter.main.ArticlePresenter
import com.ldl.wanandroid.ui.main.adapter.ArticleAdapter
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * 作者：LDL 创建时间：2020/1/14
 * 类说明：
 */
class ArticleActivity : BaseRootActivity<ArticlePresenter>(), ArticleContract.View {

    private val feedArticleDataList: ArrayList<FeedArticleData> by lazy { ArrayList<FeedArticleData>() }

    private lateinit var mAdapter: ArticleAdapter

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityUtils.finishActivity(this, true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutId(): Int = activity_article

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.menu_article)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        initRecyclerView()
        initSwipeRefreshLayout()
        initLoadMore()
    }

    override fun initEventAndData() {
        mPresenter?.getFeedArticleList()
    }

    private fun initRecyclerView() {
        rvArticle.layoutManager = LinearLayoutManager(this)
        mAdapter = ArticleAdapter(feedArticleDataList)
        rvArticle.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->
            val feedArticleData = feedArticleDataList[position]
            WebViewActivity.start(feedArticleData.title, feedArticleData.link)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.measure(0, 0)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.refresh()
        }
    }

    private fun initLoadMore() {
        mAdapter.loadMoreModule?.apply {
            isEnableLoadMoreIfNotFullPage = false
            setOnLoadMoreListener {
                mPresenter?.loadMore()
            }
        }
    }

    override fun showArticleList(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {
        if (isRefresh) {
            mAdapter.loadMoreModule?.isEnableLoadMore = true
            swipeRefreshLayout.isRefreshing = false
            feedArticleDataList.clear()
            mAdapter.setNewData(feedArticleListData.datas)
        } else {
            if (ObjectUtils.isNotEmpty(feedArticleListData.datas)) {
                mAdapter.addData(feedArticleListData.datas)
                mAdapter.loadMoreModule?.loadMoreComplete()
            } else
                mAdapter.loadMoreModule?.loadMoreEnd()
        }
        feedArticleDataList.addAll(feedArticleListData.datas)
    }

}