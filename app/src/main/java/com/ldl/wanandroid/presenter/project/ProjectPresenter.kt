package com.ldl.wanandroid.presenter.project

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.project.ProjectContract
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.bean.project.ProjectClassifyData
import com.ldl.wanandroid.core.http.rx.BaseObserver
import com.ldl.wanandroid.utils.RxUtils
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class ProjectPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<ProjectContract.View>(dataManager), ProjectContract.Presenter {

    override fun getProjectClassifyData() {
        addSubscribe(
            dataManager.getProjectClassifyData()
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .doOnSubscribe {
                    mView?.showLoading()
                }
                .doFinally {
                    mView?.showNormal()
                }
                .subscribeWith(object : BaseObserver<List<ProjectClassifyData>>(mView!!) {
                    override fun onNext(t: List<ProjectClassifyData>) {
                        mView?.showProjectClassify(t)
                    }
                })
        )
    }

}