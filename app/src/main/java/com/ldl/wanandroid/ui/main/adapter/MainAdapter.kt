package com.ldl.wanandroid.ui.main.adapter

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.MainData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.utils.CustomDividerItemDecoration
import com.ldl.wanandroid.utils.CustomDividerItemDecoration.VERTICAL_LIST

/**
 * 作者：LDL 创建时间：2020/1/6
 * 类说明：
 */
class MainAdapter(data: ArrayList<MainData>) :
    BaseQuickAdapter<MainData, BaseViewHolder>(R.layout.item_main_content, data) {

    override fun convert(helper: BaseViewHolder, item: MainData?) {
        helper.setText(R.id.tv_title, item?.title)
        helper.setText(R.id.tv_desc, item?.desc)
        addChildClickViewIds(R.id.tv_more)

        val rvContent = helper.getView<RecyclerView>(R.id.rv_content)
        rvContent.layoutManager =
            LinearLayoutManager(rvContent.context, LinearLayoutManager.HORIZONTAL, false)
        rvContent.setHasFixedSize(true)
        if (rvContent.itemDecorationCount < 1) {
            rvContent.addItemDecoration(
                CustomDividerItemDecoration(
                    rvContent.context,
                    VERTICAL_LIST,
                    ConvertUtils.dp2px(10f),
                    Color.TRANSPARENT
                )
            )
        }
        val articleData =
            GsonUtils.fromJson<ArrayList<FeedArticleData>>(
                item?.data,
                GsonUtils.getListType(FeedArticleData::class.java)
            )
        val articleListAdapter = ArticleListAdapter(articleData)
        rvContent.adapter = articleListAdapter
        articleListAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }
}