package com.ldl.wanandroid.ui.main.adapter

import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.HomepageMultiData

/**
 * 作者：LDL 创建时间：2020/1/13
 * 类说明：
 */
class BottomLineProvider : BaseItemProvider<HomepageMultiData>() {

    override val itemViewType: Int = HomepageMultiData.BOTTOM_LINE

    override val layoutId: Int = R.layout.item_bottom_line

    override fun convert(helper: BaseViewHolder, data: HomepageMultiData?) {
    }
}