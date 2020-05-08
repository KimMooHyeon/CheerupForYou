package com.sabuzak.yeonamplace.cheerupforyou

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.service.voice.AlwaysOnHotwordDetector
import android.util.Log
import android.view.Gravity
import android.view.Gravity.RIGHT
import android.view.Gravity.TOP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.sabuzak.yeonamplace.cheerupforyou.Adapter.BannerRecyclerViewAdapter
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.AppDatabase
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.ViewModel.BannerViewModel
import com.sabuzak.yeonamplace.cheerupforyou.popup.DeleteBannerPopUpActivity
import com.sabuzak.yeonamplace.cheerupforyou.MakingCheerUpTextActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.popup.RequestTempletePopUpActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.banner_recyclerview_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    private var db: AppDatabase? =  null
    private var isRemoveActive = false
    private lateinit var bannerViewModel: BannerViewModel

    private  val REQUEST_SAVE = 1


    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ll_main_loadtokingdom.setOnClickListener {

            isRemoveActive = false
            for (i in 0..(banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                (banner_recyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                    i,
                    "disable"
                )
            }

            startActivity<RoadToKingdomCheetUpActivity>()
        }

        req_image.setOnClickListener {

            isRemoveActive = false
            for (i in 0..(banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                (banner_recyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                    i,
                    "disable"
                )
            }

            startActivity<RequestTempletePopUpActivity>()
            this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }



        user_plus_image.setOnClickListener {
            user_plus_image.isClickable=false
            val handler = Handler()
            handler.postDelayed(Runnable {
                user_plus_image.isClickable=true
            }, 2000)
            var intent = Intent(Intent.ACTION_SEND)

            intent.setType("text/plain")

            var text = "https://play.google.com/store/search?q=%EC%9D%91%EC%9B%90%ED%95%B4&c=apps"
            intent.putExtra(Intent.EXTRA_TEXT, text)

            var chooser = Intent.createChooser(intent, "친구에게 공유하기")
            startActivity(chooser)


        }





        banner_recyclerview.adapter = BannerRecyclerViewAdapter(ctx = this.applicationContext)
        banner_recyclerview.layoutManager = LinearLayoutManager(this)
        bannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)

        bannerViewModel.allBanner.observe(this, Observer {
            banners-> banners?.let{
            (banner_recyclerview.adapter as BannerRecyclerViewAdapter).setBanners(it)
            save_count.text = "저장함 "+ (banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount+"/5"
        }
        })


        btn_main_make_new_cheerup_text.setOnClickListener {

            isRemoveActive = false
            for (i in 0..(banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                (banner_recyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                    i,
                    "disable"
                )
            }

            var intent = Intent(this, MakingCheerUpTextActivity::class.java)
            intent.putExtra("bannerCount",
                (banner_recyclerview.adapter as BannerRecyclerViewAdapter).bannerArray.size)



            startActivity(intent)



        }


        /**
         * 2020.05.04 최선필
         * 삭제 버튼 클릭 리스너 구현
         * 한번 누르면 하위 배너 삭제 버튼 활성화
         * 다시 누르면 하위 배너 삭제 버튼 비활성
         */
        remove_button.setOnClickListener {

            if(!isRemoveActive) {
                isRemoveActive = true
                for (i in 0..(banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                    (banner_recyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                        i,
                        "active"
                    )
                }
            }
            else
            {
                isRemoveActive = false
                for (i in 0..(banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount) {
                    (banner_recyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(
                        i,
                        "disable"
                    )
                }
            }

        }



        MobileAds.initialize(this) {}
        mAdView = adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


    }

}
