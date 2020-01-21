package com.ldl.wanandroid.ui.main.adapter

import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ObjectUtils
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData
import com.ldl.wanandroid.utils.GlideApp
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * 作者：LDL 创建时间：2020/1/2
 * 类说明：
 */
class ArticleListAdapter(datas: ArrayList<FeedArticleData>) :
    BaseQuickAdapter<FeedArticleData, BaseViewHolder>(R.layout.item_article_list, datas) {

    private val images: ArrayList<Int> by lazy { ArrayList<Int>() }

    init {
        images.add(R.drawable.bg_1)
        images.add(R.drawable.bg_2)
        images.add(R.drawable.bg_3)
        images.add(R.drawable.bg_4)
        images.add(R.drawable.bg_5)
        images.add(R.drawable.bg_6)
        images.add(R.drawable.bg_7)
        images.add(R.drawable.bg_8)
    }

    override fun convert(helper: BaseViewHolder, item: FeedArticleData?) {
        var author = "Android"
        if (!ObjectUtils.isEmpty(item?.author)) {
            author = item!!.author
        }
        helper.setText(R.id.tv_author, author)
        helper.setText(R.id.tv_title, item?.title)
        val ivCover = helper.getView<ImageView>(R.id.iv_cover)
        val random = (0 until images.size).random()
        GlideApp.with(ivCover.context)
            .load(images[random])
            .transform(
                CenterCrop(), RoundedCornersTransformation(
                    ConvertUtils.dp2px(5f), 0,
                    RoundedCornersTransformation.CornerType.ALL
                )
            )
            .into(ivCover)
    }
}