package com.sabuzak.yeonamplace.cheerupforyou.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.data.dataBase.SharedPreferenceController
import com.sabuzak.yeonamplace.cheerupforyou.presentation.home.HomeActivity
import com.sabuzak.yeonamplace.cheerupforyou.presentation.makingcheerup.MakingCheerUpTextActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()

        if (SharedPreferenceController.getCheckFirstLogin(this)!!) {
            handler.postDelayed(Runnable { // 5초 뒤에 작동!! -> 5s = 5000ms
                startActivity<MakingCheerUpTextActivity>()
                finish()
            }, 500)
            SharedPreferenceController.setCheckFirstLogin(this, false)
        } else {
            handler.postDelayed(Runnable { // 5초 뒤에 작동!! -> 5s = 5000ms

                startActivity<HomeActivity>()
                finish()
            }, 500)
        }
    }
}
