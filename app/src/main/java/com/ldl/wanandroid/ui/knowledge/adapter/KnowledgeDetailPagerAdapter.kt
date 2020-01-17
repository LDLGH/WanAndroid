package com.ldl.wanandroid.ui.knowledge.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.blankj.utilcode.util.GsonUtils
import com.ldl.wanandroid.core.bean.knowledge.KnowledgeHierarchyData
import com.ldl.wanandroid.ui.knowledge.fragment.KnowledgeDetailPagerFragment

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
class KnowledgeDetailPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val mList: ArrayList<KnowledgeHierarchyData> by lazy { ArrayList<KnowledgeHierarchyData>() }

    constructor(
        fragmentActivity: FragmentActivity,
        list: List<KnowledgeHierarchyData>
    ) : this(fragmentActivity) {
        mList.addAll(list)
    }

    override fun getItemCount(): Int = mList.size

    override fun createFragment(position: Int): Fragment =
        KnowledgeDetailPagerFragment.getInstance(mList[position].id)
}