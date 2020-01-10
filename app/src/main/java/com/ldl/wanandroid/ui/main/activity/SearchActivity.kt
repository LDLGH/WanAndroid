package com.ldl.wanandroid.ui.main.activity

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.*
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_search
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.contract.main.SearchContract
import com.ldl.wanandroid.core.bean.main.search.TopSearchData
import com.ldl.wanandroid.core.dao.HistoryData
import com.ldl.wanandroid.presenter.main.SearchPresenter
import com.ldl.wanandroid.ui.main.adapter.HotSearchListAdapter
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_search.*

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
class SearchActivity : BaseActivity<SearchPresenter>(), SearchContract.View {

    private val mTopSearchDataList: ArrayList<TopSearchData> by lazy { ArrayList<TopSearchData>() }

    private lateinit var mAdapter: HotSearchListAdapter

    private var mFlHistory: TagFlowLayout? = null
    private var mClHistory: ConstraintLayout? = null

    override fun getLayoutId(): Int = activity_search

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun initEventAndData() {
        initEditText()
        initRecyclerView()
        initHead()
        mPresenter?.loadAllHistoryData()
        mPresenter?.getTopTopSearchData()
    }

    private fun initEditText() {
        et_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val s = et_search.text.toString()
                if (ObjectUtils.isEmpty(s)) {
                    return@setOnEditorActionListener true
                }
                mPresenter?.addHistoryData(s)
                KeyboardUtils.hideSoftInput(this)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    @SuppressLint("InflateParams")
    private fun initHead() {
        val headView = LayoutInflater.from(this).inflate(R.layout.layout_search_head, null)
        mAdapter.addHeaderView(headView)
        mClHistory = headView.findViewById(R.id.cl_history)
        val ivDel = headView.findViewById<ImageView>(R.id.iv_del)
        mFlHistory = headView.findViewById(R.id.fl_history)
        ivDel.setOnClickListener {
            mPresenter?.clearHistoryData()
            mClHistory?.visibility = View.GONE
        }
    }

    private fun initRecyclerView() {
        rv_search.setHasFixedSize(true)
        rv_search.layoutManager = LinearLayoutManager(this)
        mAdapter = HotSearchListAdapter(mTopSearchDataList)
        rv_search.adapter = mAdapter
        mAdapter.setOnItemClickListener { adapter, view, position -> }
    }

    private fun setTagFlowLayout(historyDataList: List<HistoryData>) {
        mFlHistory?.adapter = object : TagAdapter<HistoryData>(historyDataList) {
            override fun getView(parent: FlowLayout?, position: Int, t: HistoryData?): View {
                val tv = TextView(mActivity)
                tv.text = t?.data
                tv.setTextColor(ColorUtils.getColor(R.color.colorTextBlack))
                tv.textSize = 12f
                tv.setPadding(
                    ConvertUtils.dp2px(10f),
                    ConvertUtils.dp2px(4f),
                    ConvertUtils.dp2px(10f),
                    ConvertUtils.dp2px(4f)
                )
                tv.setBackgroundResource(R.drawable.selector_search_fl_bg)
                return tv
            }
        }
        mFlHistory?.setOnTagClickListener { view, position, parent ->

            return@setOnTagClickListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityUtils.finishActivity(this)
                return true
            }
            R.id.action_search -> {
                mPresenter?.addHistoryData(et_search.text.toString())
                KeyboardUtils.hideSoftInput(this)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showTopTopSearch(topSearchDataList: List<TopSearchData>) {
        mTopSearchDataList.addAll(topSearchDataList)
        mAdapter.notifyDataSetChanged()
    }

    override fun showHistory(historyDataList: List<HistoryData>) {
        if (ObjectUtils.isNotEmpty(historyDataList)) {
            mClHistory?.visibility = View.VISIBLE
            setTagFlowLayout(historyDataList)
        }
    }
}