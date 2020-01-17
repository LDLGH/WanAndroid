package com.ldl.wanandroid.ui.knowledge.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.GsonUtils
import com.google.android.material.tabs.TabLayout
import com.ldl.wanandroid.R.layout.activity_knowledge_detail
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.contract.knowledge.KnowledgeDetailContract
import com.ldl.wanandroid.core.bean.knowledge.KnowledgeHierarchyData
import com.ldl.wanandroid.presenter.knowledge.KnowledgeDetailPresenter
import com.ldl.wanandroid.ui.knowledge.adapter.KnowledgeDetailPagerAdapter
import kotlinx.android.synthetic.main.activity_knowledge_detail.*
import kotlinx.android.synthetic.main.common_toolbar.*


/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
class KnowledgeDetailListActivity : BaseActivity<KnowledgeDetailPresenter>(),
    KnowledgeDetailContract.View {

    companion object {
        private const val TITLE = "title"
        private const val DATA = "data"

        fun start(title: String, data: String) {
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            bundle.putString(DATA, data)
            ActivityUtils.startActivity(bundle, KnowledgeDetailListActivity::class.java)
        }
    }

    private var mTitle = ""
    private var mData: KnowledgeHierarchyData? = null

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityUtils.finishActivity(this, true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutId(): Int = activity_knowledge_detail

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = mTitle
    }

    override fun onViewCreated() {
        super.onViewCreated()
        getBundleData()
        initViewPager()
        initTabLayout()
    }

    override fun initEventAndData() {

    }

    private fun initTabLayout() {
        mData!!.children.forEachIndexed { index, knowledgeHierarchyData ->
            tab_knowledge.addTab(tab_knowledge.newTab())
            tab_knowledge.getTabAt(index)?.text = knowledgeHierarchyData.name
        }
        tab_knowledge.addOnTabSelectedListener(object :
            TabLayout.BaseOnTabSelectedListener<TabLayout.Tab> {

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                vp_knowledge.currentItem = p0!!.position
            }
        })
    }

    private fun initViewPager() {
        vp_knowledge.currentItem = 0
        vp_knowledge.offscreenPageLimit = mData!!.children.size
        vp_knowledge.adapter = KnowledgeDetailPagerAdapter(this, mData!!.children)
        vp_knowledge.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tab_knowledge.getTabAt(position)?.select()
            }
        })
    }

    override fun getBundleData() {
        intent.extras?.apply {
            mTitle = getString(TITLE, "")
            mData = GsonUtils.fromJson(
                getString(DATA, ""),
                KnowledgeHierarchyData::class.java
            )
        }
    }
}