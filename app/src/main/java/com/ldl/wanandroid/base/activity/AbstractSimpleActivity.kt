package com.ldl.wanandroid.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * 作者：LDL 创建时间：2019/12/24
 * 类说明：
 */
abstract class AbstractSimpleActivity : AppCompatActivity() {

    protected var mActivity: AbstractSimpleActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mActivity = this
        onViewCreated()
        initToolbar()
        initEventAndData()
    }

    /**
     * 在initEventAndData()之前执行
     */
    protected abstract fun onViewCreated()

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化ToolBar
     */
    protected abstract fun initToolbar()

    /**
     * 初始化数据
     */
    protected abstract fun initEventAndData()
}