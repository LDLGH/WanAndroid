package com.ldl.wanandroid.di.module

import com.ldl.wanandroid.app.WanAndroidApp
import com.ldl.wanandroid.core.DataManager
import com.ldl.wanandroid.core.db.DbHelper
import com.ldl.wanandroid.core.db.DbHelperImpl
import com.ldl.wanandroid.core.http.HttpHelper
import com.ldl.wanandroid.core.http.HttpHelperImpl
import com.ldl.wanandroid.core.prefs.PreferenceHelper
import com.ldl.wanandroid.core.prefs.PreferenceHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 作者：LDL 创建时间：2019/12/26
 * 类说明：
 */
@Module
class AppModule(private val application: WanAndroidApp) {

    @Provides
    @Singleton
    fun provideApplicationContext(): WanAndroidApp = application

    @Provides
    @Singleton
    fun provideHttpHelper(httpHelperImpl: HttpHelperImpl): HttpHelper = httpHelperImpl

    @Provides
    @Singleton
    fun provideDbHelper(dbHelperImpl: DbHelperImpl): DbHelper = dbHelperImpl

    @Provides
    @Singleton
    fun providePreferenceHelper(preferenceHelperImpl: PreferenceHelperImpl): PreferenceHelper =
        preferenceHelperImpl

    @Provides
    @Singleton
    fun provideDataManager(
        httpHelper: HttpHelper,
        dbHelper: DbHelper,
        preferenceHelper: PreferenceHelper
    ) = DataManager(httpHelper, dbHelper, preferenceHelper)
}