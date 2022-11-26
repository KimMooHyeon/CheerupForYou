package com.sabuzak.yeonamplace.cheerupforyou.popup

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.wang.avi.AVLoadingIndicatorView


class LoadingSavePopUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_save_pop_up)
        var avi = findViewById<AVLoadingIndicatorView>(R.id.avi)
        avi.smoothToShow()
        val handler = Handler()
        handler.postDelayed(Runnable {
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            Toast.makeText(this,"저장이 완료되었습니다.",Toast.LENGTH_SHORT)
        }, 2000)


    }
 /*   fun startAnim() {
    avi.smoothToShow()
    }*/

   /* fun stopAnim() {
         avi.smoothToHide()
    }*/

}
