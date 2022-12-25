package com.sabuzak.yeonamplace.cheerupforyou.presentation.home

import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityHomeBinding
import com.sabuzak.yeonamplace.cheerupforyou.presentation.makingcheerup.MakingCheerUpTextActivity
import com.sabuzak.yeonamplace.cheerupforyou.presentation.roadtokingdom.RoadToKingdomCheerUpActivity
import com.sabuzak.yeonamplace.cheerupforyou.presentation.popup.RequestReviewPopUpActivity
import com.sabuzak.yeonamplace.cheerupforyou.presentation.popup.delete.DeleteBannerPopUpActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home), BannerListener {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var mAdView: AdView

    override fun initView() {
        binding.viewModel = viewModel

        binding.bannerRecyclerview.adapter = BannerRecyclerViewAdapter(this)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAllBanner().collectLatest { banners ->
                    (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).submitList(
                        banners
                    )
                    binding.saveCount.text =
                        " " + (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount + "/5"
                }
            }
        }
        MobileAds.initialize(this) {}
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        setObserveUserEvent()
    }

    override fun chooseDeleteBanner(index: Int) {
        val intent = Intent(this, DeleteBannerPopUpActivity::class.java)
        intent.putExtra("bannerIdx", index)
        this.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    override fun choosePlayBanner(banner: Banner) {
        val intent = Intent(this, MakingCheerUpTextActivity::class.java)
        intent.putExtra("edt_making_text", banner.text)
        intent.putExtra("text_size", banner.size)
        intent.putExtra("background_color", banner.background)
        intent.putExtra("text_color", banner.color)
        intent.putExtra("speed", banner.speed)
        intent.putExtra("direction", banner.direction)
        intent.putExtra("font", banner.font)
        intent.putExtra("effect0", banner.blink)
        intent.putExtra("effect1", banner.outline)
        intent.putExtra("effect2", banner.shining)
        intent.putExtra("effect3", banner.shadow)
        intent.putExtra("idx", banner.idx)
        this.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

    private fun navigateToMakeCheerUpTextView() {
        Log.d("navigateToRoad", "액티비티 navigateToMakeCheerUpTextView 호출")
        val intent = Intent(this, MakingCheerUpTextActivity::class.java)
        intent.putExtra(
            "bannerCount",
            (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount
        )
        startActivity(intent)
    }

    private fun navigateToRoadToKingdomView() {
        val intent = Intent(this, RoadToKingdomCheerUpActivity::class.java)
        startActivity(intent)
    }

    private fun shareToFriend() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "https://play.google.com/store/search?q=%EC%9D%91%EC%9B%90%ED%95%B4&c=apps"
        )
        startActivity(Intent.createChooser(intent, "친구에게 공유하기"))
    }

    private fun navigateToRequestPopUpView() {
        val intent = Intent(this, RequestReviewPopUpActivity::class.java)
        startActivity(intent)
    }

    private fun setObserveUserEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.eventFlow.collectLatest { event ->
                    when (val result = event.getContentIfNotHandled()) {
                        is HomeEvent.NavigateToMakeCheerUpTextView -> {
                            navigateToMakeCheerUpTextView()
                        }
                        is HomeEvent.NavigateToRoadToKingdomView -> {
                            navigateToRoadToKingdomView()
                        }
                        is HomeEvent.NavigateToRequestPopUpView -> {
                            navigateToRequestPopUpView()
                        }
                        is HomeEvent.ShareToFriend -> {
                            shareToFriend()
                        }
                        is HomeEvent.RemoveBanner -> {
                            if (result.removeFlag) {
                                for (i in 0..(binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                                    (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                                        i,
                                        "active"
                                    )
                                }
                            } else {
                                for (i in 0..(binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                                    (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                                        i,
                                        "disable"
                                    )
                                }
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}
