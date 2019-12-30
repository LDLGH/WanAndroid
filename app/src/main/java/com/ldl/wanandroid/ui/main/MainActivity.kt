package com.ldl.wanandroid.ui.main

import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.ldl.wanandroid.R
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.presenter.main.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initToolbar() {
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun initEventAndData() {
        mPresenter?.getFeedArticleList(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
