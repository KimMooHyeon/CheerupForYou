package com.sabuzak.yeonamplace.cheerypforyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sabuzak.yeonamplace.cheerypforyou.model.Banner
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_main.view.banner_recyclerview
import kotlinx.android.synthetic.main.banner_recyclerview_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        banner_recyclerview.adapter = BannerRecyclerViewAdapter()
        banner_recyclerview.layoutManager = LinearLayoutManager(this)




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
            /**
             * Banner 객체 정의 후에 제대로 세팅 할것
             * 지금은 임시로 String 대체
             */

            holder.itemView.banner_text.setText(bannerArray[position])
        }


        inner class BannerViewHolder(view: View):RecyclerView.ViewHolder(view){

        }

    }
}
