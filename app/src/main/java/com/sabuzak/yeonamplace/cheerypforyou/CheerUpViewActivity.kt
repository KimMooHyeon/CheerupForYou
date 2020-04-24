package com.sabuzak.yeonamplace.cheerypforyou

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cheer_up_view.*
import org.jetbrains.anko.toast

class CheerUpViewActivity : AppCompatActivity() {
    private var screenWidth = 0f
    private var screenHeight = 0f
    private var fromX = 0f
    private var fromY = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_cheer_up_view)
        tv_cheer_up_view_Text.setText(intent.getStringExtra("edt_making_text"))
        tv_cheer_up_view_Text.setTextColor(Color.MAGENTA)
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        screenWidth = point.x.toFloat()
        screenHeight = point.y.toFloat()

        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = uiOptions


        //애니메이션 부분
        fromY = screenHeight
        fromX = screenWidth


        //애니메이션 두개 넣기 set
        var set = AnimationSet(false)


        val animation = TranslateAnimation((screenWidth).toFloat(), (-(tv_cheer_up_view_Text.width).toFloat()), 0.0f, -0.0f)

        animation.setInterpolator( AnimationUtils.loadInterpolator( this, android.R.anim.linear_interpolator) )

        toast(animation.interpolator.toString())

        animation.duration = (intent.getStringExtra("edt_making_text").length*1000).toLong()

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        animation.repeatCount= 10


        val alphaAnim = AlphaAnimation(0F, 1.0F)


        alphaAnim.duration = 200
        alphaAnim.repeatCount = -1

        set.addAnimation(animation)
        set.addAnimation(alphaAnim)

        tv_cheer_up_view_Text.animation=set
        tv_cheer_up_view_Text.animation.start()

        super.onWindowFocusChanged(hasFocus)


    }

}


