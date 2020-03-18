package com.ldl.wanandroid.ui.main.activity

import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.FragmentUtils
import com.jakewharton.rxbinding3.view.clicks
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_main
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.contract.main.MainContract
import com.ldl.wanandroid.presenter.main.MainPresenter
import com.ldl.wanandroid.ui.main.fragment.HomepageFragment
import com.ldl.wanandroid.utils.GlideApp
import com.ldl.wanandroid.utils.ShortcutUtils
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity<MainPresenter>(), MainContract.View {

    private val mFragments = arrayListOf<Fragment>()

    override fun getLayoutId(): Int = activity_main

    private lateinit var mTvUsername: TextView

    override fun initToolbar() {
        BarUtils.transparentStatusBar(this)
        setSupportActionBar(toolbar)
    }

    override fun initEventAndData() {
        ShortcutUtils.register(this)
        initFragments()
        initDrawerLayout()
        initNavigationView()
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

    private fun initNavigationView() {
        val headerView = navigation.getHeaderView(0)
        val ivCover = headerView.findViewById<ImageView>(R.id.iv_cover)
        mTvUsername = headerView.findViewById(R.id.tv_username)

        mTvUsername.text =
            if (mPresenter!!.getLoginStatus()) mPresenter?.getLoginAccount()
            else getString(R.string.login_or_register)

        mPresenter?.addRxBindingSubscribe(ivCover.clicks().subscribe {
            if (!mPresenter!!.getLoginStatus()) {
                ActivityUtils.startActivity(LoginAndRegisterActivity::class.java)
            }
        })

        GlideApp.with(this)
            .load(R.drawable.bg_login)
            .transform(BlurTransformation(6))
            .into(ivCover)

        navigation.menu.findItem(R.id.nav_login)
            .setOnMenuItemClickListener {
                if (mPresenter!!.getLoginStatus()) {
                    showLogoutDialog()
                }
                return@setOnMenuItemClickListener true
            }
        navigation.menu.findItem(R.id.nav_login).isVisible = mPresenter!!.getLoginStatus()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                ActivityUtils.startActivity(SearchActivity::class.java)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.tips))
            .setMessage(getString(R.string.confirm_logout))
            .setPositiveButton(
                getString(R.string.yes)
            ) { _, _ ->
                mPresenter?.logout()
            }
            .setNegativeButton(
                getString(R.string.no)
            ) { _, _ ->

            }
            .show()
    }

    override fun onLogout() {
        mTvUsername.text =
            if (mPresenter!!.getLoginStatus()) mPresenter?.getLoginAccount()
            else getString(R.string.login_or_register)
        navigation.menu.findItem(R.id.nav_login).isVisible = mPresenter!!.getLoginStatus()
    }

    override fun onLoginEvent(msg: String) {
        onLogout()
    }
}
