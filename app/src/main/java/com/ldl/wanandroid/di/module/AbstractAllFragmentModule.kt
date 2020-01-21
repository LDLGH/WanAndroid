package com.ldl.wanandroid.di.module

import com.ldl.wanandroid.di.component.BaseFragmentComponent
import com.ldl.wanandroid.ui.knowledge.fragment.KnowledgeDetailPagerFragment
import com.ldl.wanandroid.ui.main.fragment.HomepageFragment
import com.ldl.wanandroid.ui.project.fragment.ProjectListFragment
import com.ldl.wanandroid.ui.wx.fragment.WXListPagerFragment
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

    @ContributesAndroidInjector(modules = [KnowledgeDetailPagerFragmentModule::class])
    abstract fun contributesKnowledgeDetailPagerFragmentInjector(): KnowledgeDetailPagerFragment

    @ContributesAndroidInjector(modules = [WXListPagerFragmentModule::class])
    abstract fun contributesWXListPagerFragmentInjector(): WXListPagerFragment

    @ContributesAndroidInjector(modules = [ProjectListFragmentModule::class])
    abstract fun contributesProjectListFragmentInjector(): ProjectListFragment

}