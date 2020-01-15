package com.ldl.wanandroid.ui.main.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.setPadding
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.fragment.BaseBottomDialogFragment
import com.ldl.wanandroid.core.bean.main.search.UsefulSiteData
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
        fl_useful_sites.adapter = tagAdapter
        fl_useful_sites.setOnTagClickListener { view, position, parent ->
            ToastUtils.showShort(list[position].name)
            return@setOnTagClickListener true
        }
    }
}