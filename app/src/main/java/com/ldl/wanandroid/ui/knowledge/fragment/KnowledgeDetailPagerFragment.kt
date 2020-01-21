package com.ldl.wanandroid.ui.knowledge.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.R.layout.fragment_knowledge_detail_pager
import com.ldl.wanandroid.base.fragment.BaseFragment
import com.ldl.wanandroid.contract.knowledge.KnowledgeDetailPagerContract
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.presenter.knowledge.KnowledgeDetailPagerPresenter
import com.ldl.wanandroid.ui.main.activity.WebViewActivity
import com.ldl.wanandroid.ui.main.adapter.ArticleAdapter
import kotlinx.android.synthetic.main.fragment_knowledge_detail_pager.*

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
class KnowledgeDetailPagerFragment : BaseFragment<KnowledgeDetailPagerPresenter>(),
    KnowledgeDetailPagerContract.View {

    companion object {
        private const val ID = "id"

        fun getInstance(id: Int): KnowledgeDetailPagerFragment {
            val fragment = KnowledgeDetailPagerFragment()
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

    override fun getLayoutId(): Int = fragment_knowledge_detail_pager

    override fun initEventAndData() {
        initSwipeRefreshLayout()
        initRecyclerView()
        initLoadMore()
        mId = arguments!!.getInt(ID)
        mPresenter?.getKnowledgeHierarchyDetailData(0, mId)
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

    override fun showKnowledgeHierarchyDetailList(
        feedArticleListData: FeedArticleListData,
        isRefresh: Boolean
    ) {
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