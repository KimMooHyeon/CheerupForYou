package com.sabuzak.yeonamplace.cheerupforyou.DataBase

import android.content.Context

object SharedPreferenceController {

    private val  prefs = "prefs"

    // 토큰
    fun setCheckFirestLogin(context: Context, check: Boolean) {
        val pref = context.getSharedPreferences(prefs, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("prefs", check)
        editor.commit()
    }

    fun getCheckFirestLogin(context: Context): Boolean? {
        val pref = context.getSharedPreferences(prefs, Context.MODE_PRIVATE)
        return pref.getBoolean("prefs", true)
    }

}