package com.ldl.wanandroid.ui.wx.activity

import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.ActivityUtils
import com.google.android.material.tabs.TabLayout
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_wx_list
import com.ldl.wanandroid.base.activity.BaseRootActivity
import com.ldl.wanandroid.contract.wx.WXContract
import com.ldl.wanandroid.core.bean.wx.WxAuthor
import com.ldl.wanandroid.presenter.wx.WXPresenter
import com.ldl.wanandroid.ui.wx.adapter.WXListPagerAdapter
import kotlinx.android.synthetic.main.activity_wx_list.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
class WXListActivity : BaseRootActivity<WXPresenter>(), WXContract.View {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityUtils.finishActivity(this, true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutId(): Int = activity_wx_list

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.menu_wx)
    }

    override fun initEventAndData() {
        initLoadingStatusViewIfNeed(ll_content)
        mPresenter?.getWxAuthorListData()
    }

    private fun initTabLayout(list: List<WxAuthor>) {
        list.forEachIndexed { index, wxAuthor ->
            tab_wx.addTab(tab_wx.newTab())
            tab_wx.getTabAt(index)?.text = wxAuthor.name
        }
        tab_wx.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                vp_wx.currentItem = p0!!.position
            }
        })
    }

    private fun initViewPager(list: List<WxAuthor>) {
        vp_wx.currentItem = 0
        vp_wx.offscreenPageLimit = list.size
        vp_wx.adapter = WXListPagerAdapter(this, list)
        vp_wx.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tab_wx.getTabAt(position)?.select()
            }
        })
    }

    override fun showWxAuthorList(wxAuthorList: List<WxAuthor>) {
        initTabLayout(wxAuthorList)
        initViewPager(wxAuthorList)
    }

}