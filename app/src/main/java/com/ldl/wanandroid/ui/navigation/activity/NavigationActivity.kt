package com.ldl.wanandroid.ui.navigation.activity

import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.ActivityUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_navigation
import com.ldl.wanandroid.base.activity.BaseRootActivity
import com.ldl.wanandroid.contract.navigation.NavigationContract
import com.ldl.wanandroid.core.bean.navigation.NavigationListData
import com.ldl.wanandroid.presenter.navigation.NavigationPresenter
import com.ldl.wanandroid.ui.navigation.adapter.NavigationMenuAdapter
import com.ldl.wanandroid.ui.navigation.adapter.NavigationVPAdapter
import com.ldl.wanandroid.utils.CenterLayoutManager
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * 作者：LDL 创建时间：2020/1/15
 * 类说明：
 */
class NavigationActivity : BaseRootActivity<NavigationPresenter>(), NavigationContract.View {

    private val mNavigationListDatas: ArrayList<NavigationListData> by lazy { ArrayList<NavigationListData>() }
    private lateinit var mMenuAdapter: NavigationMenuAdapter
    private lateinit var mNavigationVPAdapter: NavigationVPAdapter
    private lateinit var mCenterLayoutManager: CenterLayoutManager
    private var lastPos = 0

    override fun getLayoutId(): Int = activity_navigation

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.menu_navigation)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        initMenuRecyclerView()
        initViewPager()
        initSwipeRefreshLayout()
    }

    override fun initEventAndData() {
        mPresenter?.getNavigationListData()
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.measure(0, 0)
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            mPresenter?.getNavigationListData()
        }
    }

    private fun initMenuRecyclerView() {
        mCenterLayoutManager = CenterLayoutManager(this)
        rv_menu.itemAnimator = null
        rv_menu.layoutManager = mCenterLayoutManager
        rv_menu.setHasFixedSize(true)
        mMenuAdapter = NavigationMenuAdapter(mNavigationListDatas)
        rv_menu.adapter = mMenuAdapter
        mMenuAdapter.setOnItemClickListener { _, _, position ->
            if (lastPos == position) {
                return@setOnItemClickListener
            }
            pageSelected(position)
            vp_content.setCurrentItem(position, false)
        }
    }

    private fun initViewPager() {
        mNavigationVPAdapter = NavigationVPAdapter(mNavigationListDatas)
        vp_content.adapter = mNavigationVPAdapter
        vp_content.orientation = ViewPager2.ORIENTATION_VERTICAL
        vp_content.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                pageSelected(position)
            }
        })
    }

    private fun pageSelected(position: Int) {
        mNavigationListDatas[lastPos].select = false
        mMenuAdapter.notifyItemChanged(lastPos)
        mCenterLayoutManager.smoothScrollToPosition(rv_menu, RecyclerView.State(), position)
        mNavigationListDatas[position].select = true
        mMenuAdapter.notifyItemChanged(position)
        lastPos = position
    }

    override fun showNavigationList(navigationListDataList: ArrayList<NavigationListData>) {
        swipeRefreshLayout.isRefreshing = false

        lastPos = 0
        mNavigationListDatas.clear()
        navigationListDataList[0].select = true
        mNavigationListDatas.addAll(navigationListDataList)
        mMenuAdapter.notifyDataSetChanged()
        mNavigationVPAdapter.notifyDataSetChanged()
        vp_content.setCurrentItem(0, false)
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
}