package com.sabuzak.yeonamplace.cheerypforyou

import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.*
import androidx.annotation.Dimension
import kotlinx.android.synthetic.main.activity_making_cheer_up_text.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MakingCheerUpTextActivity : AppCompatActivity() {
    var direction = 1
    var speed = 1
    private var screenWidth = 0f
    private var screenHeight = 0f
    private var fromX = 0f
    private var fromY = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_making_cheer_up_text)


    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        // 각 값 초기값 설정
        var text_size = 2
        var background_color = 0
        var text_color = 0


        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        screenWidth = point.x.toFloat()
        screenHeight = point.y.toFloat()
        fromY = screenHeight
        fromX = screenWidth

        //초기값 보통 검정 하얀 작게
  /*      tv_making_text_size_normal.isSelected=true
        tv_making_color_black.isSelected=true
        tv_making_textcolor_white.isSelected=true
        tv_making_text_speed_normal.isSelected =true
        tv_making_direction_stop.isSelected=true*/
        //edit text 바뀔때마다 text 바꾸게하기
        edt_making_text.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (tv_text.animation != null) {
                    tv_text.clearAnimation()
                }
                if (tv_text.text !=""){
                    setAnim()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tv_text.text = edt_making_text.text.toString()


            }
        })





        //글자 크기 클릭 이벤트
        tv_making_text_size_very_small.setOnClickListener {
            tv_making_text_size_very_small.isSelected=true
            tv_making_text_size_small.isSelected=false
            tv_making_text_size_normal.isSelected=false
            tv_making_text_size_big.isSelected=false
            tv_text.setTextSize(Dimension.SP, 30.0f);
            text_size=0
        }
        tv_making_text_size_small.setOnClickListener {
            tv_making_text_size_very_small.isSelected=false
            tv_making_text_size_small.isSelected=true
            tv_making_text_size_normal.isSelected=false
            tv_making_text_size_big.isSelected=false
            tv_text.setTextSize(Dimension.SP, 60.0f);
            text_size=1
        }
        tv_making_text_size_normal.setOnClickListener {
            tv_making_text_size_very_small.isSelected=false
            tv_making_text_size_small.isSelected=false
            tv_making_text_size_normal.isSelected=true
            tv_making_text_size_big.isSelected=false
            tv_text.setTextSize(Dimension.SP, 90.0f);
            text_size=2
        }
        tv_making_text_size_big.setOnClickListener {
            tv_making_text_size_very_small.isSelected=false
            tv_making_text_size_small.isSelected=false
            tv_making_text_size_normal.isSelected=false
            tv_making_text_size_big.isSelected=true
            tv_text.setTextSize(Dimension.SP, 120.0f);
            text_size=3
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
                startActivity<CheerUpViewActivity>("edt_making_text" to edt_making_text.text.toString(),"text_size" to text_size , "background_color" to background_color , "text_color" to text_color , "speed" to speed , "direction" to direction)
            }else {
                toast("메세지를 입력해주세요")
            }
        }
        super.onWindowFocusChanged(hasFocus)


    }
    public fun setAnim(){

        //애니메이션 두개 넣기 set
        var set = AnimationSet(false)
        var animation = TranslateAnimation((screenWidth).toFloat(), (-(tv_text.width).toFloat()), 0.0f, -0.0f)
        if (direction==2){
            animation = TranslateAnimation((screenWidth).toFloat(), (-(tv_text.width).toFloat()), 0.0f, -0.0f)
        }else if (direction==0){
            animation = TranslateAnimation((-(tv_text.width).toFloat()), (screenWidth).toFloat(), 0.0f, -0.0f)
        }else if (direction==1){
            animation = TranslateAnimation(0.0f, 0.0f, 0.0f, -0.0f)
        }

        animation.setInterpolator( AnimationUtils.loadInterpolator( this, android.R.anim.linear_interpolator) )


        if(speed==0){
            animation.duration = (tv_text.text.length*800).toLong()
        }else if (speed ==1) {
            animation.duration = (tv_text.text.length*1000).toLong()
        }else if (speed ==2){
            animation.duration = (tv_text.text.length*2000).toLong()
        }

        animation.repeatCount= -1

        val alphaAnim = AlphaAnimation(0F, 1.0F)
        alphaAnim.duration = 200
        alphaAnim.repeatCount = -1

        set.addAnimation(animation)
        set.addAnimation(alphaAnim)
        tv_text.animation=set
        tv_text.animation.start()
    }



}
