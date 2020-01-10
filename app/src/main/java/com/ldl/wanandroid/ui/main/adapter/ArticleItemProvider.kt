package com.ldl.wanandroid.ui.main.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.HomepageMultiData
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.utils.CustomDividerItemDecoration

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
class ArticleItemProvider : BaseItemProvider<HomepageMultiData>() {

    init {
        addChildClickViewIds(R.id.tv_more)
    }

    override val itemViewType: Int
        get() = HomepageMultiData.ARTICLE
    override val layoutId: Int
        get() = R.layout.item_main_content

    override fun convert(helper: BaseViewHolder, data: HomepageMultiData?) {
        helper.setText(R.id.tv_title, data?.title)
        helper.setText(R.id.tv_desc, data?.desc)

        val rvContent = helper.getView<RecyclerView>(R.id.rv_content)
        rvContent.layoutManager =
            LinearLayoutManager(rvContent.context, LinearLayoutManager.HORIZONTAL, false)
        rvContent.setHasFixedSize(true)
        if (rvContent.itemDecorationCount < 1) {
            rvContent.addItemDecoration(
                CustomDividerItemDecoration(
                    rvContent.context,
                    CustomDividerItemDecoration.VERTICAL_LIST,
                    ConvertUtils.dp2px(10f),
                    Color.TRANSPARENT
                )
            )
        }
        val articleData =
            GsonUtils.fromJson<ArrayList<FeedArticleData>>(
                data?.data,
                GsonUtils.getListType(FeedArticleData::class.java)
            )
        val articleListAdapter = ArticleListAdapter(articleData)
        rvContent.adapter = articleListAdapter
        articleListAdapter.setOnItemClickListener { adapter, view, position ->

        }
    }

    override fun onChildClick(
        helper: BaseViewHolder,
        view: View,
        data: HomepageMultiData,
        position: Int
    ) {
        ToastUtils.showShort("onChildClick")
    }
}