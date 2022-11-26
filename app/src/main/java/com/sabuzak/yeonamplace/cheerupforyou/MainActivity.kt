package com.sabuzak.yeonamplace.cheerupforyou

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.sabuzak.yeonamplace.cheerupforyou.Adapter.BannerRecyclerViewAdapter
import com.sabuzak.yeonamplace.cheerupforyou.dataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.dataBase.ViewModel.BannerViewModel
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityMainBinding
import com.sabuzak.yeonamplace.cheerupforyou.popup.RequestTempletePopUpActivity
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var db: AppDatabase? = null
    private var isRemoveActive = false
    private lateinit var bannerViewModel: BannerViewModel
    private val REQUEST_SAVE = 1
    lateinit var mAdView: AdView

    override fun initView() {
        binding.llMainLoadtokingdom.setOnClickListener {
            isRemoveActive = false
            for (i in 0..(binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                    i,
                    "disable"
                )
            }
            startActivity<RoadToKingdomCheetUpActivity>()
        }

        binding.reqImage.setOnClickListener {

            isRemoveActive = false
            for (i in 0..(binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                    i,
                    "disable"
                )
            }
            startActivity<RequestTempletePopUpActivity>()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }


        binding.userPlusImage.setOnClickListener {
            binding.userPlusImage.isClickable = false
            val handler = Handler()
            handler.postDelayed(Runnable {
                binding.userPlusImage.isClickable = true
            }, 2000)
            var intent = Intent(Intent.ACTION_SEND)

            intent.setType("text/plain")

            var text = "https://play.google.com/store/search?q=%EC%9D%91%EC%9B%90%ED%95%B4&c=apps"
            intent.putExtra(Intent.EXTRA_TEXT, text)

            var chooser = Intent.createChooser(intent, "친구에게 공유하기")
            startActivity(chooser)

        }


        binding.bannerRecyclerview.adapter =
            BannerRecyclerViewAdapter(ctx = this.applicationContext)
        binding.bannerRecyclerview.layoutManager = LinearLayoutManager(this)
        bannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)

        bannerViewModel.allBanner.observe(this, Observer { banners ->
            banners?.let {
                (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).setBanners(it)
                binding.saveCount.text =
                    " " + (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount + "/5"
            }
        })

        binding.btnMainMakeNewCheerupText.setOnClickListener {
            isRemoveActive = false
            for (i in 0..(binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                    i,
                    "disable"
                )
            }

            var intent = Intent(this, MakingCheerUpTextActivity::class.java)
            intent.putExtra(
                "bannerCount",
                (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).bannerArray.size
            )
            startActivity(intent)
        }

        /**
         * 2020.05.04 최선필
         * 삭제 버튼 클릭 리스너 구현
         * 한번 누르면 하위 배너 삭제 버튼 활성화
         * 다시 누르면 하위 배너 삭제 버튼 비활성
         */
        binding.removeButton.setOnClickListener {
            if (!isRemoveActive) {
                isRemoveActive = true
                for (i in 0..(binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                    (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                        i,
                        "active"
                    )
                }
            } else {
                isRemoveActive = false
                for (i in 0..(binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                    (binding.bannerRecyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                        i,
                        "disable"
                    )
                }
            }

        }


        MobileAds.initialize(this) {}
        mAdView = binding.adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}
