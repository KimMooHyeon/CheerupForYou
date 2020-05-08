package com.sabuzak.yeonamplace.cheerupforyou

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.hardware.input.InputManager
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Repository.BannerRepository
import com.sabuzak.yeonamplace.cheerupforyou.popup.LodingSavePopUpActivity
import com.sabuzak.yeonamplace.cheerupforyou.popup.SaveFullPopUpActivity
import kotlinx.android.synthetic.main.activity_making_cheer_up_text.*
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MakingCheerUpTextActivity : AppCompatActivity() {
    private lateinit var bannerRepository: BannerRepository
    private  var bannerCount:Int = 0
    // 각 값 초기값 설정
    var text_size = 2
    var background_color = 0
    var text_color = 0
    var direction = 2
    var speed = 1
    var font = 0
    //효과가 없으면 0 있으면 1
    var effect0 = 0
    var effect1 = 0
    var effect2 = 0
    var effect3 = 0
    private var screenWidth = 0f
    private var screenHeight = 0f
    private var fromX = 0f
    private var fromY = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_making_cheer_up_text)
        bannerCount = intent.getIntExtra("bannerCount",0)
        // DEFAULT 값 설정
        tv_making_text_size_normal.isSelected=true
        tv_making_text_speed_normal.isSelected=true
        tv_making_textcolor_white.isSelected=true
        iv_making_direction_left.isSelected=true
        tv_making_color_black.isSelected=true
        tv_making_text_font_nanum.isSelected=true

        tv_making_text_font_hasuwon.setTypeface(Typeface.createFromAsset(getAssets(), "font/hansuwon.ttf"))
        tv_making_text_font_uljiro.setTypeface(Typeface.createFromAsset(getAssets(), "font/uljiro.ttf"))
        tv_making_text_font_hanna.setTypeface(Typeface.createFromAsset(getAssets(), "font/hanna.ttf"))
        tv_making_text_font_yanolza.setTypeface(Typeface.createFromAsset(getAssets(), "font/yanolza.ttf"))
        tv_making_text_font_jua.setTypeface(Typeface.createFromAsset(getAssets(), "font/jua.ttf"))
        tv_making_text_font_tvn.setTypeface(Typeface.createFromAsset(getAssets(), "font/tvn.ttf"))


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        fromX = screenWidth
        screenWidth = point.x.toFloat()
        screenHeight = point.y.toFloat()
        fromY = screenHeight

        var previousString = " "
        tv_making_save_confirm.setOnClickListener {
            tv_making_save_confirm.isClickable=false
            val handler = Handler()
            handler.postDelayed(Runnable { // 저장하기 클릭후 2초 동안 클릭안되게하기
                tv_making_save_confirm.isClickable=true
            }, 2000)


        /**
         * 2020.05.06 [작성자 : 최선필] 배너 객체 생성해서 디비 저장 완료
         */
        val bannerDao = AppDatabase.getDatabase(application).bannerDao()
        bannerRepository = BannerRepository(bannerDao)
        /**
         * 2020.05.07 TODO. 새로운 응원이 저장함에 저장되고 있습니다. 팝업 띄워야 함
         * 2020.05.08 나중에 이거 사용~! 저장함 갯수 판별
         * */

            if (bannerCount >= 5){
                startActivity<SaveFullPopUpActivity>()
            }else {

                runBlocking {
                    val banner = Banner(0,edt_making_text.text.toString(),font, text_size,background_color,text_color,direction,speed,tv_making_cheerup_effect0.isSelected,tv_making_cheerup_effect1.isSelected,tv_making_cheerup_effect2.isSelected,tv_making_cheerup_effect3.isSelected)
                    bannerRepository.insert(banner)
                }
                startActivity<LodingSavePopUpActivity>()
            }



    }
        edt_making_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (edt_making_text.getLineCount() >= 3)
                {
                    edt_making_text.setText(previousString)
                    edt_making_text.setSelection(edt_making_text.length())
                }


                tv_text.text = p0.toString()
                if(tv_text.width < screenWidth){

                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    ll_making_text.layoutParams = params

                }else{
                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    ll_making_text.layoutParams = params
                }

                if (tv_text.animation != null) {
                    tv_text.clearAnimation()
                }
                if (tv_text.text !=""){
                    tv_text.clearAnimation()
                    setAnim(tv_text.width,p0.toString())
                }


            }
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                previousString = p0.toString()
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }
        })
        // 효과 선택

        tv_making_cheerup_effect0.setOnClickListener {
            if(tv_making_cheerup_effect0.isSelected==false){
                tv_making_cheerup_effect0.isSelected = true
                //깜빡임 효과
                if (tv_text.animation != null) {
                    tv_text.clearAnimation()
                }
                effect0=1
                setAnim()
            }else {
                tv_making_cheerup_effect0.isSelected=false
                //깜빡임 효과 끄기
                if (tv_text.animation != null) {
                    tv_text.clearAnimation()
                }
                effect0=0
                setAnim()
            }
        }
        tv_making_cheerup_effect1.setOnClickListener {
            if(tv_making_cheerup_effect1.isSelected==false){
                tv_making_cheerup_effect1.isSelected = true
                //외곽선 효과
                effect1=1
                tv_text.setStroke(true)
                tv_text.draw(Canvas())
            }else {
                tv_making_cheerup_effect1.isSelected=false
                //외곽선 효과 끄기
                tv_text.setStroke(false)
                effect1=0
            }
        }
        tv_making_cheerup_effect2.setOnClickListener {
            if(tv_making_cheerup_effect2.isSelected==false){
                tv_making_cheerup_effect2.isSelected = true
                tv_making_cheerup_effect3.isSelected = false
                tv_text.setShadowLayer(15.0f,0.0f,0.0f,Color.parseColor("#ffffff"))
                effect2=1

            }else {
                tv_making_cheerup_effect2.isSelected=false
                //빛나게 효과 끄기
                effect2=0
                tv_text.setShadowLayer(0.0f,0.0f,0.0f,Color.parseColor("#ffffff"))
            }
        }
        tv_making_cheerup_effect3.setOnClickListener {
            if(tv_making_cheerup_effect3.isSelected==false){
                tv_making_cheerup_effect3.isSelected = true
                tv_making_cheerup_effect2.isSelected = false
                //그림자 효과
                effect3=1
                tv_text.setShadowLayer(4.0f,8.0f,3.0f,Color.parseColor("#2AEFF5"))
            }else {
                tv_making_cheerup_effect3.isSelected=false
                //그림자 효과 끄기
                effect3=0
                tv_text.setShadowLayer(0.0f,0.0f,0.0f,Color.parseColor("#ffffff"))
            }
        }

        // 폰트 선택
        tv_making_text_font_nanum.setOnClickListener {
            tv_making_text_font_nanum.isSelected=true
            tv_making_text_font_hasuwon.isSelected=false
            tv_making_text_font_uljiro.isSelected=false
            tv_making_text_font_hanna.isSelected=false
            tv_making_text_font_yanolza.isSelected=false
            tv_making_text_font_jua.isSelected=false
            tv_making_text_font_tvn.isSelected=false
            font=0
            tv_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/nanum.ttf"))
            edt_making_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/nanum.ttf"))
        }
        tv_making_text_font_hasuwon.setOnClickListener {
            tv_making_text_font_nanum.isSelected=false
            tv_making_text_font_hasuwon.isSelected=true
            tv_making_text_font_uljiro.isSelected=false
            tv_making_text_font_hanna.isSelected=false
            tv_making_text_font_yanolza.isSelected=false
            tv_making_text_font_jua.isSelected=false
            tv_making_text_font_tvn.isSelected=false
            font=1
            tv_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/hansuwon.ttf"))
            edt_making_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/hansuwon.ttf"))
        }
        tv_making_text_font_uljiro.setOnClickListener {
            tv_making_text_font_nanum.isSelected=false
            tv_making_text_font_hasuwon.isSelected=false
            tv_making_text_font_uljiro.isSelected=true
            tv_making_text_font_hanna.isSelected=false
            tv_making_text_font_yanolza.isSelected=false
            tv_making_text_font_jua.isSelected=false
            tv_making_text_font_tvn.isSelected=false
            font=2
            tv_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/uljiro.ttf"))
            edt_making_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/uljiro.ttf"))
        }
        tv_making_text_font_hanna.setOnClickListener {
            tv_making_text_font_nanum.isSelected=false
            tv_making_text_font_hasuwon.isSelected=false
            tv_making_text_font_uljiro.isSelected=false
            tv_making_text_font_hanna.isSelected=true
            tv_making_text_font_yanolza.isSelected=false
            tv_making_text_font_jua.isSelected=false
            tv_making_text_font_tvn.isSelected=false
            font=3
            tv_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/hanna.ttf"))
            edt_making_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/hanna.ttf"))
        }
        tv_making_text_font_yanolza.setOnClickListener {
            tv_making_text_font_nanum.isSelected=false
            tv_making_text_font_hasuwon.isSelected=false
            tv_making_text_font_uljiro.isSelected=false
            tv_making_text_font_hanna.isSelected=false
            tv_making_text_font_yanolza.isSelected=true
            tv_making_text_font_jua.isSelected=false
            tv_making_text_font_tvn.isSelected=false
            font=4
            tv_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/yanolza.ttf"))
            edt_making_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/yanolza.ttf"))
        }
        tv_making_text_font_jua.setOnClickListener {
            tv_making_text_font_nanum.isSelected=false
            tv_making_text_font_hasuwon.isSelected=false
            tv_making_text_font_uljiro.isSelected=false
            tv_making_text_font_hanna.isSelected=false
            tv_making_text_font_yanolza.isSelected=false
            tv_making_text_font_jua.isSelected=true
            tv_making_text_font_tvn.isSelected=false
            font=5
            tv_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/jua.ttf"))
            edt_making_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/jua.ttf"))
        }
        tv_making_text_font_tvn.setOnClickListener {
            tv_making_text_font_nanum.isSelected=false
            tv_making_text_font_hasuwon.isSelected=false
            tv_making_text_font_uljiro.isSelected=false
            tv_making_text_font_hanna.isSelected=false
            tv_making_text_font_yanolza.isSelected=false
            tv_making_text_font_jua.isSelected=false
            tv_making_text_font_tvn.isSelected=true
            font=6
            tv_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/tvn.ttf"))
            edt_making_text.setTypeface(Typeface.createFromAsset(getAssets(), "font/tvn.ttf"))
        }
        val handler = Handler()
        //글자 크기 클릭 이벤트
        tv_making_text_size_very_small.setOnClickListener {
            tv_making_text_size_very_small.isSelected=true
            tv_making_text_size_small.isSelected=false
            tv_making_text_size_normal.isSelected=false
            tv_making_text_size_big.isSelected=false
            tv_text.setTextSize(Dimension.SP, 30.0f);
            text_size=0

            handler.postDelayed(Runnable {
                if(tv_text.width < screenWidth){

                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    ll_making_text.layoutParams = params

                }else{
                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    ll_making_text.layoutParams = params
                }
            }, 1)
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            setAnim()

        }
        tv_making_text_size_small.setOnClickListener {
            tv_making_text_size_very_small.isSelected=false
            tv_making_text_size_small.isSelected=true
            tv_making_text_size_normal.isSelected=false
            tv_making_text_size_big.isSelected=false
            tv_text.setTextSize(Dimension.SP, 60.0f);
            text_size=1
            handler.postDelayed(Runnable {
                if(tv_text.width < screenWidth){

                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    ll_making_text.layoutParams = params

                }else{
                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    ll_making_text.layoutParams = params
                }
            }, 1)
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            setAnim()
        }
        tv_making_text_size_normal.setOnClickListener {
            tv_making_text_size_very_small.isSelected=false
            tv_making_text_size_small.isSelected=false
            tv_making_text_size_normal.isSelected=true
            tv_making_text_size_big.isSelected=false
            tv_text.setTextSize(Dimension.SP, 90.0f);
            text_size=2
            handler.postDelayed(Runnable {
                if(tv_text.width < screenWidth){

                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    ll_making_text.layoutParams = params

                }else{
                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    ll_making_text.layoutParams = params
                }
            }, 1)
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            setAnim()
        }
        tv_making_text_size_big.setOnClickListener {
            tv_making_text_size_very_small.isSelected=false
            tv_making_text_size_small.isSelected=false
            tv_making_text_size_normal.isSelected=false
            tv_making_text_size_big.isSelected=true
            tv_text.setTextSize(Dimension.SP, 120.0f);
            text_size=3
            handler.postDelayed(Runnable {
                if(tv_text.width < screenWidth){

                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    ll_making_text.layoutParams = params

                }else{
                    val params = ll_making_text.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    ll_making_text.layoutParams = params
                }
            }, 1)
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            setAnim()
        }

        //배경 색깔 선택
        tv_making_color_black.setOnClickListener {
            tv_making_color_black.isSelected=true
            tv_making_color_blue.isSelected=false
            tv_making_color_dark_green.isSelected=false
            tv_making_color_yellow.isSelected=false
            tv_making_color_orange.isSelected=false
            tv_making_color_pink.isSelected=false
            hs_makingtext.setBackgroundColor(Color.parseColor("#000000"))
            background_color=0
        }
        tv_making_color_blue.setOnClickListener {
            tv_making_color_black.isSelected=false
            tv_making_color_blue.isSelected=true
            tv_making_color_dark_green.isSelected=false
            tv_making_color_yellow.isSelected=false
            tv_making_color_orange.isSelected=false
            tv_making_color_pink.isSelected=false
            hs_makingtext.setBackgroundColor(Color.parseColor("#3c3eda"))
            background_color=1
        }
        tv_making_color_dark_green.setOnClickListener {
            tv_making_color_black.isSelected=false
            tv_making_color_blue.isSelected=false
            tv_making_color_dark_green.isSelected=true
            tv_making_color_yellow.isSelected=false
            tv_making_color_orange.isSelected=false
            tv_making_color_pink.isSelected=false
            hs_makingtext.setBackgroundColor(Color.parseColor("#0191b6"))
            background_color=2
        }
        tv_making_color_yellow.setOnClickListener {
            tv_making_color_black.isSelected=false
            tv_making_color_blue.isSelected=false
            tv_making_color_dark_green.isSelected=false
            tv_making_color_yellow.isSelected=true
            tv_making_color_orange.isSelected=false
            tv_making_color_pink.isSelected=false
            hs_makingtext.setBackgroundColor(Color.parseColor("#f9d80d"))
            background_color=3
        }
        tv_making_color_orange.setOnClickListener {
            tv_making_color_black.isSelected=false
            tv_making_color_blue.isSelected=false
            tv_making_color_dark_green.isSelected=false
            tv_making_color_yellow.isSelected=false
            tv_making_color_orange.isSelected=true
            tv_making_color_pink.isSelected=false
            hs_makingtext.setBackgroundColor(Color.parseColor("#ff7b17"))
            background_color=4
        }
        tv_making_color_pink.setOnClickListener {
            tv_making_color_black.isSelected=false
            tv_making_color_blue.isSelected=false
            tv_making_color_dark_green.isSelected=false
            tv_making_color_yellow.isSelected=false
            tv_making_color_orange.isSelected=false
            tv_making_color_pink.isSelected=true
            hs_makingtext.setBackgroundColor(Color.parseColor("#f637f3"))
            background_color=5
        }

        // 글자 색깔
        tv_making_textcolor_white.setOnClickListener {
            tv_making_textcolor_white.isSelected = true
            tv_making_textcolor_blue.isSelected = false
            tv_making_textcolor_dark_green.isSelected =false
            tv_making_textcolor_yellow.isSelected= false
            tv_making_textcolor_orange.isSelected= false
            tv_making_textcolor_pink.isSelected =false
            tv_text.setTextColor(Color.parseColor("#FFFFFF"))
            text_color=0
        }
        tv_making_textcolor_blue.setOnClickListener {
            tv_making_textcolor_white.isSelected = false
            tv_making_textcolor_blue.isSelected = true
            tv_making_textcolor_dark_green.isSelected =false
            tv_making_textcolor_yellow.isSelected= false
            tv_making_textcolor_orange.isSelected= false
            tv_making_textcolor_pink.isSelected =false
            tv_text.setTextColor(Color.parseColor("#3c3eda"))
            text_color=1
        }
        tv_making_textcolor_dark_green.setOnClickListener {
            tv_making_textcolor_white.isSelected = false
            tv_making_textcolor_blue.isSelected = false
            tv_making_textcolor_dark_green.isSelected =true
            tv_making_textcolor_yellow.isSelected= false
            tv_making_textcolor_orange.isSelected= false
            tv_making_textcolor_pink.isSelected =false
            tv_text.setTextColor(Color.parseColor("#0191b6"))
            text_color=2
        }
        tv_making_textcolor_yellow.setOnClickListener {
            tv_making_textcolor_white.isSelected = false
            tv_making_textcolor_blue.isSelected = false
            tv_making_textcolor_dark_green.isSelected =false
            tv_making_textcolor_yellow.isSelected= true
            tv_making_textcolor_orange.isSelected= false
            tv_making_textcolor_pink.isSelected =false
            tv_text.setTextColor(Color.parseColor("#f9d80d"))
            text_color=3
        }
        tv_making_textcolor_orange.setOnClickListener {
            tv_making_textcolor_white.isSelected = false
            tv_making_textcolor_blue.isSelected = false
            tv_making_textcolor_dark_green.isSelected =false
            tv_making_textcolor_yellow.isSelected= false
            tv_making_textcolor_orange.isSelected= true
            tv_making_textcolor_pink.isSelected =false
            tv_text.setTextColor(Color.parseColor("#ff7b17"))
            text_color=4
        }
        tv_making_textcolor_pink.setOnClickListener {
            tv_making_textcolor_white.isSelected = false
            tv_making_textcolor_blue.isSelected = false
            tv_making_textcolor_dark_green.isSelected =false
            tv_making_textcolor_yellow.isSelected= false
            tv_making_textcolor_orange.isSelected= false
            tv_making_textcolor_pink.isSelected =true
            tv_text.setTextColor(Color.parseColor("#f637f3"))
            text_color=5
        }
        // 글자 속도 선택
        tv_making_text_speed_fast.setOnClickListener {
            tv_making_text_speed_fast.isSelected=true
            tv_making_text_speed_normal.isSelected=false
            tv_making_text_speed_slow.isSelected = false
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            speed=0
            setAnim()
        }
        tv_making_text_speed_normal.setOnClickListener {
            tv_making_text_speed_fast.isSelected=false
            tv_making_text_speed_normal.isSelected=true
            tv_making_text_speed_slow.isSelected = false
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            speed=1
            setAnim()

        }
        tv_making_text_speed_slow.setOnClickListener {
            tv_making_text_speed_fast.isSelected=false
            tv_making_text_speed_normal.isSelected=false
            tv_making_text_speed_slow.isSelected = true
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            speed=2
            setAnim()
        }
        //방향
        iv_making_direction_right.setOnClickListener {
            iv_making_direction_right.isSelected=true
            tv_making_direction_stop.isSelected=false
            iv_making_direction_left.isSelected=false
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            direction=0
            setAnim()
        }
        tv_making_direction_stop.setOnClickListener {
            iv_making_direction_right.isSelected=false
            tv_making_direction_stop.isSelected=true
            iv_making_direction_left.isSelected=false
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            direction=1
            setAnim()


        }
        iv_making_direction_left.setOnClickListener {
            iv_making_direction_right.isSelected=false
            tv_making_direction_stop.isSelected=false
            iv_making_direction_left.isSelected=true
            if (tv_text.animation != null) {
                tv_text.clearAnimation()
            }
            direction=2
            setAnim()
        }





        //뒤로가기 버튼
        iv_making_back_button.setOnClickListener {
            finish()
        }
        // 확대 하기
        iv_making_expand_button.setOnClickListener {
            if (edt_making_text.text.toString().isNotEmpty()){
                //포커스 문제 ....
                var inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(edt_making_text, 0)

                startActivity<CheerUpViewActivity>("edt_making_text" to edt_making_text.text.toString(),"text_size" to text_size , "background_color" to background_color , "text_color" to text_color , "speed" to speed , "direction" to direction,"font" to font , "effect0" to effect0,"effect1" to effect1,"effect2" to effect2,"effect3" to effect3)
            }else {
                toast("메세지를 입력해주세요")
            }
        }
        super.onWindowFocusChanged(hasFocus)


    }
    fun setAnim(width: Int = tv_text.width, text: CharSequence = tv_text.text ){

        //애니메이션 두개 넣기 set
        var set = AnimationSet(false)

        var animation = TranslateAnimation((screenWidth).toFloat(), (-(width).toFloat()), 0.0f, -0.0f)
        if (direction==2){
            if (-screenWidth.toFloat() < -(width).toFloat()){
                animation = TranslateAnimation((screenWidth).toFloat(), (-screenWidth.toFloat()), 0.0f, -0.0f)
            }else
                animation = TranslateAnimation((screenWidth).toFloat(), (-(width).toFloat()), 0.0f, -0.0f)
        }else if (direction==0){
            if (-screenWidth.toFloat() < -(width).toFloat()){
                animation = TranslateAnimation(-screenWidth.toFloat(), (screenWidth).toFloat(), 0.0f, -0.0f)
            } else
                animation = TranslateAnimation((-(width).toFloat()), (screenWidth).toFloat(), 0.0f, -0.0f)
        }else if (direction==1){
            animation = TranslateAnimation(0.0f, 0.0f, 0.0f, -0.0f)
        }

        animation.setInterpolator( AnimationUtils.loadInterpolator( this, android.R.anim.linear_interpolator) )


        if(speed==0){
            animation.duration = (((screenWidth).toLong() + (width).toLong())*0.8).toLong()
        }else if (speed ==1) {
            animation.duration = ((screenWidth).toLong() + (width).toLong())
        }else if (speed ==2){
            animation.duration = (((screenWidth).toLong() + (width).toLong())*1.2).toLong()
        }

        animation.repeatCount= -1

        if (effect0 == 1){
            val alphaAnim = AlphaAnimation(0F, 1.0F)
            alphaAnim.duration = 400
            alphaAnim.repeatCount = -1
            set.addAnimation(alphaAnim)
        }

        set.addAnimation(animation)
        ll_making_text.animation=set
        ll_making_text.animation.start()
    }



}