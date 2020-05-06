package com.sabuzak.yeonamplace.cheerupforyou.Adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.popup.DeleteBannerPopUpActivity
import kotlinx.android.synthetic.main.banner_recyclerview_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class BannerRecyclerViewAdapter internal constructor(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * 지금은 String 객체로 임시 생성
     * 추후 Banner 객체 정의 후 변경 예정
     */
    private val c = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var bannerArray = emptyList<Banner>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = inflater.inflate(R.layout.banner_recyclerview_item,parent,false)

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
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
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
        }
        else{

            holder.itemView.banner_text.setText(bannerArray[position].text)
            holder.itemView.delete_image.onClick {
                /**
                 * TODO 2020.05.02 팝업 띄우기
                 */
                holder.itemView.delete_image.setImageResource(R.drawable.ic_delete_red)
                var intent = Intent(c, DeleteBannerPopUpActivity::class.java)
                intent.putExtra("bannerIdx",bannerArray[position].idx)
                c.startActivity(intent)

            }

        }


    }


    internal fun setBanners(banners: List<Banner>){
        this.bannerArray = banners
        notifyDataSetChanged()
    }


    inner class BannerViewHolder(view: View): RecyclerView.ViewHolder(view){


    }



}