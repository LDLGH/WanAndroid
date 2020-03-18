package com.ldl.wanandroid.ui.main.adapter

import android.view.View
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.provider.BaseItemProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.bean.main.HomepageMultiData
import com.ldl.wanandroid.ui.main.activity.LoginAndRegisterActivity


/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
class LoginItemProvider : BaseItemProvider<HomepageMultiData>() {

    init {
        addChildClickViewIds(R.id.tv_login)
    }

    override val itemViewType: Int
        get() = HomepageMultiData.LOGIN

    override val layoutId: Int
        get() = R.layout.item_login

    override fun convert(helper: BaseViewHolder, data: HomepageMultiData) {

    }

    override fun onChildClick(
        helper: BaseViewHolder,
        view: View,
        data: HomepageMultiData,
        position: Int
    ) {
        ActivityUtils.startActivity(LoginAndRegisterActivity::class.java)
    }
}