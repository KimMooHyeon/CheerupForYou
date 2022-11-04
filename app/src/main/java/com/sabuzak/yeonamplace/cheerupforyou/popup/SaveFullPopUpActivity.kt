package com.sabuzak.yeonamplace.cheerupforyou.popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sabuzak.yeonamplace.cheerupforyou.R


class SaveFullPopUpActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_full_pop_up)
        var tv_save_confirm = findViewById<TextView>(R.id.tv_save_confirm)
        tv_save_confirm.setOnClickListener {
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
