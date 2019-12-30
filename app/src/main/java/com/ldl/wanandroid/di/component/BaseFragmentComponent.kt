package com.ldl.wanandroid.di.component

import com.ldl.wanandroid.base.fragment.BaseFragment
import com.ldl.wanandroid.base.presenter.AbstractPresenter
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseFragmentComponent : AndroidInjector<BaseFragment<AbstractPresenter>> {

    /**
     * 每一个继承于BaseFragment的Fragment都继承于同一个子组件
     */
    @Subcomponent.Builder
    abstract class BaseBuilder : AndroidInjector.Builder<BaseFragment<AbstractPresenter>>()
}