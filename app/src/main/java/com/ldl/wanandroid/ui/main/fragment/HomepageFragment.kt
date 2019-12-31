package com.ldl.wanandroid.ui.main.fragment

import com.ldl.wanandroid.R.layout.fragment_homepage
import com.ldl.wanandroid.base.fragment.BaseRootFragment
import com.ldl.wanandroid.contract.main.HomepageContract
import com.ldl.wanandroid.core.bean.collect.FeedArticleListData
import com.ldl.wanandroid.presenter.main.HomepagePresenter

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class HomepageFragment : BaseRootFragment<HomepagePresenter>(), HomepageContract.View {

    override fun getLayoutId(): Int = fragment_homepage

    override fun initEventAndData() {
        mPresenter?.getFeedArticleList(false)
    }

    override fun showArticleList(feedArticleListData: FeedArticleListData, isRefresh: Boolean) {

    }
}