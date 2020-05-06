package com.sabuzak.yeonamplace.cheerupforyou.popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Repository.BannerRepository
import com.sabuzak.yeonamplace.cheerupforyou.R
import kotlinx.android.synthetic.main.activity_delete_banner_pop_up.*
import kotlinx.android.synthetic.main.activity_making_cheer_up_text.*
import kotlinx.coroutines.runBlocking

class DeleteBannerPopUpActivity : AppCompatActivity() {
    private lateinit var bannerRepository:BannerRepository
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_banner_pop_up)
        tv_delete_confirm.setOnClickListener {


                var bannerIdx = intent.getIntExtra("bannerIdx",-1)
                if (bannerIdx != -1)
                {
                    val bannerDao = AppDatabase.getDatabase(application).bannerDao()
                    bannerRepository = BannerRepository(bannerDao)
                        runBlocking {

                            bannerRepository.deleteByIdx(bannerIdx)
                        }


                }


            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
