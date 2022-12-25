package com.sabuzak.yeonamplace.cheerupforyou.presentation.popup.delete

import androidx.activity.viewModels
import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityDeleteBannerPopUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class DeleteBannerPopUpActivity :
    BaseActivity<ActivityDeleteBannerPopUpBinding>(R.layout.activity_delete_banner_pop_up) {
    private val viewModel: DeleteViewModel by viewModels()
    override fun onBackPressed() {
        finish()
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun initView() {
        binding.tvDeleteConfirm.setOnClickListener {
            val bannerIdx = intent.getIntExtra("bannerIdx", -1)
            if (bannerIdx != -1) {
                runBlocking {
                    viewModel.deleteByIdx(bannerIdx)
                }
            }
            finish()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }
    }
}
