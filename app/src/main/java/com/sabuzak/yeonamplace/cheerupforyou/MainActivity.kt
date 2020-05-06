package com.sabuzak.yeonamplace.cheerupforyou

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Gravity.RIGHT
import android.view.Gravity.TOP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.banner_recyclerview_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    private var isRemoveActive = false
    private lateinit var bannerViewModel: BannerViewModel

    private  val REQUEST_SAVE = 1


    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_main_make_new_cheerup_text.setOnClickListener {
            startActivity<MakingCheerUpTextActivity>()
        }
        banner_recyclerview.adapter = BannerRecyclerViewAdapter(context = this.applicationContext)
        banner_recyclerview.layoutManager = LinearLayoutManager(this)
        bannerViewModel = ViewModelProvider(this).get(BannerViewModel::class.java)

        bannerViewModel.allBanner.observe(this, Observer {
            banners-> banners?.let{

            (banner_recyclerview.adapter as BannerRecyclerViewAdapter).setBanners(it)
        }
        })
        save_count.text = "저장함 "+ (banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount+"/5"
        /**
         * 2020.05.04 최선필
         * 삭제 버튼 클릭 리스너 구현
         * 한번 누르면 하위 배너 삭제 버튼 활성화
         * 다시 누르면 하위 배너 삭제 버튼 비활성
         */
        remove_button.setOnClickListener {

            if(isRemoveActive == false) {
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
