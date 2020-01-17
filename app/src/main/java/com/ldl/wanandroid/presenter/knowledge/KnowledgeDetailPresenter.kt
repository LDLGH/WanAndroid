package com.ldl.wanandroid.presenter.knowledge

import com.ldl.wanandroid.base.presenter.BasePresenter
import com.ldl.wanandroid.contract.knowledge.KnowledgeDetailContract
import com.ldl.wanandroid.core.DataManager
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2020/1/16
 * 类说明：
 */
class KnowledgeDetailPresenter @Inject constructor(var dataManager: DataManager) :
    BasePresenter<KnowledgeDetailContract.View>(dataManager), KnowledgeDetailContract.Presenter {
}