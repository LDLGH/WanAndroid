package com.ldl.wanandroid.ui.navigation.adapter

import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.core.bean.navigation.NavigationListData
import com.ldl.wanandroid.ui.main.activity.WebViewActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout

/**
 * 作者：LDL 创建时间：2020/1/15
 * 类说明：
 */
class NavigationVPAdapter(list: ArrayList<NavigationListData>) :
    BaseQuickAdapter<NavigationListData, BaseViewHolder>(
        R.layout.item_vp_content, list
    ) {

    override fun convert(helper: BaseViewHolder, item: NavigationListData) {
        
        helper.setText(R.id.tv_title, item.name)

        val flContent = helper.getView<TagFlowLayout>(R.id.fl_content)
        flContent.adapter = object : TagAdapter<FeedArticleData>(item.articles) {
            override fun getView(parent: FlowLayout?, position: Int, t: FeedArticleData?): View {
                val tv = TextView(flContent.context)
                tv.text = t?.title
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
        flContent.setOnTagClickListener { _, position, _ ->
            val feedArticleData = item!!.articles[position]
            WebViewActivity.start(feedArticleData.title, feedArticleData.link)
            return@setOnTagClickListener true
        }
    }
}