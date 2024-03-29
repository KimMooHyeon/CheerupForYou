package com.sabuzak.yeonamplace.cheerupforyou.presentation.cheeringboard

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.Dimension
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityCheeringBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheeringBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheeringBoardBinding
    private var screenWidth = 0f
    private var fromX = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheeringBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
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


        //애니메이션 두개 넣기 set
        val set = AnimationSet(false)
        val resources: Resources = this.resources
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        var animation = TranslateAnimation(
            (screenWidth).toFloat(),
            (-(binding.tvCheerUpViewText.width).toFloat()),
            0.0f,
            -0.0f
        )
        if (intent.getIntExtra("direction", 0) == 2) {
            if (-screenWidth.toFloat() < -(binding.tvCheerUpViewText.width).toFloat()) {
                animation = TranslateAnimation(
                    (screenWidth).toFloat() + resources.getDimensionPixelSize(resourceId),
                    (-screenWidth.toFloat()),
                    0.0f,
                    -0.0f
                )
            } else
                animation = TranslateAnimation(
                    (screenWidth).toFloat() + resources.getDimensionPixelSize(resourceId),
                    (-(binding.tvCheerUpViewText.width).toFloat()),
                    0.0f,
                    -0.0f
                )
        } else if (intent.getIntExtra("direction", 0) == 0) {
            if (-screenWidth.toFloat() < -(binding.tvCheerUpViewText.width).toFloat()) {
                animation =
                    TranslateAnimation(-screenWidth.toFloat(), (screenWidth).toFloat(), 0.0f, -0.0f)
            } else
                animation = TranslateAnimation(
                    (-(binding.tvCheerUpViewText.width).toFloat()),
                    (screenWidth).toFloat() + resources.getDimensionPixelSize(resourceId),
                    0.0f,
                    -0.0f
                )
        } else if (intent.getIntExtra("direction", 0) == 1) {
            animation = TranslateAnimation(0.0f, 0.0f, 0.0f, -0.0f)
            if (screenWidth > binding.tvCheerUpViewText.width) {
                val params = binding.llCheerUpView.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.CENTER
                binding.llCheerUpView.layoutParams = params
            }
        }

        animation.interpolator =
            AnimationUtils.loadInterpolator(this, android.R.anim.linear_interpolator)
        if (intent.getIntExtra("speed", 0) == 0) {
            animation.duration =
                ((((screenWidth).toLong() + (binding.tvCheerUpViewText.width).toLong())) * 0.7).toLong()
        } else if (intent.getIntExtra("speed", 0) == 1) {
            animation.duration =
                ((screenWidth).toLong() + (binding.tvCheerUpViewText.width).toLong())
        } else if (intent.getIntExtra("speed", 0) == 2) {
            animation.duration =
                ((((screenWidth).toLong() + (binding.tvCheerUpViewText.width).toLong())) * 1.3).toLong()
        }
        Log.e(
            "size2",
            "animation.duration = " + animation.duration + " (screenWidth).toLong() = " + ((screenWidth).toLong()
                .toString()) + "(binding.tvCheerUpViewText).toLong() = " + ((binding.tvCheerUpViewText.width).toLong()
                .toString()) + "screenWidth = " + screenWidth.toString()
        )

        /*     animation.setAnimationListener(object : Animation.AnimationListener {
                 override fun onAnimationStart(animation: Animation) {}
                 override fun onAnimationEnd(animation: Animation) {
                 }
                 override fun onAnimationRepeat(animation: Animation) {}
             })*/

        animation.repeatCount = -1

        if (intent.getIntExtra("effect0", 0) == 1) {
            val alphaAnim = AlphaAnimation(0F, 1.0F)
            alphaAnim.duration = 600
            alphaAnim.repeatCount = -1
            set.addAnimation(alphaAnim)
        }

        set.addAnimation(animation)
        binding.llCheerUpView.animation = set
        binding.llCheerUpView.animation.start()


        super.onWindowFocusChanged(hasFocus)


    }

     fun initView() {

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding.ivCheerupDelete.setOnClickListener {
            finish()
        }
        binding.ivCheerupLock.isSelected = true
        binding.hsCheerupView.isClickable = false
        binding.ivCheerupLock.setOnClickListener {

            binding.clCheerupAll.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

            if (binding.ivCheerupLock.isSelected) {
                Toast.makeText(this, "잠금 화면이 설정되었습니다.", Toast.LENGTH_SHORT).show()
                binding.ivCheerupLock.isSelected = false
                binding.ivCheerupDelete.visibility = View.INVISIBLE
                binding.ivCheerupDelete.isClickable = false
            } else {
                Toast.makeText(this, "잠금 화면이 해제되었습니다.", Toast.LENGTH_SHORT).show()
                binding.ivCheerupLock.isSelected = true
                binding.ivCheerupDelete.isClickable = true
                binding.ivCheerupDelete.visibility = View.VISIBLE

            }

        }

        binding.tvCheerUpViewText.text = intent.getStringExtra("edt_making_text")
        //효과 받기

        if (intent.getIntExtra("effect1", 0) == 1) {
            //있는거
            binding.tvCheerUpViewText.setStroke(true)
            binding.tvCheerUpViewText.draw(Canvas())
        }

        if (intent.getIntExtra("effect2", 0) == 1) {
            binding.tvCheerUpViewText.setShadowLayer(15.0f, 0.0f, 0.0f, Color.parseColor("#ffffff"))
        }
        if (intent.getIntExtra("effect3", 0) == 1) {
            binding.tvCheerUpViewText.setShadowLayer(4.0f, 8.0f, 3.0f, Color.parseColor("#2AEFF5"))
        }

        // 폰트 받기
        if (intent.getIntExtra("font", 0) == 0) {
            binding.tvCheerUpViewText.typeface = Typeface.createFromAsset(assets, "font/nanum.ttf")
        } else if (intent.getIntExtra("font", 0) == 1) {
            binding.tvCheerUpViewText.typeface =
                Typeface.createFromAsset(assets, "font/hansuwon.ttf")
        } else if (intent.getIntExtra("font", 0) == 2) {
            binding.tvCheerUpViewText.typeface = Typeface.createFromAsset(assets, "font/uljiro.ttf")
        } else if (intent.getIntExtra("font", 0) == 3) {
            binding.tvCheerUpViewText.typeface = Typeface.createFromAsset(assets, "font/hanna.ttf")
        } else if (intent.getIntExtra("font", 0) == 4) {
            binding.tvCheerUpViewText.typeface =
                Typeface.createFromAsset(assets, "font/yanolza.ttf")
        } else if (intent.getIntExtra("font", 0) == 5) {
            binding.tvCheerUpViewText.typeface = Typeface.createFromAsset(assets, "font/jua.ttf")
        } else if (intent.getIntExtra("font", 0) == 6) {
            binding.tvCheerUpViewText.typeface = Typeface.createFromAsset(assets, "font/tvn.ttf")
        }

        // 글자 색 받기
        if (intent.getIntExtra("text_color", 0) == 0) {
            binding.tvCheerUpViewText.setTextColor(Color.parseColor("#ffffff"))
        } else if (intent.getIntExtra("text_color", 0) == 1) {
            binding.tvCheerUpViewText.setTextColor(Color.parseColor("#3c3eda"))
        } else if (intent.getIntExtra("text_color", 0) == 2) {
            binding.tvCheerUpViewText.setTextColor(Color.parseColor("#0191b6"))
        } else if (intent.getIntExtra("text_color", 0) == 3) {
            binding.tvCheerUpViewText.setTextColor(Color.parseColor("#f9d80d"))
        } else if (intent.getIntExtra("text_color", 0) == 4) {
            binding.tvCheerUpViewText.setTextColor(Color.parseColor("#ff7b17"))
        } else if (intent.getIntExtra("text_color", 0) == 5) {
            binding.tvCheerUpViewText.setTextColor(Color.parseColor("#f637f3"))
        }

        // 백그라운드 색 변경

        if (intent.getIntExtra("background_color", 0) == 0) {
            binding.hsCheerupView.setBackgroundColor(Color.parseColor("#000000"))
        } else if (intent.getIntExtra("background_color", 0) == 1) {
            binding.hsCheerupView.setBackgroundColor(Color.parseColor("#3c3eda"))
        } else if (intent.getIntExtra("background_color", 0) == 2) {
            binding.hsCheerupView.setBackgroundColor(Color.parseColor("#0191b6"))
        } else if (intent.getIntExtra("background_color", 0) == 3) {
            binding.hsCheerupView.setBackgroundColor(Color.parseColor("#f9d80d"))
        } else if (intent.getIntExtra("background_color", 0) == 4) {
            binding.hsCheerupView.setBackgroundColor(Color.parseColor("#ff7b17"))
        } else if (intent.getIntExtra("background_color", 0) == 5) {
            binding.hsCheerupView.setBackgroundColor(Color.parseColor("#f637f3"))
        } else if (intent.getIntExtra("background_color", 0) == 6) {
            binding.hsCheerupView.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.back_goldenchild_01
                )
            )
        } else if (intent.getIntExtra("background_color", 0) == 7) {
            binding.hsCheerupView.setBackgroundColor(Color.parseColor("#c80714"))
        } else if (intent.getIntExtra("background_color", 0) == 8) {
            binding.hsCheerupView.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.back_very_01
                )
            )
        } else if (intent.getIntExtra("background_color", 0) == 9) {
            binding.hsCheerupView.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.back_onoff_01
                )
            )
        } else if (intent.getIntExtra("background_color", 0) == 10) {
            binding.hsCheerupView.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.back_ownus_01
                )
            )
        } else if (intent.getIntExtra("background_color", 0) == 11) {
            binding.hsCheerupView.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.back_pentagon_02
                )
            )
        } else if (intent.getIntExtra("background_color", 0) == 12) {
            binding.hsCheerupView.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.back_too_01
                )
            )
        }

        //글자 크기 변경
        if (intent.getIntExtra("text_size", 0) == 0) {
            binding.tvCheerUpViewText.setTextSize(Dimension.SP, 50.0f)
        } else if (intent.getIntExtra("text_size", 0) == 1) {
            binding.tvCheerUpViewText.setTextSize(Dimension.SP, 70.0f)
        } else if (intent.getIntExtra("text_size", 0) == 2) {
            binding.tvCheerUpViewText.setTextSize(Dimension.SP, 130.0f)
        } else if (intent.getIntExtra("text_size", 0) == 3) {
            binding.tvCheerUpViewText.setTextSize(Dimension.SP, 200.0f)
        }
    }
}


