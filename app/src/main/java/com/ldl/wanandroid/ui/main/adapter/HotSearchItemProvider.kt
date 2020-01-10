package com.ldl.wanandroid.ui.main.adapter

import android.view.View
import android.widget.TextView
import androidx.core.view.setPadding
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.HomepageMultiData
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
class HotSearchItemProvider : BaseItemProvider<HomepageMultiData>() {

    override val itemViewType: Int
        get() = HomepageMultiData.HOT_SEARCH

    override val layoutId: Int
        get() = R.layout.item_hot_search

    override fun convert(helper: BaseViewHolder, data: HomepageMultiData?) {
        helper.setText(R.id.tv_title, data?.title)
        helper.setText(R.id.tv_desc, data?.desc)

        val flowLayout = helper.getView<TagFlowLayout>(R.id.flowLayout)

        val list = GsonUtils.fromJson<ArrayList<String>>(
            data?.data,
            GsonUtils.getListType(String::class.java)
        )
        val tagAdapter = object : TagAdapter<String>(list) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tv = TextView(flowLayout.context)
                tv.text = t
                tv.setTextColor(ColorUtils.getColor(R.color.colorWhite))
                tv.textSize = 12f
                tv.setPadding(ConvertUtils.dp2px(4f))
                tv.setBackgroundColor(
                    if (position % 2 == 0) ColorUtils.getColor(R.color.colorPrimary)
                    else ColorUtils.getColor(R.color.colorAccent)
                )
                return tv
            }
        }
        flowLayout.adapter = tagAdapter
        flowLayout.setOnTagClickListener { view, position, parent ->
            ToastUtils.showShort(list[position])
            return@setOnTagClickListener true
        }
    }
}