package com.sabuzak.yeonamplace.cheerupforyou.presentation.popup

import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.data.dataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.data.repository.BannerRepository
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityDeleteBannerPopUpBinding
import kotlinx.coroutines.runBlocking

class DeleteBannerPopUpActivity :
    BaseActivity<ActivityDeleteBannerPopUpBinding>(R.layout.activity_delete_banner_pop_up) {
    private lateinit var bannerRepository: BannerRepository
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun initView() {
        binding.tvDeleteConfirm.setOnClickListener {
            val bannerIdx = intent.getIntExtra("bannerIdx", -1)
            if (bannerIdx != -1) {
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
