package com.ldl.wanandroid.ui.main.activity

import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ObjectUtils
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.ldl.wanandroid.R
import com.ldl.wanandroid.R.layout.activity_login_register
import com.ldl.wanandroid.base.activity.BaseActivity
import com.ldl.wanandroid.contract.main.LoginAndRegisterContract
import com.ldl.wanandroid.presenter.main.LoginAndRegisterPresenter
import kotlinx.android.synthetic.main.activity_login_register.*
import kotlinx.android.synthetic.main.activity_search.toolbar
import java.util.concurrent.TimeUnit

/**
 * 作者：LDL 创建时间：2020/1/9
 * 类说明：
 */
class LoginAndRegisterActivity : BaseActivity<LoginAndRegisterPresenter>(),
    LoginAndRegisterContract.View {

    private var isLogin = true

    override fun getLayoutId(): Int = activity_login_register

    override fun initToolbar() {
        BarUtils.transparentStatusBar(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onViewCreated() {
        super.onViewCreated()
        mPresenter?.addRxBindingSubscribe(btn_login.clicks()
            .subscribe {
                isLogin = true
                onViewData()
            })

        mPresenter?.addRxBindingSubscribe(tv_create_account.clicks()
            .subscribe {
                isLogin = false
                onViewData()
            })
        mPresenter?.addRxBindingSubscribe(btn_submit.clicks()
            .throttleFirst(2, TimeUnit.SECONDS)
            .subscribe {
                val username = et_user.text.toString()
                val password = et_password.text.toString()
                if (ObjectUtils.isEmpty(et_user.text.toString())) {
                    textInputLayoutUser.error = getString(R.string.input_username)
                    return@subscribe
                }
                if (et_password.text.toString().length < 6) {
                    textInputLayoutPassword.error = getString(R.string.password_limit)
                    return@subscribe
                }
                KeyboardUtils.hideSoftInput(this)
                if (isLogin) {
                    mPresenter?.login(username, password)
                } else {
                    mPresenter?.registerAndLogin(username, password)
                }
            })

        mPresenter?.addRxBindingSubscribe(et_user.textChanges()
            .subscribe {
                textInputLayoutUser.isErrorEnabled = false
            })

        mPresenter?.addRxBindingSubscribe(et_password.textChanges()
            .subscribe {
                textInputLayoutPassword.isErrorEnabled = false
            })
    }

    override fun initEventAndData() {

    }

    private fun onViewData() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        cl_head.visibility = View.VISIBLE
        cl_content.visibility = View.GONE
        et_user.requestFocus()
        KeyboardUtils.showSoftInput(this)

        tv_title.text = getString(if (isLogin) R.string.login else R.string.register_account)
        btn_submit.text = getString(if (isLogin) R.string.login else R.string.create_account)
    }

    override fun onBackPressed() {
        KeyboardUtils.hideSoftInput(this)
        if (cl_head.visibility == View.VISIBLE) {
            cl_head.visibility = View.GONE
            cl_content.visibility = View.VISIBLE
            toolbar.setNavigationIcon(R.drawable.ic_clear_white_24dp)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else super.onOptionsItemSelected(item!!)
    }

    override fun onLogin() {
        showToast(getString(R.string.login_success))
        finish()
    }

}