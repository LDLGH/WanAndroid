package com.ldl.wanandroid.ui.navigation.adapter

import android.graphics.Typeface
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.navigation.NavigationListData

/**
 * 作者：LDL 创建时间：2020/1/15
 * 类说明：
 */
class NavigationMenuAdapter(list: ArrayList<NavigationListData>) :
    BaseQuickAdapter<NavigationListData, BaseViewHolder>(R.layout.item_navigation_menu, list) {

    override fun convert(helper: BaseViewHolder, item: NavigationListData) {
        item.apply {
            helper.setBackgroundColor(
                R.id.cl_item,
                if (select) ColorUtils.getColor(R.color.colorWhite)
                else ColorUtils.getColor(R.color.colorBackground)
            )
            helper.setTextColorRes(
                R.id.tv_menu,
                if (select) R.color.colorTextBlack
                else R.color.colorTextGray
            )
            helper.setText(R.id.tv_menu, name)
            val tvMenu = helper.getView<TextView>(R.id.tv_menu)
            tvMenu.textSize = if (select) 16f else 14f
            tvMenu.typeface =
                if (select) Typeface.DEFAULT_BOLD else Typeface.DEFAULT

            helper.setVisible(R.id.view_tag, select)
        }
    }

}