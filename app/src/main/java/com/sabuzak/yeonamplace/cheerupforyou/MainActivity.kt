package com.sabuzak.yeonamplace.cheerypforyou

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.AlwaysOnHotwordDetector
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.sabuzak.yeonamplace.cheerypforyou.DataBase.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.banner_recyclerview
import kotlinx.android.synthetic.main.banner_recyclerview_item.view.*
import kotlinx.coroutines.delay
import org.jetbrains.anko.image
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var db:AppDatabase? =  null
    private var isRemoveActive = false
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_main_make_new_cheerup_text.setOnClickListener {
            startActivity<MakingCheerUpTextActivity>()
        }
        banner_recyclerview.adapter = BannerRecyclerViewAdapter()
        banner_recyclerview.layoutManager = LinearLayoutManager(this)
        remove_button.setOnClickListener {
            isRemoveActive = true
           for(i in 0.. (banner_recyclerview.adapter as BannerRecyclerViewAdapter).itemCount)
           {
               (banner_recyclerview.adapter as BannerRecyclerViewAdapter).notifyItemChanged(i,"active")
           }

        }



        MobileAds.initialize(this) {}
        mAdView = adView
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "banner-db"
        ).build()


    }


    inner class BannerRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


        /**
         * 지금은 String 객체로 임시 생성
         * 추후 Banner 객체 정의 후 변경 예정
         */
        var bannerArray = arrayOf("응원해","우리를","모두다")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view = LayoutInflater.from(parent?.context).inflate(R.layout.banner_recyclerview_item,parent,false)

            return BannerViewHolder(view)
        }

        override fun getItemCount(): Int {

            return bannerArray.size
        }


        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        }

        /*
         *
         */
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int,payloads: MutableList<Any>) {
            /**
             * Banner 객체 정의 후에 제대로 세팅 할것
             * 지금은 임시로 String 대체
             */
            Log.d("payload",payloads.toString())
            if(payloads.size > 0) {
                if (payloads[0] == "active") {
                    holder.itemView.delete_image.visibility = View.VISIBLE
                } else if (payloads[0] == "disable") {
                    holder.itemView.delete_image.visibility = View.GONE
                }
                else if(payloads[0] == "remove")
                {
                    holder.itemView.delete_image.setImageResource(R.drawable.ic_delete_red)
                }
            }
            else{

                holder.itemView.banner_text.setText(bannerArray[position])
                holder.itemView.delete_image.onClick {



                    //delay(1000)

                    /**
                     * TODO 2020.05.02 이미지 리소스 비교하여 빨강이랑 하얀색 구분
                     */

                    holder.itemView.delete_image.setImageResource(R.drawable.ic_delete_red)
                    //holder.itemView.delete_image.visibility = View.GONE

                        /**
                         * TODO  2020.04팝업창 띄우기 해야됨
                         */



                }

            }


        }





        inner class BannerViewHolder(view: View):RecyclerView.ViewHolder(view){

        }



    }
}
