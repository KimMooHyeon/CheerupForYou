package com.sabuzak.yeonamplace.cheerupforyou.presentation.makingcheerup

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.annotation.Dimension
import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import com.sabuzak.yeonamplace.cheerupforyou.data.dataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeLocalDataSource
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityMakingCheerUpTextBinding
import com.sabuzak.yeonamplace.cheerupforyou.presentation.cheeringboard.CheeringBoardActivity
import com.sabuzak.yeonamplace.cheerupforyou.presentation.popup.LoadingSavePopUpActivity
import com.sabuzak.yeonamplace.cheerupforyou.presentation.popup.SaveFullPopUpActivity
import com.sabuzak.yeonamplace.cheerupforyou.presentation.popup.delete.DeleteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

@AndroidEntryPoint
class MakingCheerUpTextActivity :
    BaseActivity<ActivityMakingCheerUpTextBinding>(R.layout.activity_making_cheer_up_text) {
    private val viewModel: MakingCheerUpTextViewModel by viewModels()
    private var bannerCount: Int = 0

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

    override fun onWindowFocusChanged(hasFocus: Boolean) {

        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        fromX = screenWidth
        screenWidth = point.x.toFloat()
        screenHeight = point.y.toFloat()
        fromY = screenHeight

        var previousString = " "

        // 수정하기 부분
        if (intent.getIntExtra("idx", -99) != -99) {

            // 배너의 idx 값 intent.getIntExtra("idx")
            binding.edtMakingText.hint = intent.getStringExtra("edt_making_text")
            binding.tvMakingSaveConfirm.text = "수정하기"
            binding.tvText.text = intent.getStringExtra("edt_making_text")
            text_size = intent.getIntExtra("text_size", -99)
            background_color = intent.getIntExtra("background_color", -99)
            text_color = intent.getIntExtra("text_color", -99)
            direction = intent.getIntExtra("direction", -99)
            speed = intent.getIntExtra("speed", -99)
            font = intent.getIntExtra("font", -99)

            if (direction == 0) {
                binding.ivMakingDirectionRight.isSelected = true
                binding.ivMakingDirectionLeft.isSelected = false
            } else if (direction == 1) {
                binding.tvMakingDirectionStop.isSelected = true
                binding.ivMakingDirectionLeft.isSelected = false
            } else if (direction == 2) {
                binding.ivMakingDirectionLeft.isSelected = true
            }

            if (speed == 0) {
                binding.tvMakingTextSpeedFast.isSelected = true
                binding.tvMakingTextSpeedNormal.isSelected = false
                binding.tvMakingTextSpeedSlow.isSelected = false
            } else if (speed == 1) {
                binding.tvMakingTextSpeedFast.isSelected = false
                binding.tvMakingTextSpeedNormal.isSelected = true
                binding.tvMakingTextSpeedSlow.isSelected = false
            } else if (speed == 2) {
                binding.tvMakingTextSpeedFast.isSelected = false
                binding.tvMakingTextSpeedNormal.isSelected = false
                binding.tvMakingTextSpeedSlow.isSelected = true
            }

            //효과가 없으면 0 있으면 1
            if (intent.getBooleanExtra("effect0", false)) {
                effect0 = 1
                binding.tvMakingCheerupEffect0.isSelected = true
            }
            if (intent.getBooleanExtra("effect1", false)) {
                effect1 = 1
                binding.tvMakingCheerupEffect1.isSelected = true
            }
            if (intent.getBooleanExtra("effect2", false)) {
                effect2 = 1
                binding.tvMakingCheerupEffect2.isSelected = true
            }
            if (intent.getBooleanExtra("effect3", false)) {
                effect3 = 1
                binding.tvMakingCheerupEffect3.isSelected = true
            }

            //효과 받기

            if (effect1 == 1) {
                //있는거
                binding.tvText.setStroke(true)
                binding.tvText.draw(Canvas())
            }

            if (effect2 == 1) {
                binding.tvText.setShadowLayer(15.0f, 0.0f, 0.0f, Color.parseColor("#ffffff"))
            }
            if (effect3 == 1) {
                binding.tvText.setShadowLayer(4.0f, 8.0f, 3.0f, Color.parseColor("#2AEFF5"))
            }

            // 폰트 받기
            if (font == 0) {
                binding.tvText.typeface = Typeface.createFromAsset(assets, "font/nanum.ttf")
                binding.tvMakingTextFontNanum.isSelected = true
            } else if (font == 1) {
                binding.tvText.typeface = Typeface.createFromAsset(assets, "font/hansuwon.ttf")
                binding.tvMakingTextFontNanum.isSelected = false
                binding.tvMakingTextFontHasuwon.isSelected = true
            } else if (font == 2) {
                binding.tvText.typeface = Typeface.createFromAsset(assets, "font/uljiro.ttf")
                binding.tvMakingTextFontNanum.isSelected = false
                binding.tvMakingTextFontUljiro.isSelected = true
            } else if (font == 3) {
                binding.tvText.typeface = Typeface.createFromAsset(assets, "font/hanna.ttf")
                binding.tvMakingTextFontNanum.isSelected = false
                binding.tvMakingTextFontHanna.isSelected = true
            } else if (font == 4) {
                binding.tvText.typeface = Typeface.createFromAsset(assets, "font/yanolza.ttf")
                binding.tvMakingTextFontNanum.isSelected = false
                binding.tvMakingTextFontYanolza.isSelected = true
            } else if (font == 5) {
                binding.tvText.typeface = Typeface.createFromAsset(assets, "font/jua.ttf")
                binding.tvMakingTextFontNanum.isSelected = false
                binding.tvMakingTextFontJua.isSelected = true
            } else if (font == 6) {
                binding.tvText.typeface = Typeface.createFromAsset(assets, "font/tvn.ttf")
                binding.tvMakingTextFontNanum.isSelected = false
                binding.tvMakingTextFontTvn.isSelected = true
            }
            binding.tvMakingTextcolorDarkGreen
            // 글자 색 받기
            if (text_color == 0) {
                binding.tvText.setTextColor(Color.parseColor("#ffffff"))
                binding.tvMakingTextcolorWhite.isSelected = true
            } else if (text_color == 1) {
                binding.tvText.setTextColor(Color.parseColor("#3c3eda"))
                binding.tvMakingTextcolorWhite.isSelected = false
                binding.tvMakingTextcolorBlue.isSelected = true
            } else if (text_color == 2) {
                binding.tvText.setTextColor(Color.parseColor("#0191b6"))
                binding.tvMakingTextcolorWhite.isSelected = false
                binding.tvMakingTextcolorDarkGreen.isSelected = true
            } else if (text_color == 3) {
                binding.tvText.setTextColor(Color.parseColor("#f9d80d"))
                binding.tvMakingTextcolorWhite.isSelected = false
                binding.tvMakingTextcolorYellow.isSelected = true
            } else if (text_color == 4) {
                binding.tvText.setTextColor(Color.parseColor("#ff7b17"))
                binding.tvMakingTextcolorWhite.isSelected = false
                binding.tvMakingTextcolorOrange.isSelected = true
            } else if (text_color == 5) {
                binding.tvText.setTextColor(Color.parseColor("#f637f3"))
                binding.tvMakingTextcolorWhite.isSelected = false
                binding.tvMakingTextcolorPink.isSelected = true
            }

            // 백그라운드 색 변경
            if (background_color == 0) {
                binding.hsMakingtext.setBackgroundColor(Color.parseColor("#000000"))
                binding.tvMakingColorBlack.isSelected = true
                binding.tvMakingColorBlue.isSelected = false
                binding.tvMakingColorDarkGreen.isSelected = false
                binding.tvMakingColorYellow.isSelected = false
                binding.tvMakingColorOrange.isSelected = false
                binding.tvMakingColorPink.isSelected = false
            } else if (background_color == 1) {
                binding.hsMakingtext.setBackgroundColor(Color.parseColor("#3c3eda"))
                binding.tvMakingColorBlack.isSelected = false
                binding.tvMakingColorBlue.isSelected = true
            } else if (background_color == 2) {
                binding.hsMakingtext.setBackgroundColor(Color.parseColor("#0191b6"))
                binding.tvMakingColorBlack.isSelected = false
                binding.tvMakingColorDarkGreen.isSelected = true
            } else if (background_color == 3) {
                binding.hsMakingtext.setBackgroundColor(Color.parseColor("#f9d80d"))
                binding.tvMakingColorBlack.isSelected = false
                binding.tvMakingColorYellow.isSelected = true
            } else if (background_color == 4) {
                binding.hsMakingtext.setBackgroundColor(Color.parseColor("#ff7b17"))
                binding.tvMakingColorBlack.isSelected = false
                binding.tvMakingColorOrange.isSelected = true
            } else if (background_color == 5) {
                binding.hsMakingtext.setBackgroundColor(Color.parseColor("#f637f3"))
                binding.tvMakingColorBlack.isSelected = false
                binding.tvMakingColorPink.isSelected = true
            }

            //글자 크기 변경
            if (text_size == 0) {
                binding.tvText.setTextSize(Dimension.SP, 30.0f)
                binding.tvMakingTextSizeVerySmall.isSelected = true
                binding.tvMakingTextSizeNormal.isSelected = false
            } else if (text_size == 1) {
                binding.tvText.setTextSize(Dimension.SP, 60.0f)
                binding.tvMakingTextSizeSmall.isSelected = true
                binding.tvMakingTextSizeNormal.isSelected = false
            } else if (text_size == 2) {
                binding.tvText.setTextSize(Dimension.SP, 90.0f)
                binding.tvMakingTextSizeNormal.isSelected = true
            } else if (text_size == 3) {
                binding.tvText.setTextSize(Dimension.SP, 120.0f)
                binding.tvMakingTextSizeNormal.isSelected = false
                binding.tvMakingTextSizeBig.isSelected = true
            }

            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }

            /**
             * 2020.05.12 width 0 되는 문제 해결
             */
            binding.tvText.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            setAnim(binding.tvText.measuredWidth)

        }


        binding.tvMakingSaveConfirm.setOnClickListener {
            binding.tvMakingSaveConfirm.isClickable = false
            val handler = Handler()
            handler.postDelayed(Runnable { // 저장하기 클릭후 2초 동안 클릭안되게하기
                binding.tvMakingSaveConfirm.isClickable = true
            }, 2000)


            /**
             * 2020.05.06 [작성자 : 최선필] 배너 객체 생성해서 디비 저장 완료
             */
            /**
             * 2020.05.07 TODO. 새로운 응원이 저장함에 저장되고 있습니다. 팝업 띄워야 함
             * 2020.05.08 나중에 이거 사용~! 저장함 갯수 판별
             * */

            if (binding.tvMakingSaveConfirm.text == "수정하기") {
                runBlocking {
                    val banner = Banner(
                        intent.getIntExtra("idx", -99),
                        binding.tvText.text.toString(),
                        font,
                        text_size,
                        background_color,
                        text_color,
                        direction,
                        speed,
                        binding.tvMakingCheerupEffect0.isSelected,
                        binding.tvMakingCheerupEffect1.isSelected,
                        binding.tvMakingCheerupEffect2.isSelected,
                        binding.tvMakingCheerupEffect3.isSelected
                    )
                    viewModel.update(banner)
                }
                startActivity<LoadingSavePopUpActivity>()

            } else {

                if (bannerCount >= 5) {
                    startActivity<SaveFullPopUpActivity>()
                } else {

                    runBlocking {
                        val banner = Banner(
                            0,
                            binding.tvText.text.toString(),
                            font,
                            text_size,
                            background_color,
                            text_color,
                            direction,
                            speed,
                            binding.tvMakingCheerupEffect0.isSelected,
                            binding.tvMakingCheerupEffect1.isSelected,
                            binding.tvMakingCheerupEffect2.isSelected,
                            binding.tvMakingCheerupEffect3.isSelected
                        )
                        viewModel.insert(banner)
                    }
                    startActivity<LoadingSavePopUpActivity>()
                }
            }
        }

        binding.edtMakingText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (binding.edtMakingText.lineCount >= 3) {
                    binding.edtMakingText.setText(previousString)
                    binding.edtMakingText.setSelection(binding.edtMakingText.length())
                }


                binding.tvText.text = p0.toString()
                if (binding.tvText.width < screenWidth) {

                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    binding.llMakingText.layoutParams = params

                } else {
                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    binding.llMakingText.layoutParams = params
                }

                if (binding.tvText.animation != null) {
                    binding.tvText.clearAnimation()
                }
                if (binding.tvText.text != "") {
                    binding.tvText.clearAnimation()
                    setAnim(binding.tvText.width, p0.toString())
                }


            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                previousString = p0.toString()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }
        })
        // 효과 선택

        binding.tvMakingCheerupEffect0.setOnClickListener {
            if (!binding.tvMakingCheerupEffect0.isSelected) {
                binding.tvMakingCheerupEffect0.isSelected = true
                //깜빡임 효과
                if (binding.tvText.animation != null) {
                    binding.tvText.clearAnimation()
                }
                effect0 = 1
                setAnim()
            } else {
                binding.tvMakingCheerupEffect0.isSelected = false
                //깜빡임 효과 끄기
                if (binding.tvText.animation != null) {
                    binding.tvText.clearAnimation()
                }
                effect0 = 0
                setAnim()
            }
        }
        binding.tvMakingCheerupEffect1.setOnClickListener {
            if (!binding.tvMakingCheerupEffect1.isSelected) {
                binding.tvMakingCheerupEffect1.isSelected = true
                //외곽선 효과
                effect1 = 1
                binding.tvText.setStroke(true)
                binding.tvText.draw(Canvas())
            } else {
                binding.tvMakingCheerupEffect1.isSelected = false
                //외곽선 효과 끄기
                binding.tvText.setStroke(false)
                effect1 = 0
            }
        }
        binding.tvMakingCheerupEffect2.setOnClickListener {
            if (!binding.tvMakingCheerupEffect2.isSelected) {
                binding.tvMakingCheerupEffect2.isSelected = true
                binding.tvMakingCheerupEffect3.isSelected = false
                binding.tvText.setShadowLayer(15.0f, 0.0f, 0.0f, Color.parseColor("#ffffff"))
                effect2 = 1

            } else {
                binding.tvMakingCheerupEffect2.isSelected = false
                //빛나게 효과 끄기
                effect2 = 0
                binding.tvText.setShadowLayer(0.0f, 0.0f, 0.0f, Color.parseColor("#ffffff"))
            }
        }
        binding.tvMakingCheerupEffect3.setOnClickListener {
            if (!binding.tvMakingCheerupEffect3.isSelected) {
                binding.tvMakingCheerupEffect3.isSelected = true
                binding.tvMakingCheerupEffect2.isSelected = false
                //그림자 효과
                effect3 = 1
                binding.tvText.setShadowLayer(4.0f, 8.0f, 3.0f, Color.parseColor("#2AEFF5"))
            } else {
                binding.tvMakingCheerupEffect3.isSelected = false
                //그림자 효과 끄기
                effect3 = 0
                binding.tvText.setShadowLayer(0.0f, 0.0f, 0.0f, Color.parseColor("#ffffff"))
            }
        }


        // 폰트 선택
        binding.tvMakingTextFontNanum.setOnClickListener {
            binding.tvMakingTextFontNanum.isSelected = true
            binding.tvMakingTextFontHasuwon.isSelected = false
            binding.tvMakingTextFontUljiro.isSelected = false
            binding.tvMakingTextFontHanna.isSelected = false
            binding.tvMakingTextFontYanolza.isSelected = false
            binding.tvMakingTextFontJua.isSelected = false
            binding.tvMakingTextFontTvn.isSelected = false
            font = 0
            binding.tvText.typeface = Typeface.createFromAsset(assets, "font/nanum.ttf")
            binding.edtMakingText.typeface = Typeface.createFromAsset(assets, "font/nanum.ttf")
        }
        binding.tvMakingTextFontHasuwon.setOnClickListener {
            binding.tvMakingTextFontNanum.isSelected = false
            binding.tvMakingTextFontHasuwon.isSelected = true
            binding.tvMakingTextFontUljiro.isSelected = false
            binding.tvMakingTextFontHanna.isSelected = false
            binding.tvMakingTextFontYanolza.isSelected = false
            binding.tvMakingTextFontJua.isSelected = false
            binding.tvMakingTextFontTvn.isSelected = false
            font = 1
            binding.tvText.typeface = Typeface.createFromAsset(assets, "font/hansuwon.ttf")
            binding.edtMakingText.typeface = Typeface.createFromAsset(assets, "font/hansuwon.ttf")
        }
        binding.tvMakingTextFontUljiro.setOnClickListener {
            binding.tvMakingTextFontNanum.isSelected = false
            binding.tvMakingTextFontHasuwon.isSelected = false
            binding.tvMakingTextFontUljiro.isSelected = true
            binding.tvMakingTextFontHanna.isSelected = false
            binding.tvMakingTextFontYanolza.isSelected = false
            binding.tvMakingTextFontJua.isSelected = false
            binding.tvMakingTextFontTvn.isSelected = false
            font = 2
            binding.tvText.typeface = Typeface.createFromAsset(assets, "font/uljiro.ttf")
            binding.edtMakingText.typeface = Typeface.createFromAsset(assets, "font/uljiro.ttf")
        }
        binding.tvMakingTextFontHanna.setOnClickListener {
            binding.tvMakingTextFontNanum.isSelected = false
            binding.tvMakingTextFontHasuwon.isSelected = false
            binding.tvMakingTextFontUljiro.isSelected = false
            binding.tvMakingTextFontHanna.isSelected = true
            binding.tvMakingTextFontYanolza.isSelected = false
            binding.tvMakingTextFontJua.isSelected = false
            binding.tvMakingTextFontTvn.isSelected = false
            font = 3
            binding.tvText.typeface = Typeface.createFromAsset(assets, "font/hanna.ttf")
            binding.edtMakingText.typeface = Typeface.createFromAsset(assets, "font/hanna.ttf")
        }
        binding.tvMakingTextFontYanolza.setOnClickListener {
            binding.tvMakingTextFontNanum.isSelected = false
            binding.tvMakingTextFontHasuwon.isSelected = false
            binding.tvMakingTextFontUljiro.isSelected = false
            binding.tvMakingTextFontHanna.isSelected = false
            binding.tvMakingTextFontYanolza.isSelected = true
            binding.tvMakingTextFontJua.isSelected = false
            binding.tvMakingTextFontTvn.isSelected = false
            font = 4
            binding.tvText.typeface = Typeface.createFromAsset(assets, "font/yanolza.ttf")
            binding.edtMakingText.typeface = Typeface.createFromAsset(assets, "font/yanolza.ttf")
        }
        binding.tvMakingTextFontJua.setOnClickListener {
            binding.tvMakingTextFontNanum.isSelected = false
            binding.tvMakingTextFontHasuwon.isSelected = false
            binding.tvMakingTextFontUljiro.isSelected = false
            binding.tvMakingTextFontHanna.isSelected = false
            binding.tvMakingTextFontYanolza.isSelected = false
            binding.tvMakingTextFontJua.isSelected = true
            binding.tvMakingTextFontTvn.isSelected = false
            font = 5
            binding.tvText.typeface = Typeface.createFromAsset(assets, "font/jua.ttf")
            binding.edtMakingText.typeface = Typeface.createFromAsset(assets, "font/jua.ttf")
        }
        binding.tvMakingTextFontTvn.setOnClickListener {
            binding.tvMakingTextFontNanum.isSelected = false
            binding.tvMakingTextFontHasuwon.isSelected = false
            binding.tvMakingTextFontUljiro.isSelected = false
            binding.tvMakingTextFontHanna.isSelected = false
            binding.tvMakingTextFontYanolza.isSelected = false
            binding.tvMakingTextFontJua.isSelected = false
            binding.tvMakingTextFontTvn.isSelected = true
            font = 6
            binding.tvText.typeface = Typeface.createFromAsset(assets, "font/tvn.ttf")
            binding.edtMakingText.typeface = Typeface.createFromAsset(assets, "font/tvn.ttf")
        }
        val handler = Handler()

        //글자 크기 클릭 이벤트
        binding.tvMakingTextSizeVerySmall.setOnClickListener {
            binding.tvMakingTextSizeVerySmall.isSelected = true
            binding.tvMakingTextSizeSmall.isSelected = false
            binding.tvMakingTextSizeNormal.isSelected = false
            binding.tvMakingTextSizeBig.isSelected = false
            binding.tvText.setTextSize(Dimension.SP, 30.0f)
            text_size = 0

            handler.postDelayed({
                if (binding.tvText.width < screenWidth) {

                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    binding.llMakingText.layoutParams = params

                } else {
                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    binding.llMakingText.layoutParams = params
                }
            }, 1)
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            setAnim()

        }
        binding.tvMakingTextSizeSmall.setOnClickListener {
            binding.tvMakingTextSizeVerySmall.isSelected = false
            binding.tvMakingTextSizeSmall.isSelected = true
            binding.tvMakingTextSizeNormal.isSelected = false
            binding.tvMakingTextSizeBig.isSelected = false
            binding.tvText.setTextSize(Dimension.SP, 60.0f)
            text_size = 1
            handler.postDelayed({
                if (binding.tvText.width < screenWidth) {

                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    binding.llMakingText.layoutParams = params

                } else {
                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    binding.llMakingText.layoutParams = params
                }
            }, 1)
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            setAnim()
        }
        binding.tvMakingTextSizeNormal.setOnClickListener {
            binding.tvMakingTextSizeVerySmall.isSelected = false
            binding.tvMakingTextSizeSmall.isSelected = false
            binding.tvMakingTextSizeNormal.isSelected = true
            binding.tvMakingTextSizeBig.isSelected = false
            binding.tvText.setTextSize(Dimension.SP, 90.0f)
            text_size = 2
            handler.postDelayed(Runnable {
                if (binding.tvText.width < screenWidth) {

                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    binding.llMakingText.layoutParams = params

                } else {
                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    binding.llMakingText.layoutParams = params
                }
            }, 1)
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            setAnim()
        }
        binding.tvMakingTextSizeBig.setOnClickListener {
            binding.tvMakingTextSizeVerySmall.isSelected = false
            binding.tvMakingTextSizeSmall.isSelected = false
            binding.tvMakingTextSizeNormal.isSelected = false
            binding.tvMakingTextSizeBig.isSelected = true
            binding.tvText.setTextSize(Dimension.SP, 120.0f);
            text_size = 3
            handler.postDelayed(Runnable {
                if (binding.tvText.width < screenWidth) {

                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER
                    binding.llMakingText.layoutParams = params

                } else {
                    val params = binding.llMakingText.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.CENTER_VERTICAL
                    binding.llMakingText.layoutParams = params
                }
            }, 1)
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            setAnim()
        }

        //배경 색깔 선택
        binding.tvMakingColorBlack.setOnClickListener {
            binding.tvMakingColorBlack.isSelected = true
            binding.tvMakingColorBlue.isSelected = false
            binding.tvMakingColorDarkGreen.isSelected = false
            binding.tvMakingColorYellow.isSelected = false
            binding.tvMakingColorOrange.isSelected = false
            binding.tvMakingColorPink.isSelected = false
            binding.hsMakingtext.setBackgroundColor(Color.parseColor("#000000"))
            background_color = 0
        }
        binding.tvMakingColorBlue.setOnClickListener {
            binding.tvMakingColorBlack.isSelected = false
            binding.tvMakingColorBlue.isSelected = true
            binding.tvMakingColorDarkGreen.isSelected = false
            binding.tvMakingColorYellow.isSelected = false
            binding.tvMakingColorOrange.isSelected = false
            binding.tvMakingColorPink.isSelected = false
            binding.hsMakingtext.setBackgroundColor(Color.parseColor("#3c3eda"))
            background_color = 1
        }
        binding.tvMakingColorDarkGreen.setOnClickListener {
            binding.tvMakingColorBlack.isSelected = false
            binding.tvMakingColorBlue.isSelected = false
            binding.tvMakingColorDarkGreen.isSelected = true
            binding.tvMakingColorYellow.isSelected = false
            binding.tvMakingColorOrange.isSelected = false
            binding.tvMakingColorPink.isSelected = false
            binding.hsMakingtext.setBackgroundColor(Color.parseColor("#0191b6"))
            background_color = 2
        }
        binding.tvMakingColorYellow.setOnClickListener {
            binding.tvMakingColorBlack.isSelected = false
            binding.tvMakingColorBlue.isSelected = false
            binding.tvMakingColorDarkGreen.isSelected = false
            binding.tvMakingColorYellow.isSelected = true
            binding.tvMakingColorOrange.isSelected = false
            binding.tvMakingColorPink.isSelected = false
            binding.hsMakingtext.setBackgroundColor(Color.parseColor("#f9d80d"))
            background_color = 3
        }
        binding.tvMakingColorOrange.setOnClickListener {
            binding.tvMakingColorBlack.isSelected = false
            binding.tvMakingColorBlue.isSelected = false
            binding.tvMakingColorDarkGreen.isSelected = false
            binding.tvMakingColorYellow.isSelected = false
            binding.tvMakingColorOrange.isSelected = true
            binding.tvMakingColorPink.isSelected = false
            binding.hsMakingtext.setBackgroundColor(Color.parseColor("#ff7b17"))
            background_color = 4
        }
        binding.tvMakingColorPink.setOnClickListener {
            binding.tvMakingColorBlack.isSelected = false
            binding.tvMakingColorBlue.isSelected = false
            binding.tvMakingColorDarkGreen.isSelected = false
            binding.tvMakingColorYellow.isSelected = false
            binding.tvMakingColorOrange.isSelected = false
            binding.tvMakingColorPink.isSelected = true
            binding.hsMakingtext.setBackgroundColor(Color.parseColor("#f637f3"))
            background_color = 5
        }

        // 글자 색깔
        binding.tvMakingTextcolorWhite.setOnClickListener {
            binding.tvMakingTextcolorWhite.isSelected = true
            binding.tvMakingTextcolorBlue.isSelected = false
            binding.tvMakingTextcolorDarkGreen.isSelected = false
            binding.tvMakingTextcolorYellow.isSelected = false
            binding.tvMakingTextcolorOrange.isSelected = false
            binding.tvMakingTextcolorPink.isSelected = false
            binding.tvText.setTextColor(Color.parseColor("#FFFFFF"))
            text_color = 0
        }
        binding.tvMakingTextcolorBlue.setOnClickListener {
            binding.tvMakingTextcolorWhite.isSelected = false
            binding.tvMakingTextcolorBlue.isSelected = true
            binding.tvMakingTextcolorDarkGreen.isSelected = false
            binding.tvMakingTextcolorYellow.isSelected = false
            binding.tvMakingTextcolorOrange.isSelected = false
            binding.tvMakingTextcolorPink.isSelected = false
            binding.tvText.setTextColor(Color.parseColor("#3c3eda"))
            text_color = 1
        }
        binding.tvMakingTextcolorDarkGreen.setOnClickListener {
            binding.tvMakingTextcolorWhite.isSelected = false
            binding.tvMakingTextcolorBlue.isSelected = false
            binding.tvMakingTextcolorDarkGreen.isSelected = true
            binding.tvMakingTextcolorYellow.isSelected = false
            binding.tvMakingTextcolorOrange.isSelected = false
            binding.tvMakingTextcolorPink.isSelected = false
            binding.tvText.setTextColor(Color.parseColor("#0191b6"))
            text_color = 2
        }
        binding.tvMakingTextcolorYellow.setOnClickListener {
            binding.tvMakingTextcolorWhite.isSelected = false
            binding.tvMakingTextcolorBlue.isSelected = false
            binding.tvMakingTextcolorDarkGreen.isSelected = false
            binding.tvMakingTextcolorYellow.isSelected = true
            binding.tvMakingTextcolorOrange.isSelected = false
            binding.tvMakingTextcolorPink.isSelected = false
            binding.tvText.setTextColor(Color.parseColor("#f9d80d"))
            text_color = 3
        }
        binding.tvMakingTextcolorOrange.setOnClickListener {
            binding.tvMakingTextcolorWhite.isSelected = false
            binding.tvMakingTextcolorBlue.isSelected = false
            binding.tvMakingTextcolorDarkGreen.isSelected = false
            binding.tvMakingTextcolorYellow.isSelected = false
            binding.tvMakingTextcolorOrange.isSelected = true
            binding.tvMakingTextcolorPink.isSelected = false
            binding.tvText.setTextColor(Color.parseColor("#ff7b17"))
            text_color = 4
        }
        binding.tvMakingTextcolorPink.setOnClickListener {
            binding.tvMakingTextcolorWhite.isSelected = false
            binding.tvMakingTextcolorBlue.isSelected = false
            binding.tvMakingTextcolorDarkGreen.isSelected = false
            binding.tvMakingTextcolorYellow.isSelected = false
            binding.tvMakingTextcolorOrange.isSelected = false
            binding.tvMakingTextcolorPink.isSelected = true
            binding.tvText.setTextColor(Color.parseColor("#f637f3"))
            text_color = 5
        }
        // 글자 속도 선택
        binding.tvMakingTextSpeedFast.setOnClickListener {
            binding.tvMakingTextSpeedFast.isSelected = true
            binding.tvMakingTextSpeedNormal.isSelected = false
            binding.tvMakingTextSpeedSlow.isSelected = false
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            speed = 0
            setAnim()
        }
        binding.tvMakingTextSpeedNormal.setOnClickListener {
            binding.tvMakingTextSpeedFast.isSelected = false
            binding.tvMakingTextSpeedNormal.isSelected = true
            binding.tvMakingTextSpeedSlow.isSelected = false
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            speed = 1
            setAnim()

        }
        binding.tvMakingTextSpeedSlow.setOnClickListener {
            binding.tvMakingTextSpeedFast.isSelected = false
            binding.tvMakingTextSpeedNormal.isSelected = false
            binding.tvMakingTextSpeedSlow.isSelected = true
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            speed = 2
            setAnim()
        }
        //방향
        binding.ivMakingDirectionRight.setOnClickListener {
            binding.ivMakingDirectionRight.isSelected = true
            binding.tvMakingDirectionStop.isSelected = false
            binding.ivMakingDirectionLeft.isSelected = false
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            direction = 0
            setAnim()
        }
        binding.tvMakingDirectionStop.setOnClickListener {
            binding.ivMakingDirectionRight.isSelected = false
            binding.tvMakingDirectionStop.isSelected = true
            binding.ivMakingDirectionLeft.isSelected = false
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            direction = 1
            setAnim()


        }
        binding.ivMakingDirectionLeft.setOnClickListener {
            binding.ivMakingDirectionRight.isSelected = false
            binding.tvMakingDirectionStop.isSelected = false
            binding.ivMakingDirectionLeft.isSelected = true
            if (binding.tvText.animation != null) {
                binding.tvText.clearAnimation()
            }
            direction = 2
            setAnim()
        }

        //뒤로가기 버튼
        binding.ivMakingBackButton.setOnClickListener {
            finish()
        }
        // 확대 하기
        binding.ivMakingExpandButton.setOnClickListener {
            if (binding.edtMakingText.text.toString().isNotEmpty() || binding.tvText.text != "") {
                //포커스 문제 ....
                val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.showSoftInput(binding.edtMakingText, 0)

                startActivity<CheeringBoardActivity>(
                    "edt_making_text" to binding.tvText.text.toString(),
                    "text_size" to text_size,
                    "background_color" to background_color,
                    "text_color" to text_color,
                    "speed" to speed,
                    "direction" to direction,
                    "font" to font,
                    "effect0" to effect0,
                    "effect1" to effect1,
                    "effect2" to effect2,
                    "effect3" to effect3
                )
            } else {
                toast("메세지를 입력해주세요")
            }
        }
        super.onWindowFocusChanged(hasFocus)

    }

    fun setAnim(width: Int = 1, text: CharSequence = "") {
        val width = binding.tvText.width
        var text = binding.tvText.text
        //애니메이션 두개 넣기 set
        val set = AnimationSet(false)

        var animation =
            TranslateAnimation((screenWidth).toFloat(), (-(width).toFloat()), 0.0f, -0.0f)
        if (direction == 2) {
            if (-screenWidth.toFloat() < -(width).toFloat()) {
                animation = TranslateAnimation(
                    (screenWidth).toFloat(),
                    (-screenWidth.toFloat()),
                    0.0f,
                    -0.0f
                )
            } else
                animation =
                    TranslateAnimation((screenWidth).toFloat(), (-(width).toFloat()), 0.0f, -0.0f)
        } else if (direction == 0) {
            if (-screenWidth.toFloat() < -(width).toFloat()) {
                animation =
                    TranslateAnimation(-screenWidth.toFloat(), (screenWidth).toFloat(), 0.0f, -0.0f)
            } else
                animation =
                    TranslateAnimation((-(width).toFloat()), (screenWidth).toFloat(), 0.0f, -0.0f)
        } else if (direction == 1) {
            animation = TranslateAnimation(0.0f, 0.0f, 0.0f, -0.0f)
        }

        animation.interpolator = AnimationUtils.loadInterpolator(
            this,
            android.R.anim.linear_interpolator
        )


        if (speed == 0) {
            animation.duration = (((screenWidth).toLong() + (width).toLong()) * 0.8).toLong()

        } else if (speed == 1) {
            animation.duration = ((screenWidth).toLong() + (width).toLong())
        } else if (speed == 2) {
            animation.duration = (((screenWidth).toLong() + (width).toLong()) * 1.2).toLong()
        }

        animation.repeatCount = -1

        if (effect0 == 1) {
            val alphaAnim = AlphaAnimation(0F, 1.0F)
            alphaAnim.duration = 600
            alphaAnim.repeatCount = -1
            set.addAnimation(alphaAnim)
        }

        set.addAnimation(animation)
        binding.llMakingText.animation = set
        binding.llMakingText.animation.start()
    }

    override fun initView() {
        bannerCount = intent.getIntExtra("bannerCount", 0)
        // DEFAULT 값 설정

        binding.tvMakingTextSizeNormal.isSelected = true
        binding.tvMakingTextSpeedNormal.isSelected = true
        binding.tvMakingTextcolorWhite.isSelected = true
        binding.ivMakingDirectionLeft.isSelected = true
        binding.tvMakingColorBlack.isSelected = true
        binding.tvMakingTextFontNanum.isSelected = true

        binding.tvMakingTextFontHasuwon.typeface = Typeface.createFromAsset(
            assets,
            "font/hansuwon.ttf"
        )
        binding.tvMakingTextFontUljiro.typeface = Typeface.createFromAsset(
            assets,
            "font/uljiro.ttf"
        )
        binding.tvMakingTextFontHanna.typeface = Typeface.createFromAsset(
            assets,
            "font/hanna.ttf"
        )
        binding.tvMakingTextFontYanolza.typeface = Typeface.createFromAsset(
            assets,
            "font/yanolza.ttf"
        )
        binding.tvMakingTextFontJua.typeface = Typeface.createFromAsset(
            assets,
            "font/jua.ttf"
        )
        binding.tvMakingTextFontTvn.typeface = Typeface.createFromAsset(
            assets,
            "font/tvn.ttf"
        )
    }
}