package com.sabuzak.yeonamplace.cheerupforyou.presentation.popup

import android.os.Handler
import android.widget.Toast
import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityLoadingSavePopUpBinding

class LoadingSavePopUpActivity : BaseActivity<ActivityLoadingSavePopUpBinding>(R.layout.activity_loading_save_pop_up) {

    override fun initView() {
        binding.avi.smoothToShow()
        val handler = Handler()
        handler.postDelayed({
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }, 2000)
    }
}
