package com.ldl.wanandroid.app

import android.app.Application
import android.content.Context
import androidx.core.content.ContextCompat
import com.billy.android.loading.Gloading
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.facebook.stetho.Stetho
import com.ldl.wanandroid.BuildConfig
import com.ldl.wanandroid.R
import com.ldl.wanandroid.core.dao.DaoMaster
import com.ldl.wanandroid.core.dao.DaoSession
import com.ldl.wanandroid.di.component.AppComponent
import com.ldl.wanandroid.di.component.DaggerAppComponent
import com.ldl.wanandroid.di.module.AppModule
import com.ldl.wanandroid.di.module.HttpModule
import com.ldl.wanandroid.ui.main.adapter.GlobalAdapter
import com.scwang.smartrefresh.header.DeliveryHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
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

    private val instance: WanAndroidApp? = null

    @set:Inject
    var mAndroidInjector: DispatchingAndroidInjector<Any>? = null

    @Volatile
    private var appComponent: AppComponent? = null

    private var mDaoSession: DaoSession? = null

    //static 代码段可以防止内存泄露, 全局设置刷新头部及尾部，优先级最低
    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context: Context?, layout: RefreshLayout ->
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)
            DeliveryHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context: Context?, _: RefreshLayout? ->
            BallPulseFooter(
                context
            ).setAnimatingColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
        }
    }

    @Synchronized
    fun getInstance(): WanAndroidApp? {
        return instance
    }

    override fun onCreate() {
        super.onCreate()

        CrashReport.initCrashReport(this, Constants.BUGLY_ID, BuildConfig.DEBUG)

        Utils.init(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
        initAppComponent()
        initGreenDao()

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

    @Synchronized
    fun getAppComponent(): AppComponent? {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .httpModule(HttpModule())
                .build()
        }
        return appComponent
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