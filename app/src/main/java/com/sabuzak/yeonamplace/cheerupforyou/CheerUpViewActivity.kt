package com.sabuzak.yeonamplace.cheerupforyou

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import org.jetbrains.anko.toast

class CheerUpViewActivity : AppCompatActivity() {
    private var screenWidth = 0f
    private var fromX = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_cheer_up_view)
        var iv_cheerup_delete = findViewById<ImageView>(R.id.iv_cheerup_delete)
        var iv_cheerup_lock = findViewById<ImageView>(R.id.iv_cheerup_lock)
        var hs_cheerup_view = findViewById<HorizontalScrollView>(R.id.hs_cheerup_view)
        var cl_cheerup_all = findViewById<ConstraintLayout>(R.id.cl_cheerup_all)
        var tv_cheer_up_view_Text =  findViewById<OutlineTextView>(R.id.tv_cheer_up_view_Text)
        var ll_cheer_up_view =  findViewById<LinearLayout>(R.id.ll_cheer_up_view)



        iv_cheerup_delete.setOnClickListener {
            finish()
        }
        iv_cheerup_lock.isSelected=true
        hs_cheerup_view.isClickable=false
        iv_cheerup_lock.setOnClickListener {

            cl_cheerup_all.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
            doFullScreen()
            if (iv_cheerup_lock.isSelected==true){
                Toast.makeText(this,"잠금 화면이 설정되었습니다.",Toast.LENGTH_SHORT).show()
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
            tv_cheer_up_view_Text.setShadowLayer(15.0f,0.0f,0.0f,Color.parseColor("#ffffff"))
        }
        if(intent.getIntExtra("effect3",0)==1) {
            tv_cheer_up_view_Text.setShadowLayer(4.0f,8.0f,3.0f,Color.parseColor("#2AEFF5"))
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
        }else if (intent.getIntExtra("background_color",0) ==6){
            hs_cheerup_view.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.back_goldenchild_01))
        }else if (intent.getIntExtra("background_color",0) ==7){
            hs_cheerup_view.setBackgroundColor(Color.parseColor("#c80714"))
        }else if (intent.getIntExtra("background_color",0) ==8){
            hs_cheerup_view.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.back_very_01))
        }else if (intent.getIntExtra("background_color",0) ==9){
            hs_cheerup_view.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.back_onoff_01))
        }else if (intent.getIntExtra("background_color",0) ==10){
            hs_cheerup_view.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.back_ownus_01))
        }else if (intent.getIntExtra("background_color",0) ==11){
            hs_cheerup_view.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.back_pentagon_02))
        }else if (intent.getIntExtra("background_color",0) ==12){
            hs_cheerup_view.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.back_too_01))
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

        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = uiOptions

        //애니메이션 부분

        fromX = screenWidth


        var tv_cheer_up_view_Text =  findViewById<OutlineTextView>(R.id.tv_cheer_up_view_Text)
        var ll_cheer_up_view =  findViewById<LinearLayout>(R.id.ll_cheer_up_view)
        //애니메이션 두개 넣기 set
        var set = AnimationSet(false)
        val resources: Resources = this.resources
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        var animation = TranslateAnimation((screenWidth).toFloat(), (-(tv_cheer_up_view_Text.width).toFloat()), 0.0f, -0.0f)
        if (intent.getIntExtra("direction",0)==2){
            if (-screenWidth.toFloat() < -(tv_cheer_up_view_Text.width).toFloat()){
                animation = TranslateAnimation((screenWidth).toFloat()+resources.getDimensionPixelSize(resourceId), (-screenWidth.toFloat()), 0.0f, -0.0f)
            }else
                animation = TranslateAnimation((screenWidth).toFloat()+resources.getDimensionPixelSize(resourceId), (-(tv_cheer_up_view_Text.width).toFloat()), 0.0f, -0.0f)
        }else if (intent.getIntExtra("direction",0)==0){
            if (-screenWidth.toFloat() < -(tv_cheer_up_view_Text.width).toFloat()){
                animation = TranslateAnimation(-screenWidth.toFloat(), (screenWidth).toFloat(), 0.0f, -0.0f)
            } else
                animation = TranslateAnimation((-(tv_cheer_up_view_Text.width).toFloat()), (screenWidth).toFloat()+resources.getDimensionPixelSize(resourceId), 0.0f, -0.0f)
        }else if (intent.getIntExtra("direction",0)==1){
            animation = TranslateAnimation(0.0f, 0.0f, 0.0f, -0.0f)
            if (screenWidth > tv_cheer_up_view_Text.width){
                val params = ll_cheer_up_view.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.CENTER
                ll_cheer_up_view.layoutParams = params
            }
        }

        animation.setInterpolator( AnimationUtils.loadInterpolator( this, android.R.anim.linear_interpolator) )
        if(intent.getIntExtra("speed",0)==0){
            animation.duration = ((((screenWidth).toLong() + (tv_cheer_up_view_Text.width).toLong()))*0.7).toLong()
        }else if (intent.getIntExtra("speed",0) ==1) {
            animation.duration = ((screenWidth).toLong() + (tv_cheer_up_view_Text.width).toLong())
        }else if (intent.getIntExtra("speed",0) ==2){
            animation.duration = ((((screenWidth).toLong() + (tv_cheer_up_view_Text.width).toLong()))*1.3).toLong()
        }
        Log.e("size2","animation.duration = "+ animation.duration + " (screenWidth).toLong() = " + ((screenWidth).toLong().toString()) + "(tv_cheer_up_view_Text).toLong() = " +((tv_cheer_up_view_Text.width).toLong().toString()) + "screenWidth = "+screenWidth.toString() )

   /*     animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
            }
            override fun onAnimationRepeat(animation: Animation) {}
        })*/

        animation.repeatCount= -1

        if (intent.getIntExtra("effect0",0) == 1){
            val alphaAnim = AlphaAnimation(0F, 1.0F)
            alphaAnim.duration = 600
            alphaAnim.repeatCount = -1
            set.addAnimation(alphaAnim)
        }

        set.addAnimation(animation)
        ll_cheer_up_view.animation= set
        ll_cheer_up_view.animation.start()


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


