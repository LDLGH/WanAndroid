package com.ldl.wanandroid.di.component

import com.ldl.wanandroid.app.WanAndroidApp
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        AbstractAllActivityModule::class,
        AbstractAllFragmentModule::class,
        AbstractAllDialogFragmentModule::class,
        AppModule::class,
        HttpModule::class]
)
interface AppComponent {

    /**
     * 注入WanAndroidApp实例
     *
     * @param wanAndroidApp WanAndroidApp
     */
    fun inject(wanAndroidApp: WanAndroidApp)

    /**
     * 提供App的Context
     *
     * @return context
     */
    fun getContext(): WanAndroidApp

    /**
     * 数据中心
     *
     * @return DataManager
     */
    fun getDataManager(): DataManager
}