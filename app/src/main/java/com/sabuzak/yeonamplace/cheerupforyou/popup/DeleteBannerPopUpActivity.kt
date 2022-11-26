package com.sabuzak.yeonamplace.cheerupforyou.popup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sabuzak.yeonamplace.cheerupforyou.dataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.dataBase.Repository.BannerRepository
import com.sabuzak.yeonamplace.cheerupforyou.R
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
        var tv_delete_confirm = findViewById<TextView>(R.id.tv_delete_confirm)
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
