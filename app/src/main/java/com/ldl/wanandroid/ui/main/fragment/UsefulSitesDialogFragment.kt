package com.ldl.wanandroid.ui.main.fragment

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.fragment.BaseBottomDialogFragment
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData
import com.ldl.wanandroid.ui.main.activity.WebViewActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.dialog_useful_sites.*

/**
 * 作者：LDL 创建时间：2020/1/13
 * 类说明：
 */
class UsefulSitesDialogFragment : BaseBottomDialogFragment() {

    companion object {

        private const val BUNDLE_DATA = "bundle_data"

        fun getInstance(list: List<UsefulSiteData>): UsefulSitesDialogFragment {
            val fragment = UsefulSitesDialogFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_DATA, GsonUtils.toJson(list))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayout(): Int = R.layout.dialog_useful_sites

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener {
            dismissAllowingStateLoss()
        }
    }

    override fun initEventAndData() {
        val data = arguments?.getString(BUNDLE_DATA)
        val list = GsonUtils.fromJson<List<UsefulSiteData>>(
            data,
            GsonUtils.getListType(UsefulSiteData::class.java)
        )
        val tagAdapter = object : TagAdapter<UsefulSiteData>(list) {
            override fun getView(parent: FlowLayout?, position: Int, t: UsefulSiteData?): View {
                val tv = TextView(mActivity)
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
        fl_useful_sites.adapter = tagAdapter
        fl_useful_sites.setOnTagClickListener { _, position, _ ->
            val usefulSiteData = list[position]
            WebViewActivity.start(usefulSiteData.name, usefulSiteData.link)
            return@setOnTagClickListener true
        }
    }
}