package com.sabuzak.yeonamplace.cheerypforyou

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.*
import android.widget.Toast
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
            Toast.makeText(this,"잠금 화면이 설정되었습니다.",Toast.LENGTH_SHORT).show()
            cl_cheerup_all.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            doFullScreen()
            if (iv_cheerup_lock.isSelected==true){
                iv_cheerup_lock.isSelected=false
                iv_cheerup_delete.visibility=View.INVISIBLE
                iv_cheerup_delete.isClickable=false
                doFullScreen()
            }else{
                Toast.makeText(this,"잠금 화면이 해제되었습니다.",Toast.LENGTH_SHORT).show()
                iv_cheerup_lock.isSelected=true
                iv_cheerup_delete.isClickable=true
                iv_cheerup_delete.visibility=View.VISIBLE

            }

        }

        tv_cheer_up_view_Text.setText(intent.getStringExtra("edt_making_text"))

        //효과 받기

        if(intent.getIntExtra("effect1",0)==1) {
            //있는거
            tv_cheer_up_view_Text.setStroke(true)
            tv_cheer_up_view_Text.draw(Canvas())
        }

        if(intent.getIntExtra("effect2",0)==1) {
            tv_cheer_up_view_Text.setShadowLayer(10.0f,0.0f,0.0f,Color.parseColor("#ffffff"))
        }
        if(intent.getIntExtra("effect3",0)==1) {
            tv_cheer_up_view_Text.setShadowLayer(2.0f,6.0f,3.0f,Color.parseColor("#2AEFF5"))
        }


        // 폰트 받기
        if(intent.getIntExtra("font",0)==0){
            tv_cheer_up_view_Text.setTypeface(Typeface.createFromAsset(getAssets(), "font/nanum.ttf"))
        }else if (intent.getIntExtra("font",0)==1){
            tv_cheer_up_view_Text.setTypeface(Typeface.createFromAsset(getAssets(), "font/hansuwon.ttf"))
        }else if (intent.getIntExtra("font",0)==2){
            tv_cheer_up_view_Text.setTypeface(Typeface.createFromAsset(getAssets(), "font/uljiro.ttf"))
        }else if (intent.getIntExtra("font",0)==3){
            tv_cheer_up_view_Text.setTypeface(Typeface.createFromAsset(getAssets(), "font/hanna.ttf"))
        }else if (intent.getIntExtra("font",0)==4){
            tv_cheer_up_view_Text.setTypeface(Typeface.createFromAsset(getAssets(), "font/yanolza.ttf"))
        }else if (intent.getIntExtra("font",0)==5){
            tv_cheer_up_view_Text.setTypeface(Typeface.createFromAsset(getAssets(), "font/jua.ttf"))
        }else if (intent.getIntExtra("font",0)==6){
            tv_cheer_up_view_Text.setTypeface(Typeface.createFromAsset(getAssets(), "font/tvn.ttf"))
        }


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
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 50.0f)
        } else if (intent.getIntExtra("text_size",0) == 1){
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 70.0f)
        } else if (intent.getIntExtra("text_size",0) ==2){
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 130.0f)
        } else if (intent.getIntExtra("text_size",0) ==3){
            tv_cheer_up_view_Text.setTextSize(Dimension.SP, 200.0f)
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

        var animation = TranslateAnimation((screenWidth).toFloat(), (-(tv_cheer_up_view_Text.width).toFloat()), 0.0f, -0.0f)
        if (intent.getIntExtra("direction",0)==2){
            animation = TranslateAnimation((screenWidth).toFloat(), (-(tv_cheer_up_view_Text.width).toFloat()), 0.0f, -0.0f)
        }else if (intent.getIntExtra("direction",0)==0){
            animation = TranslateAnimation((-(tv_cheer_up_view_Text.width).toFloat()), (screenWidth).toFloat(), 0.0f, -0.0f)
        }else if (intent.getIntExtra("direction",0)==1){
            animation = TranslateAnimation(0.0f, 0.0f, 0.0f, -0.0f)
        }


        animation.setInterpolator( AnimationUtils.loadInterpolator( this, android.R.anim.linear_interpolator) )
        if(intent.getIntExtra("speed",0)==0){

            animation.duration = (intent.getStringExtra("edt_making_text")!!.length*800).toLong()
        }else if (intent.getIntExtra("speed",0) ==1) {
            animation.duration = (intent.getStringExtra("edt_making_text")!!.length*1000).toLong()
        }else if (intent.getIntExtra("speed",0) ==2){
            animation.duration = (intent.getStringExtra("edt_making_text")!!.length*2000).toLong()
        }


        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })
        animation.repeatCount= -1

        if (intent.getIntExtra("effect0",0) == 1){
            val alphaAnim = AlphaAnimation(0F, 1.0F)
            alphaAnim.duration = 200
            alphaAnim.repeatCount = -1
            set.addAnimation(alphaAnim)
        }

        set.addAnimation(animation)


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


