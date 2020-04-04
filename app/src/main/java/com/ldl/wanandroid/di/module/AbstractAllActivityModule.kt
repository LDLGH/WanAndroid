package com.ldl.wanandroid.di.module

import com.ldl.wanandroid.di.component.BaseActivityComponent
import com.ldl.wanandroid.ui.knowledge.activity.KnowledgeActivity
import com.ldl.wanandroid.ui.knowledge.activity.KnowledgeDetailListActivity
import com.ldl.wanandroid.ui.main.activity.*
import com.ldl.wanandroid.ui.navigation.activity.NavigationActivity
import com.ldl.wanandroid.ui.project.activity.ProjectActivity
import com.ldl.wanandroid.ui.wx.activity.WXListActivity
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

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun contributesSplashActivityInjector(): SplashActivity

    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    abstract fun contributesSearchActivityInjector(): SearchActivity

    @ContributesAndroidInjector(modules = [LoginAndRegisterActivityModule::class])
    abstract fun contributesLoginAndRegisterActivityInjector(): LoginAndRegisterActivity

    @ContributesAndroidInjector(modules = [WebViewActivityModule::class])
    abstract fun contributesWebViewActivityInjector(): WebViewActivity

    @ContributesAndroidInjector(modules = [ArticleActivityModule::class])
    abstract fun contributesArticleActivityInjector(): ArticleActivity

    @ContributesAndroidInjector(modules = [NavigationActivityModule::class])
    abstract fun contributesNavigationActivityInjector(): NavigationActivity

    @ContributesAndroidInjector(modules = [KnowledgeActivityModule::class])
    abstract fun contributesKnowledgeActivityInjector(): KnowledgeActivity

    @ContributesAndroidInjector(modules = [KnowledgeDetailActivityModule::class])
    abstract fun contributesKnowledgeDetailActivityInjector(): KnowledgeDetailListActivity

    @ContributesAndroidInjector(modules = [WXListActivityModule::class])
    abstract fun contributesWXListActivityInjector(): WXListActivity

    @ContributesAndroidInjector(modules = [ProjectActivityModule::class])
    abstract fun contributesProjectActivityInjector(): ProjectActivity

    @ContributesAndroidInjector(modules = [SearchListActivityModule::class])
    abstract fun contributesSearchListActivityInjector(): SearchListActivity
}