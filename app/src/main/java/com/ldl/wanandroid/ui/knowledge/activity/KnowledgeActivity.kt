package com.ldl.wanandroid.ui.knowledge.activity

import android.view.MenuItem
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.GsonUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_knowledge
import com.ldl.wanandroid.base.activity.BaseRootActivity
import com.ldl.wanandroid.contract.knowledge.KnowledgeContract
import com.ldl.wanandroid.core.bean.knowledge.KnowledgeHierarchyData
import com.ldl.wanandroid.presenter.knowledge.KnowledgePresenter
import com.ldl.wanandroid.ui.knowledge.adapter.KnowledgeAdapter
import com.ldl.wanandroid.utils.StaggeredDividerItemDecoration
import kotlinx.android.synthetic.main.activity_knowledge.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
class KnowledgeActivity : BaseRootActivity<KnowledgePresenter>(), KnowledgeContract.View {

    private lateinit var mAdapter: KnowledgeAdapter

    private val mList: ArrayList<KnowledgeHierarchyData> by lazy {
        ArrayList<KnowledgeHierarchyData>()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityUtils.finishActivity(this, true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutId(): Int = activity_knowledge

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.menu_knowledge)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        initSwipeRefreshLayout()
        initRecyclerView()
    }

    override fun initEventAndData() {
        mPresenter?.getKnowledgeHierarchyData()
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.measure(0, 0)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.getKnowledgeHierarchyData()
        }
    }

    private fun initRecyclerView() {
        rvKnowledge.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rvKnowledge.addItemDecoration(StaggeredDividerItemDecoration(this, 10))
        mAdapter = KnowledgeAdapter(mList)
        rvKnowledge.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->
            val knowledgeHierarchyData = mList[position]
            KnowledgeDetailListActivity.start(
                knowledgeHierarchyData.name,
                GsonUtils.toJson(knowledgeHierarchyData)
            )
        }
    }


    override fun showKnowledgeHierarchy(knowledgeHierarchyDataList: List<KnowledgeHierarchyData>) {
        swipeRefreshLayout.isRefreshing = false
        mList.clear()
        mList.addAll(knowledgeHierarchyDataList)
        mAdapter.notifyDataSetChanged()
    }


}