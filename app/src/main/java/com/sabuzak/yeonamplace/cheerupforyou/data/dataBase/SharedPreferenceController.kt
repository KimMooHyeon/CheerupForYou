package com.sabuzak.yeonamplace.cheerupforyou.data.dataBase

import android.content.Context

object SharedPreferenceController {

    private val prefs = "prefs"

    fun setCheckFirstLogin(context: Context, check: Boolean) {
        val pref = context.getSharedPreferences(prefs, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("prefs", check)
        editor.commit()
    }

    fun getCheckFirstLogin(context: Context): Boolean {
        val pref = context.getSharedPreferences(prefs, Context.MODE_PRIVATE)
        return pref.getBoolean("prefs", true)
    }

}