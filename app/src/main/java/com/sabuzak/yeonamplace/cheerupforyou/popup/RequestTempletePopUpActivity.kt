package com.sabuzak.yeonamplace.cheerupforyou.popup

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sabuzak.yeonamplace.cheerupforyou.R
import kotlinx.android.synthetic.main.activity_request_templete_pop_up.*

class RequestTempletePopUpActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_templete_pop_up)
        iv_request_cancle.setOnClickListener {
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
        tv_request_confirm.setOnClickListener {
              var intent =  Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/search?q=%EC%9D%91%EC%9B%90%ED%95%B4&c=apps"))
                startActivity(intent);
        }
    }
}
