package com.ldl.wanandroid.ui.main.adapter

import android.widget.TextView
import com.blankj.utilcode.util.ObjectUtils
import com.blankj.utilcode.util.StringUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.collect.FeedArticleData

/**
 * 作者：LDL 创建时间：2020/1/14
 * 类说明：
 */
class ArticleAdapter(list: ArrayList<FeedArticleData>) :
    BaseQuickAdapter<FeedArticleData, BaseViewHolder>(R.layout.item_article, list),
    LoadMoreModule {

    override fun convert(helper: BaseViewHolder, item: FeedArticleData?) {
        item?.apply {
            var shareUser = "Android"
            if (!ObjectUtils.isEmpty(author)) {
                shareUser = author
            }
            helper.setText(R.id.tv_shareUser, shareUser)
            helper.setText(R.id.tv_chapter, "${superChapterName}/${chapterName}")
//            val toDBC = StringUtils.toDBC(title)
            helper.setText(R.id.tv_title, title)
            helper.setText(R.id.tv_time, niceDate)

            val tvZan = helper.getView<TextView>(R.id.tv_zan)
            tvZan.setCompoundDrawablesWithIntrinsicBounds(
                if (collect) R.drawable.ic_star_blue_24dp else R.drawable.ic_star_gray_24dp,
                0,
                0,
                0
            )
        }
    }

}