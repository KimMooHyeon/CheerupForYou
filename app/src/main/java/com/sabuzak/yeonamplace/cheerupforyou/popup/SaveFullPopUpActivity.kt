package com.sabuzak.yeonamplace.cheerupforyou.popup

import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivitySaveFullPopUpBinding


class SaveFullPopUpActivity : BaseActivity<ActivitySaveFullPopUpBinding>(R.layout.activity_save_full_pop_up) {
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }

    override fun initView() {
        binding.tvSaveConfirm.setOnClickListener {
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
