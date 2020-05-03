package com.sabuzak.yeonamplace.cheerupforyou.popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sabuzak.yeonamplace.cheerupforyou.R
import kotlinx.android.synthetic.main.activity_delete_banner_pop_up.*

class DeleteBannerPopUpActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_banner_pop_up)
        tv_delete_confirm.setOnClickListener {
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
