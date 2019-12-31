package com.ldl.wanandroid.presenter.knowledge

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.knowledge.KnowledgeContract
import com.ldl.wanandroid.core.DataManager
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class KnowledgePresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<KnowledgeContract.View>(dataManager), KnowledgeContract.Presenter {
}