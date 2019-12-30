package com.ldl.wanandroid.di.module

import com.ldl.wanandroid.di.component.BaseActivityComponent
import com.ldl.wanandroid.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
@Module(subcomponents = [BaseActivityComponent::class])
abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivityInjector(): MainActivity
}