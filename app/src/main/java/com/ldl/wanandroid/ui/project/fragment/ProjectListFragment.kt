package com.ldl.wanandroid.ui.project.fragment

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ObjectUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.fragment.BaseFragment
import com.ldl.wanandroid.contract.project.ProjectListContract
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleListData
import com.ldl.wanandroid.presenter.project.ProjectListPresenter
import com.ldl.wanandroid.ui.main.activity.WebViewActivity
import com.ldl.wanandroid.ui.project.adapter.ProjectListAdapter
import com.ldl.wanandroid.utils.CustomDividerItemDecoration
import com.ldl.wanandroid.utils.CustomDividerItemDecoration.BOTH_SET
import kotlinx.android.synthetic.main.fragment_project_list.*

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
class ProjectListFragment : BaseFragment<ProjectListPresenter>(), ProjectListContract.View {

    companion object {
        private const val ID = "id"

        fun getInstance(id: Int): ProjectListFragment {
            val fragment = ProjectListFragment()
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

    private lateinit var mAdapter: ProjectListAdapter

    override fun getLayoutId(): Int = R.layout.fragment_project_list

    override fun initEventAndData() {
        initSwipeRefreshLayout()
        initRecyclerView()
        initLoadMore()
        mId = arguments!!.getInt(ID)
        mPresenter?.getProjectListData(0, mId)
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.measure(0, 0)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.refresh(mId)
        }
    }

    private fun initRecyclerView() {
        rvPager.layoutManager = GridLayoutManager(mActivity, 2)
        rvPager.addItemDecoration(
            CustomDividerItemDecoration(
                mActivity,
                BOTH_SET,
                ConvertUtils.dp2px(10f),
                ColorUtils.getColor(R.color.colorBackground)
            )
        )
        mAdapter = ProjectListAdapter(mFeedArticleDataList)
        rvPager.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->
            val feedArticleData = mFeedArticleDataList[position]
            WebViewActivity.start(feedArticleData.title, feedArticleData.link)
        }
        mAdapter.setOnItemChildClickListener { adapter, view, position ->  }
    }

    private fun initLoadMore() {
        mAdapter.loadMoreModule?.apply {
            isEnableLoadMoreIfNotFullPage = false
            setOnLoadMoreListener {
                mPresenter?.loadMore(mId)
            }
        }
    }

    override fun showProjectList(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {
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