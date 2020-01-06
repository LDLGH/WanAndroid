package com.ldl.wanandroid.ui.main.adapter

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.layout_banner
import com.ldl.wanandroid.core.bean.main.banner.BannerData
import com.ldl.wanandroid.utils.GlideApp
import com.zhpan.bannerview.holder.ViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * 作者：LDL 创建时间：2020/1/2
 * 类说明：
 */
class BannerViewHolder : ViewHolder<BannerData> {
    override fun getLayoutId(): Int = layout_banner
    override fun onBind(itemView: View?, data: BannerData?, position: Int, size: Int) {
        val imageView = itemView?.findViewById<ImageView>(R.id.iv_banner)
        GlideApp.with(imageView!!.context)
            .load(data?.imagePath)
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCornersTransformation(
                        30, 0,
                        RoundedCornersTransformation.CornerType.ALL
                    )
                )
            )
            .into(imageView)
    }
}