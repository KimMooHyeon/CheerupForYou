package com.sabuzak.yeonamplace.cheerupforyou.popup

import android.content.Intent
import android.net.Uri
import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityRequestReviewPopUpBinding


class RequestReviewPopUpActivity :
    BaseActivity<ActivityRequestReviewPopUpBinding>(R.layout.activity_request_review_pop_up) {
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }

    override fun initView() {
        binding.ivRequestCancle.setOnClickListener {
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        binding.tvRequestConfirm.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("https://play.google.com/store/search?q=%EC%9D%91%EC%9B%90%ED%95%B4&c=apps")
            startActivity(intent);
        }
    }
}
