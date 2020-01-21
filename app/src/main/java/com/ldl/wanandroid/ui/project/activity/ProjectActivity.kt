package com.ldl.wanandroid.ui.project.activity

import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.ActivityUtils
import com.google.android.material.tabs.TabLayout
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_project
import com.ldl.wanandroid.base.activity.BaseRootActivity
import com.ldl.wanandroid.contract.project.ProjectContract
import com.ldl.wanandroid.core.bean.project.ProjectClassifyData
import com.ldl.wanandroid.presenter.project.ProjectPresenter
import com.ldl.wanandroid.ui.project.adapter.ProjectListPagerAdapter
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
class ProjectActivity : BaseRootActivity<ProjectPresenter>(), ProjectContract.View {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityUtils.finishActivity(this, true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutId(): Int = activity_project

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.menu_project)
    }

    override fun initEventAndData() {
        initLoadingStatusViewIfNeed(ll_content)
        mPresenter?.getProjectClassifyData()
    }

    private fun initTabLayout(list: List<ProjectClassifyData>) {
        list.forEachIndexed { index, projectClassifyData ->
            tab_project.addTab(tab_project.newTab())
            tab_project.getTabAt(index)?.text = projectClassifyData.name
        }
        tab_project.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                vp_project.currentItem = p0!!.position
            }
        })
    }

    private fun initViewPager(list: List<ProjectClassifyData>) {
        vp_project.currentItem = 0
        vp_project.offscreenPageLimit = list.size
        vp_project.adapter = ProjectListPagerAdapter(this, list)
        vp_project.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tab_project.getTabAt(position)?.select()
            }
        })
    }

    override fun showProjectClassify(projectClassifyDataList: List<ProjectClassifyData>) {
        initTabLayout(projectClassifyDataList)
        initViewPager(projectClassifyDataList)
    }
}