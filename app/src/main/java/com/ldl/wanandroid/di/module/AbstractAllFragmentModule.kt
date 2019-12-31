package com.ldl.wanandroid.di.module

import com.ldl.wanandroid.di.component.BaseFragmentComponent
import com.ldl.wanandroid.ui.knowledge.fragment.KnowledgeFragment
import com.ldl.wanandroid.ui.main.activity.MainActivity
import com.ldl.wanandroid.ui.main.fragment.HomepageFragment
import com.ldl.wanandroid.ui.navigation.fragment.NavigationFragment
import com.ldl.wanandroid.ui.project.fragment.ProjectFragment
import com.ldl.wanandroid.ui.wx.fragment.WXFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
@Module(subcomponents = [BaseFragmentComponent::class])
abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = [HomepageFragmentModule::class])
    abstract fun contributesHomepageFragmentInjector(): HomepageFragment

    @ContributesAndroidInjector(modules = [KnowledgeFragmentModule::class])
    abstract fun contributesKnowledgeFragmentInjector(): KnowledgeFragment

    @ContributesAndroidInjector(modules = [WXFragmentModule::class])
    abstract fun contributesWXFragmentInjector(): WXFragment

    @ContributesAndroidInjector(modules = [NavigationFragmentModule::class])
    abstract fun contributesNavigationFragmentInjector(): NavigationFragment

    @ContributesAndroidInjector(modules = [ProjectFragmentModule::class])
    abstract fun contributesProjectFragmentInjector(): ProjectFragment
}