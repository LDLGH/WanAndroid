package com.ldl.wanandroid.app

import android.app.Application
import com.billy.android.loading.Gloading
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.facebook.stetho.Stetho
import com.ldl.wanandroid.BuildConfig
import com.ldl.wanandroid.core.dao.DaoMaster
import com.ldl.wanandroid.core.dao.DaoSession
import com.ldl.wanandroid.di.component.AppComponent
import com.ldl.wanandroid.di.component.DaggerAppComponent
import com.ldl.wanandroid.di.module.AppModule
import com.ldl.wanandroid.di.module.HttpModule
import com.ldl.wanandroid.ui.main.adapter.GlobalAdapter
import com.tencent.bugly.crashreport.CrashReport
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * 作者：LDL 创建时间：2019/12/24
 * 类说明：
 */
class WanAndroidApp : Application(), HasAndroidInjector {

    @set:Inject
    var mAndroidInjector: DispatchingAndroidInjector<Any>? = null

    private var appComponent: AppComponent? = null

    private var mDaoSession: DaoSession? = null

    companion object {
        var getInstance: WanAndroidApp? = null
    }

    override fun onCreate() {
        super.onCreate()

        getInstance = this

        CrashReport.initCrashReport(this, Constants.BUGLY_ID, BuildConfig.DEBUG)

        Utils.init(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }

        initGreenDao()

        initAppComponent()

        Gloading.initDefault(GlobalAdapter())
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }


    override fun androidInjector(): AndroidInjector<Any> = mAndroidInjector!!

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .httpModule(HttpModule())
            .build()
        appComponent?.inject(this)
    }

    private fun initGreenDao() {
        val devOpenHelper = DaoMaster.DevOpenHelper(this, Constants.DB_NAME)
        val database = devOpenHelper.writableDatabase
        val daoMaster = DaoMaster(database)
        mDaoSession = daoMaster.newSession()
    }

    fun getDaoSession(): DaoSession = mDaoSession!!
}