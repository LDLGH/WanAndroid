package com.ldl.wanandroid.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import com.blankj.utilcode.util.StringUtils
import com.ldl.wanandroid.R
import com.ldl.wanandroid.ui.main.activity.MainActivity
import com.ldl.wanandroid.ui.main.activity.SearchActivity

/**
 * 作者：LDL 创建时间：2020/1/7
 * 类说明：
 */
object ShortcutUtils {

    @TargetApi(Build.VERSION_CODES.N_MR1)
    fun register(context: Context) {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)
        val infos = ArrayList<ShortcutInfo>()

        val intentBack = Intent(context, MainActivity::class.java)
        intentBack.action = "com.ldl.wanandroid.BACK"

        val intent = Intent(context, SearchActivity::class.java)
        intent.action = Intent.ACTION_VIEW

        val intents = arrayOf(intentBack, intent)
        val info = ShortcutInfo.Builder(context, "publish-2")
            .setShortLabel(StringUtils.getString(R.string.search))
            .setLongLabel(StringUtils.getString(R.string.search))
            .setIcon(Icon.createWithResource(context, R.drawable.ic_search_blue_24dp))
            .setIntents(intents)
            .build()
        infos.add(info)
        shortcutManager.dynamicShortcuts = infos
    }
}