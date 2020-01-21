package com.ldl.wanandroid.ui.project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ldl.wanandroid.core.bean.project.ProjectClassifyData
import com.ldl.wanandroid.ui.project.fragment.ProjectListFragment

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
class ProjectListPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    constructor(
        fragmentActivity: FragmentActivity,
        list: List<ProjectClassifyData>
    ) : this(
        fragmentActivity
    ) {
        mList.addAll(list)
    }

    private val mList: ArrayList<ProjectClassifyData> by lazy {
        ArrayList<ProjectClassifyData>()
    }

    override fun getItemCount(): Int = mList.size

    override fun createFragment(position: Int): Fragment =
        ProjectListFragment.getInstance(mList[position].id)
}