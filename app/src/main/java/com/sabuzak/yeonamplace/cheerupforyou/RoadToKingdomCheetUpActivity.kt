package com.sabuzak.yeonamplace.cheerupforyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.sabuzak.yeonamplace.cheerupforyou.Adapter.CheerUpViewRecyclerViewAdapter
import com.sabuzak.yeonamplace.cheerupforyou.data.CheerUpViewData


class RoadToKingdomCheetUpActivity : AppCompatActivity() {
    lateinit var cheerUpViewRecyclerViewAdapter: CheerUpViewRecyclerViewAdapter
    var arrayList: ArrayList<CheerUpViewData> = ArrayList()
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_road_to_kingdom_cheet_up)
        var iv_road_back = findViewById<ImageView>(R.id.iv_road_back)
        var rl_road_to_kingdom=  findViewById<RecyclerView>(R.id.rl_road_to_kingdom)
        var ad_roadtokingdom = findViewById<AdView>(R.id.ad_roadtokingdom)

        iv_road_back.setOnClickListener {
            finish()
        }

        arrayList.add(CheerUpViewData("골든차일드 응원해", 3,6,0,2,1,5,1,0,1,0))
        arrayList.add(CheerUpViewData("더보이즈 응원해", 3,7,0,2,1,5,1,0,1,0))
        arrayList.add(CheerUpViewData("베리베리 응원해", 3,8,0,2,1,5,1,0,1,0))
        arrayList.add(CheerUpViewData("온앤오프 응원해", 3,9,0,2,1,5,1,0,1,0))
        arrayList.add(CheerUpViewData("원어스 응원해", 3,10,0,2,1,5,1,0,1,0))
        arrayList.add(CheerUpViewData("펜타곤 응원해", 3,11,0,2,1,5,1,0,1,0))
        arrayList.add(CheerUpViewData("TOO 응원해", 3,12,0,2,1,5,1,0,1,0))

        cheerUpViewRecyclerViewAdapter = CheerUpViewRecyclerViewAdapter(this, arrayList)
        rl_road_to_kingdom.adapter = cheerUpViewRecyclerViewAdapter
        rl_road_to_kingdom.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        MobileAds.initialize(this) {}
        mAdView = ad_roadtokingdom
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}
