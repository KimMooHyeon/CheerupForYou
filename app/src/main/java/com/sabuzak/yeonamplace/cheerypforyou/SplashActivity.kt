package com.sabuzak.yeonamplace.cheerypforyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(Runnable { // 5초 뒤에 작동!! -> 5s = 5000ms
            startActivity<MakingCheerUpTextActivity>()
            finish()
        }, 500)

    }
}
