package com.sabuzak.yeonamplace.cheerupforyou.popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.R
import kotlinx.android.synthetic.main.activity_delete_banner_pop_up.*
import kotlinx.coroutines.runBlocking

class DeleteBannerPopUpActivity : AppCompatActivity() {
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_banner_pop_up)
        tv_delete_confirm.setOnClickListener {

            var banner = intent.getIntExtra("banner",-1)
            var db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "banner-db"
            ).build()

            runBlocking {

                var banner = intent.getIntExtra("bannerIdx",-1)
                if (banner != -1)
                {
                    db.bannerDao().deleteBannerByIdx(banner)
                }
            }

            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
