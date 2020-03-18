package com.ldl.wanandroid.ui.project.adapter

import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.utils.GlideApp
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * 作者：LDL 创建时间：2020/1/19
 * 类说明：
 */
class ProjectListAdapter(datas: ArrayList<FeedArticleData>) :
    BaseQuickAdapter<FeedArticleData, BaseViewHolder>(R.layout.item_project_list, datas),
    LoadMoreModule {

    init {
        addChildClickViewIds(R.id.iv_more)
    }

    override fun convert(helper: BaseViewHolder, item: FeedArticleData) {
        helper.setText(R.id.tv_title, item.title)
        helper.setText(R.id.tv_shareUser, item.author)
        val ivCover = helper.getView<ImageView>(R.id.iv_cover)
        GlideApp.with(ivCover.context)
            .load(item.envelopePic)
            .transform(
                RoundedCornersTransformation(
                    ConvertUtils.dp2px(5f), 0,
                    RoundedCornersTransformation.CornerType.TOP
                )
            )
            .into(ivCover)
    }

}