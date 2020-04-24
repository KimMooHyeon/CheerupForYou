package com.sabuzak.yeonamplace.cheerypforyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_making_cheer_up_text.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MakingCheerUpTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_making_cheer_up_text)

        tv_making_text_size_very_small.isPressed=true
        tv_making_text_size_very_small.isPressed=true
      //  tv_making_color_pink.isPressed=true




        //뒤로가기 버튼
        iv_making_back_button.setOnClickListener {
            finish()
        }

        // 확대 하기
        iv_making_expand_button.setOnClickListener {
            if (edt_making_text.text.toString().isNotEmpty()){
                startActivity<CheerUpViewActivity>("edt_making_text" to edt_making_text.text.toString())
            }else {
                toast("메세지를 입력해주세요")
            }

        }


    }
}
