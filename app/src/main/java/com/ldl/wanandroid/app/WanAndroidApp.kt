package com.ldl.wanandroid.app

import android.app.Application
import com.billy.android.loading.Gloading
import com.bumptech.glide.Glide
import com.ldl.wanandroid.app.task.InitBuglyTask
import com.ldl.wanandroid.app.task.InitStethoTask
import com.ldl.wanandroid.app.task.InitUtilsTask
import com.ldl.wanandroid.core.dao.DaoMaster
import com.ldl.wanandroid.core.dao.DaoSession
import com.ldl.wanandroid.di.component.AppComponent
import com.ldl.wanandroid.di.component.DaggerAppComponent
import com.ldl.wanandroid.di.module.AppModule
import com.ldl.wanandroid.di.module.HttpModule
import com.ldl.wanandroid.ui.main.adapter.GlobalAdapter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.jay.launchstarter.TaskDispatcher
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

    override fun onCreate() {
        super.onCreate()

        getInstance = this

        //异步初始化工具
        TaskDispatcher.init(this)
        val taskDispatcher = TaskDispatcher.createInstance()
        taskDispatcher.addTask(InitBuglyTask())
            .addTask(InitStethoTask())
            .addTask(InitUtilsTask())
            .start()
        taskDispatcher.await()

        initGreenDao()
        initAppComponent()
        initGloading()
    }

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

    private fun initGloading() {
        Gloading.initDefault(GlobalAdapter())
    }

    fun getDaoSession(): DaoSession = mDaoSession!!


    override fun androidInjector(): AndroidInjector<Any> = mAndroidInjector!!
}