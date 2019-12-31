package com.ldl.wanandroid.presenter.project

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.project.ProjectContract
import com.ldl.wanandroid.core.DataManager
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class ProjectPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<ProjectContract.View>(dataManager), ProjectContract.Presenter {
}