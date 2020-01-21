package com.ldl.wanandroid.ui.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.menu.MenuData

/**
 * 作者：LDL 创建时间：2020/1/6
 * 类说明：
 */
class MenuAdapter(data: ArrayList<MenuData>) :
    BaseQuickAdapter<MenuData, BaseViewHolder>(R.layout.item_menu, data) {

    override fun convert(helper: BaseViewHolder, item: MenuData?) {
        helper.setText(R.id.tv_menu, item?.name)
        helper.setImageResource(R.id.iv_menu, item!!.url)
    }
}