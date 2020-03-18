package com.ldl.wanandroid.ui.main.adapter

import android.graphics.Typeface
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.search.TopSearchData

/**
 * 作者：LDL 创建时间：2020/1/8
 * 类说明：
 */
class HotSearchListAdapter(topSearchDataList: ArrayList<TopSearchData>) :
    BaseQuickAdapter<TopSearchData, BaseViewHolder>(
        R.layout.item_hot_search_list, topSearchDataList
    ) {

    override fun convert(helper: BaseViewHolder, item: TopSearchData) {
        helper.setText(R.id.tv_ranking, helper.adapterPosition.toString())
        helper.setText(R.id.tv_name, item.name)
        helper.setTextColor(
            R.id.tv_ranking,
            ColorUtils.getColor(
                if (helper.adapterPosition < 4)
                    R.color.colorAccent
                else R.color.colorTextGray
            )
        )
        val tvName = helper.getView<TextView>(R.id.tv_name)
        tvName.typeface =
            if (helper.adapterPosition < 4) Typeface.DEFAULT_BOLD else Typeface.DEFAULT
        helper.setVisible(R.id.tv_hot, helper.adapterPosition < 4)
    }
}