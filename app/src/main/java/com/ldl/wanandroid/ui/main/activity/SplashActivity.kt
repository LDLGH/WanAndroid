package com.ldl.wanandroid.ui.main.activity

import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_splash
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.contract.main.SplashContract
import com.ldl.wanandroid.presenter.main.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * 作者：LDL 创建时间：2019/12/30
 * 类说明：
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashContract.View {

    override fun getLayoutId(): Int = activity_splash

    override fun initToolbar() {
        BarUtils.transparentStatusBar(this)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        view_lottie.setAnimation(R.raw.sheep_play_computer)
        view_lottie.playAnimation()
        tv_skip.start()
        tv_skip.setOnClickListener {
            jumpToMain()
        }
    }

    override fun initEventAndData() {

    }

    override fun jumpToMain() {
        ActivityUtils.startActivity(MainActivity::class.java)
        finish()
    }

    override fun onBackPressed() {
//        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        view_lottie.cancelAnimation()
    }

}