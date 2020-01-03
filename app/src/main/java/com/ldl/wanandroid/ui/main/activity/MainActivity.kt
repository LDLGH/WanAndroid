package com.ldl.wanandroid.ui.main.activity

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.LogUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_main
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.presenter.main.MainPresenter
import com.ldl.wanandroid.ui.knowledge.fragment.KnowledgeFragment
import com.ldl.wanandroid.ui.main.fragment.HomepageFragment
import com.ldl.wanandroid.ui.navigation.fragment.NavigationFragment
import com.ldl.wanandroid.ui.project.fragment.ProjectFragment
import com.ldl.wanandroid.ui.wx.fragment.WXFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity<MainPresenter>() {

    private val mFragments = arrayListOf<Fragment>()

    override fun getLayoutId(): Int = activity_main

    override fun initToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun initEventAndData() {
        initFragments()
        initDrawerLayout()
        initNavigationView()
    }

    private fun initFragments() {
        mFragments.add(HomepageFragment())
        mFragments.add(KnowledgeFragment())
        mFragments.add(WXFragment())
        mFragments.add(NavigationFragment())
        mFragments.add(ProjectFragment())
        FragmentUtils.add(supportFragmentManager, mFragments, R.id.fl_content, 0)
    }

    private fun initDrawerLayout() {
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        toggle.syncState()
        drawerLayout.addDrawerListener(toggle)
    }

    private fun initNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_main -> {
                    FragmentUtils.showHide(0, mFragments)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_knowledge -> {
                    FragmentUtils.showHide(1, mFragments)
                    LogUtils.d("2222")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_wx -> {
                    FragmentUtils.showHide(2, mFragments)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_navigation -> {
                    FragmentUtils.showHide(3, mFragments)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_project -> {
                    FragmentUtils.showHide(4, mFragments)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    FragmentUtils.showHide(0, mFragments)
                    return@setOnNavigationItemSelectedListener true
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
