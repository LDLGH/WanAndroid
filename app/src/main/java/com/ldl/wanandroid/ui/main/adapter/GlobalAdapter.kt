package com.ldl.wanandroid.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.billy.android.loading.Gloading.*
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.layout_global_loading_status
import kotlinx.android.synthetic.main.layout_global_loading_status.view.*


/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class GlobalAdapter : Adapter {

    override fun getView(holder: Holder?, convertView: View?, status: Int): View {

        var view: GlobalLoadingStatusView? = null
        if (convertView != null || convertView is GlobalLoadingStatusView) {
            view = convertView as GlobalLoadingStatusView
        }
        if (view == null) {
            view = GlobalLoadingStatusView(holder!!.context, holder.retryTask)
        }
        view.setStatus(status)
        view.start()
        return view
    }
}

@SuppressLint("ViewConstructor")
class GlobalLoadingStatusView constructor(context: Context, retryTask: Runnable) :
    RelativeLayout(context) {

    private var mRetryTask: Runnable? = null

    init {
        LayoutInflater.from(context).inflate(layout_global_loading_status, this)
        mRetryTask = retryTask
    }

    fun start() {
        animation_loading.playAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animation_loading.cancelAnimation()
    }

    fun setStatus(status: Int) {
        var show = true
        when (status) {
            STATUS_LOAD_SUCCESS -> {
                show = false
            }
            STATUS_LOADING -> {
            }
            STATUS_LOAD_FAILED -> {
                animation_loading.setAnimation(R.raw.network_error)
                animation_loading.setOnClickListener {
                    mRetryTask?.run()
                }
            }
            else -> {
            }
        }
        visibility = if (show) View.VISIBLE else View.GONE
    }

}

