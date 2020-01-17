package com.ldl.wanandroid.ui.wx.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ldl.wanandroid.core.bean.wx.WxAuthor
import com.ldl.wanandroid.ui.wx.fragment.WXListPagerFragment

/**
 * 作者：LDL 创建时间：2020/1/17
 * 类说明：
 */
class WXListPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val mList: ArrayList<WxAuthor> by lazy {
        ArrayList<WxAuthor>()
    }

    constructor(
        fragmentActivity: FragmentActivity,
        list: List<WxAuthor>
    ) : this(fragmentActivity) {
        mList.addAll(list)
    }

    override fun getItemCount(): Int = mList.size

    override fun createFragment(position: Int): Fragment =
        WXListPagerFragment.getInstance(mList[position].id)
}