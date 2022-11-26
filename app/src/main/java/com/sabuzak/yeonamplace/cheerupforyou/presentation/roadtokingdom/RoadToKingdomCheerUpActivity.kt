package com.sabuzak.yeonamplace.cheerupforyou.presentation.roadtokingdom

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.sabuzak.yeonamplace.cheerupforyou.BaseActivity
import com.sabuzak.yeonamplace.cheerupforyou.data.model.CheerUpViewData
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.databinding.ActivityRoadToKingdomCheerUpBinding

class RoadToKingdomCheerUpActivity :
    BaseActivity<ActivityRoadToKingdomCheerUpBinding>(R.layout.activity_road_to_kingdom_cheer_up) {
    lateinit var cheerUpViewRecyclerViewAdapter: CheerUpViewRecyclerViewAdapter
    var arrayList: ArrayList<CheerUpViewData> = ArrayList()
    lateinit var mAdView: AdView

    override fun initView() {

        binding.ivRoadBack.setOnClickListener {
            finish()
        }
        arrayList.add(CheerUpViewData("골든차일드 응원해", 3, 6, 0, 2, 1, 5, 1, 0, 1, 0))
        arrayList.add(CheerUpViewData("더보이즈 응원해", 3, 7, 0, 2, 1, 5, 1, 0, 1, 0))
        arrayList.add(CheerUpViewData("베리베리 응원해", 3, 8, 0, 2, 1, 5, 1, 0, 1, 0))
        arrayList.add(CheerUpViewData("온앤오프 응원해", 3, 9, 0, 2, 1, 5, 1, 0, 1, 0))
        arrayList.add(CheerUpViewData("원어스 응원해", 3, 10, 0, 2, 1, 5, 1, 0, 1, 0))
        arrayList.add(CheerUpViewData("펜타곤 응원해", 3, 11, 0, 2, 1, 5, 1, 0, 1, 0))
        arrayList.add(CheerUpViewData("TOO 응원해", 3, 12, 0, 2, 1, 5, 1, 0, 1, 0))

        cheerUpViewRecyclerViewAdapter = CheerUpViewRecyclerViewAdapter(this, arrayList)
        binding.rlRoadToKingdom.adapter = cheerUpViewRecyclerViewAdapter
        binding.rlRoadToKingdom.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        MobileAds.initialize(this) {}
        mAdView = binding.adRoadtokingdom
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}
