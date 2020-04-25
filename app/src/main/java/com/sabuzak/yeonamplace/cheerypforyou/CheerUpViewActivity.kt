package com.sabuzak.yeonamplace.cheerypforyou

import android.graphics.Color
import android.graphics.Point
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.*
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cheer_up_view.*

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

        iv_cheerup_delete.setOnClickListener {
            finish()
        }
        iv_cheerup_lock.isSelected=true
        hs_cheerup_view.isClickable=false
        iv_cheerup_lock.setOnClickListener {
            doFullScreen()
            if (iv_cheerup_lock.isSelected==true){
                iv_cheerup_lock.isSelected=false
                iv_cheerup_delete.visibility=View.INVISIBLE
                iv_cheerup_delete.isClickable=false
                doFullScreen()
            }else{
                iv_cheerup_lock.isSelected=true
                iv_cheerup_delete.isClickable=true
                iv_cheerup_delete.visibility=View.VISIBLE

            }

        }

        tv_cheer_up_view_Text.setText(intent.getStringExtra("edt_making_text"))

        // 글자 색 받기
        if(intent.getIntExtra("text_color",0) ==0 ) {
            tv_cheer_up_view_Text.setTextColor(Color.parseColor("#ffffff"))
        }else if(intent.getIntExtra("text_color",0) ==1 ) {
            tv_cheer_up_view_Text.setTextColor(Color.parseColor("#3c3eda"))
        }else if(intent.getIntExtra("text_color",0) ==2 ) {
            tv_cheer_up_view_Text.setTextColor(Color.parseColor("#0191b6"))
        }else if(intent.getIntExtra("text_color",0) ==3 ) {
            tv_cheer_up_view_Text.setTextColor(Color.parseColor("#f9d80d"))
        }else if(intent.getIntExtra("text_color",0) ==4 ) {
            tv_cheer_up_view_Text.setTextColor(Color.parseColor( "#ff7b17"))
        }else if(intent.getIntExtra("text_color",0) ==5 ) {
            tv_cheer_up_view_Text.setTextColor(Color.parseColor("#f637f3"))
        }

        // 백그라운드 색 변경

        if (intent.getIntExtra("background_color",0) ==0){
            hs_cheerup_view.setBackgroundColor(Color.parseColor("#000000"))
        }else if (intent.getIntExtra("background_color",0) ==1){
            hs_cheerup_view.setBackgroundColor(Color.parseColor("#3c3eda"))
        }else if (intent.getIntExtra("background_color",0) ==2){
            hs_cheerup_view.setBackgroundColor(Color.parseColor("#0191b6"))
        }else if (intent.getIntExtra("background_color",0) ==3){
            hs_cheerup_view.setBackgroundColor(Color.parseColor("#f9d80d"))
        }else if (intent.getIntExtra("background_color",0) ==4){
            hs_cheerup_view.setBackgroundColor(Color.parseColor("#ff7b17"))
        }else if (intent.getIntExtra("background_color",0) ==5){
            hs_cheerup_view.setBackgroundColor(Color.parseColor("#f637f3"))
        }

        //글자 크기 변경
        if (intent.getIntExtra("text_size",0) ==0){
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 50.0f);
        } else if (intent.getIntExtra("text_size",0) == 1){
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 70.0f);
        } else if (intent.getIntExtra("text_size",0) ==2){
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 130.0f);
        } else if (intent.getIntExtra("text_size",0) ==3){
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 200.0f);
        }



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
    private fun doFullScreen() {
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN
    }

}


