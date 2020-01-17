package com.ldl.wanandroid.ui.main.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ColorUtils
import com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
import com.just.agentweb.AgentWeb
import com.just.agentweb.NestedScrollAgentWebView
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_webview
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.contract.main.WebViewContract
import com.ldl.wanandroid.presenter.main.WebViewPresenter
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.common_toolbar.*


/**
 * 作者：LDL 创建时间：2020/1/14
 * 类说明：
 */
class WebViewActivity : BaseActivity<WebViewPresenter>(), WebViewContract.View {

    companion object {
        private const val TITLE = "title"
        private const val URL = "url"

        fun start(title: String, url: String) {
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            bundle.putString(URL, url)
            ActivityUtils.startActivity(bundle, WebViewActivity::class.java)
        }
    }

    private var mAgentWeb: AgentWeb? = null

    private var toolbarTitle = ""
    private var url = ""

    override fun getLayoutId(): Int = activity_webview

    override fun initToolbar() {
        getBundleData()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = toolbarTitle
    }

    override fun initEventAndData() {
        initWebView()
    }

    private fun initWebView() {
        val webView = NestedScrollAgentWebView(this)
        val lp = CoordinatorLayout.LayoutParams(-1, -1)
        lp.behavior = ScrollingViewBehavior()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(coordinator_main, 1, lp)
            .useDefaultIndicator(ColorUtils.getColor(R.color.colorAccent))
            .setWebView(webView)
            .createAgentWeb()
            .ready()
            .go(url)
    }

    override fun getBundleData() {
        intent.extras?.apply {
            toolbarTitle = getString(TITLE, "")
            url = getString(URL, "")
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_webview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_clear -> {
                ActivityUtils.finishActivity(this, true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if(!mAgentWeb!!.back()){
            super.onBackPressed()
        }
    }

    override fun onPause() {
        mAgentWeb?.webLifeCycle?.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb?.webLifeCycle?.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb?.webLifeCycle?.onDestroy()
        super.onDestroy()
    }
}