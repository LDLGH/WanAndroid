package com.ldl.wanandroid.ui.main.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.billy.android.loading.Gloading
import com.billy.android.loading.Gloading.STATUS_LOADING
import com.billy.android.loading.Gloading.STATUS_LOAD_FAILED
import com.ldl.wanandroid.R.layout.layout_global_error_status
import com.ldl.wanandroid.R.layout.layout_global_loading_status
import kotlinx.android.synthetic.main.layout_global_error_status.view.*
import kotlinx.android.synthetic.main.layout_global_loading_status.view.*

/**
 * 作者：LDL 创建时间：2019/12/31
 * 类说明：
 */
class GlobalAdapter : Gloading.Adapter {

    override fun getView(holder: Gloading.Holder?, convertView: View?, status: Int): View {
        if (status == STATUS_LOADING) {
            val view: GlobalLoadingStatusView
            if (convertView == null || convertView !is GlobalLoadingStatusView) {
                view = GlobalLoadingStatusView(holder!!.context)
                convertView?.tag = view
            } else {
                view = convertView.tag as GlobalLoadingStatusView
            }
            view.start()
            return view
        } else if (status == STATUS_LOAD_FAILED) {
            val view: GlobalErrorStatusView
            if (convertView == null || convertView !is GlobalErrorStatusView) {
                view = GlobalErrorStatusView(holder!!.context, holder.retryTask)
                convertView?.tag = view
            } else {
                view = convertView.tag as GlobalErrorStatusView
            }
            view.start()
            return view
        }
        return convertView!!.tag as View
    }
}

class GlobalLoadingStatusView constructor(context: Context) : RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(layout_global_loading_status, this)
    }

    fun start() {
        animation_loading.playAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animation_loading.cancelAnimation()
    }
}

@SuppressLint("ViewConstructor")
class GlobalErrorStatusView constructor(context: Context, retryTask: Runnable) :
    RelativeLayout(context) {

    init {
        LayoutInflater.from(context).inflate(layout_global_error_status, this)
        animation_error.setOnClickListener {
            retryTask.run()
        }
    }

    fun start() {
        animation_error.playAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animation_error.cancelAnimation()
    }
}

