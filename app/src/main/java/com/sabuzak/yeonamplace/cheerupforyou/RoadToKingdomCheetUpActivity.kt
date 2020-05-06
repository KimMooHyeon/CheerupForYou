package com.sabuzak.yeonamplace.cheerupforyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.sabuzak.yeonamplace.cheerupforyou.Adapter.CheerUpViewRecyclerViewAdapter
import com.sabuzak.yeonamplace.cheerupforyou.Data.CheerUpViewData
import kotlinx.android.synthetic.main.activity_road_to_kingdom_cheet_up.*

class RoadToKingdomCheetUpActivity : AppCompatActivity() {
    lateinit var cheerUpViewRecyclerViewAdapter: CheerUpViewRecyclerViewAdapter
    var arrayList: ArrayList<CheerUpViewData> = ArrayList()
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_road_to_kingdom_cheet_up)

        iv_road_back.setOnClickListener {
            finish()
        }

        arrayList.add(CheerUpViewData("Test1", 3,0,1,1,2,1,1,1,0,1))
        arrayList.add(CheerUpViewData("Test2 Test2 Test2 Test2", 2,3,0,0,1,5,0,0,0,0))
        arrayList.add(CheerUpViewData("Test3", 0,2,5,2,2,2,0,0,1,0))
        arrayList.add(CheerUpViewData("Test4", 1,1,3,1,1,3,1,0,0,1))
        arrayList.add(CheerUpViewData("Test5", 2,3,1,0,0,4,0,1,1,0))
        arrayList.add(CheerUpViewData("Test3", 0,2,5,2,2,2,0,0,1,0))
        arrayList.add(CheerUpViewData("Test4", 1,1,3,1,1,3,1,0,0,1))
        arrayList.add(CheerUpViewData("Test5 Test5Test5 Test5 Test5", 2,3,1,0,0,4,0,1,1,0))
        cheerUpViewRecyclerViewAdapter = CheerUpViewRecyclerViewAdapter(this, arrayList)
        rl_road_to_kingdom.adapter = cheerUpViewRecyclerViewAdapter
        rl_road_to_kingdom.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        MobileAds.initialize(this) {}
        mAdView = ad_roadtokingdom
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

    }
}
