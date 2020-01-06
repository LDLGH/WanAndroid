package com.ldl.wanandroid.ui.main.activity

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.FragmentUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_main
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.presenter.main.MainPresenter
import com.ldl.wanandroid.ui.main.fragment.HomepageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity<MainPresenter>() {

    private val mFragments = arrayListOf<Fragment>()

    override fun getLayoutId(): Int = activity_main

    override fun initToolbar() {
        BarUtils.transparentStatusBar(this)
        setSupportActionBar(toolbar)
    }

    override fun initEventAndData() {
        initFragments()
        initDrawerLayout()
    }

    private fun initFragments() {
        mFragments.add(HomepageFragment())
        FragmentUtils.add(supportFragmentManager, mFragments, R.id.fl_content, 0)
    }

    private fun initDrawerLayout() {
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.syncState()
        drawerLayout.addDrawerListener(toggle)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
