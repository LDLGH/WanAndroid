package com.ldl.wanandroid.ui.wx.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.R.layout.fragment_wx_list_pager
import com.ldl.wanandroid.base.fragment.BaseFragment
import com.ldl.wanandroid.contract.wx.WXListPagerContract
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.presenter.wx.WXListPagerPresenter
import com.ldl.wanandroid.ui.main.activity.WebViewActivity
import com.ldl.wanandroid.ui.main.adapter.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_wx_list_pager.*

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
class WXListPagerFragment : BaseFragment<WXListPagerPresenter>(), WXListPagerContract.View {

    companion object {
        private const val ID = "id"

        fun getInstance(id: Int): WXListPagerFragment {
            val fragment = WXListPagerFragment()
            val bundle = Bundle()
            bundle.putInt(ID, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mId = 0

    private val mFeedArticleDataList: ArrayList<FeedArticleData> by lazy {
        ArrayList<FeedArticleData>()
    }

    private lateinit var mAdapter: ArticleAdapter

    override fun getLayoutId(): Int = fragment_wx_list_pager

    override fun initEventAndData() {
        initSwipeRefreshLayout()
        initRecyclerView()
        initLoadMore()
        mId = arguments!!.getInt(ID)
        mPresenter?.getWxSumData(mId, 0)
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.measure(0, 0)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.refresh(mId)
        }
    }

    private fun initRecyclerView() {
        rvPager.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = ArticleAdapter(mFeedArticleDataList)
        rvPager.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->
            val feedArticleData = mFeedArticleDataList[position]
            WebViewActivity.start(feedArticleData.title, feedArticleData.link)
        }
    }

    private fun initLoadMore() {
        mAdapter.loadMoreModule?.apply {
            isEnableLoadMoreIfNotFullPage = false
            setOnLoadMoreListener {
                mPresenter?.loadMore(mId)
            }
        }
    }

    override fun showWxSumData(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {
        if (isRefresh) {
            swipeRefreshLayout.isRefreshing = false
            mFeedArticleDataList.clear()
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
        mFeedArticleDataList.addAll(feedArticleListData.datas)
    }
}