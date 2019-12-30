package com.ldl.wanandroid.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 * 作者：LDL 创建时间：2019/12/25
 * 类说明：
 */
abstract class AbstractSimpleDialogFragment : DialogFragment() {

    var mRootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mRootView = inflater.inflate(getLayout(), container, false)
        initEventAndData()
        return mRootView
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try { //防止连续点击add多个fragment
            manager.beginTransaction().remove(this).commit()
            super.show(manager, tag)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract fun getLayout(): Int

    /**
     * 初始化数据
     */
    protected abstract fun initEventAndData()
}