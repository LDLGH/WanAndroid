package com.ldl.wanandroid.ui.main.adapter

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.HomepageMultiData
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData
import com.ldl.wanandroid.ui.main.activity.WebViewActivity
import com.ldl.wanandroid.ui.main.fragment.UsefulSitesDialogFragment
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
class UsefulSitesItemProvider : BaseItemProvider<HomepageMultiData>() {

    init {
        addChildClickViewIds(R.id.tv_more)
    }

    override val itemViewType: Int
        get() = HomepageMultiData.USEFUL_SITES

    override val layoutId: Int
        get() = R.layout.item_useful_sites

    override fun convert(helper: BaseViewHolder, data: HomepageMultiData) {
        helper.setText(R.id.tv_title, data.title)
        helper.setText(R.id.tv_desc, data.desc)
        val flowLayout = helper.getView<TagFlowLayout>(R.id.flowLayout)

        var list = GsonUtils.fromJson<List<UsefulSiteData>>(
            data.data,
            GsonUtils.getListType(UsefulSiteData::class.java)
        )
        if (list.size > 10) {
            list = list.subList(0, 9)
        }
        val tagAdapter = object : TagAdapter<UsefulSiteData>(list) {
            override fun getView(parent: FlowLayout?, position: Int, t: UsefulSiteData?): View {
                val tv = TextView(flowLayout.context)
                tv.text = t?.name
                tv.setTextColor(ColorUtils.getColor(R.color.colorTextBlack))
                tv.textSize = 12f
                tv.setPadding(
                    ConvertUtils.dp2px(10f),
                    ConvertUtils.dp2px(4f),
                    ConvertUtils.dp2px(10f),
                    ConvertUtils.dp2px(4f)
                )
                tv.setBackgroundResource(R.drawable.selector_search_fl_bg)
                return tv
            }
        }
        flowLayout.adapter = tagAdapter
        flowLayout.setOnTagClickListener { _, position, _ ->
            val usefulSiteData = list[position]
            WebViewActivity.start(usefulSiteData.name, usefulSiteData.link)
            return@setOnTagClickListener true
        }
    }

    override fun onChildClick(
        helper: BaseViewHolder,
        view: View,
        data: HomepageMultiData,
        position: Int
    ) {
        val usefulSiteDataList = GsonUtils.fromJson<List<UsefulSiteData>>(
            data.data,
            GsonUtils.getListType(UsefulSiteData::class.java)
        )
        val fragment = UsefulSitesDialogFragment.getInstance(usefulSiteDataList)
        fragment.show(
            (context as AppCompatActivity).supportFragmentManager,
            "UsefulSitesDialogFragment"
        )
    }
}