package com.ldl.wanandroid.ui.project.fragment

import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.fragment.LazyLoadFragment
import com.ldl.wanandroid.contract.project.ProjectContract
import com.ldl.wanandroid.presenter.project.ProjectPresenter

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class ProjectFragment : LazyLoadFragment<ProjectPresenter>(), ProjectContract.View {
    override fun loadData() {
    }

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun initEventAndData() {
    }
}