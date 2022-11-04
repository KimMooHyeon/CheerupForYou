package com.sabuzak.yeonamplace.cheerupforyou.Adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner
import com.sabuzak.yeonamplace.cheerupforyou.MakingCheerUpTextActivity
import com.sabuzak.yeonamplace.cheerupforyou.OutlineTextView
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.popup.DeleteBannerPopUpActivity
import org.jetbrains.anko.sdk27.coroutines.onClick

class BannerRecyclerViewAdapter(var ctx: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * 지금은 String 객체로 임시 생성
     * 추후 Banner 객체 정의 후 변경 예정
     */
    private val c = ctx
    private val inflater: LayoutInflater = LayoutInflater.from(ctx)
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
        var delete_image =holder.itemView.findViewById<ImageView>(R.id.delete_image)
        var banner_text =holder.itemView.findViewById<OutlineTextView>(R.id.banner_text)
        var hs_rv_main_item =holder.itemView.findViewById<HorizontalScrollView>(R.id.hs_rv_main_item)

        Log.d("payload",payloads.toString())
        if(payloads.size > 0) {
            if (payloads[0] == "active") {

                delete_image.visibility = View.VISIBLE
            } else if (payloads[0] == "disable") {
                delete_image.setImageResource(R.drawable.ic_delete)
                delete_image.visibility = View.GONE
            }
        }
        else{

            banner_text.setText(bannerArray[position].text)
            //효과 받기

            if(bannerArray[position].outline!! ) {
                //있는거
                banner_text.setStroke(true)
                banner_text.draw(Canvas())
            }

            if(bannerArray[position].shining!!) {
                banner_text.setShadowLayer(15.0f,0.0f,0.0f, Color.parseColor("#ffffff"))
            }
            if(bannerArray[position].shadow!!) {
                banner_text.setShadowLayer(4.0f,8.0f,3.0f, Color.parseColor("#2AEFF5"))
            }


            // 폰트 받기
            if(bannerArray[position].font==0){
                banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/nanum.ttf"))
            }else if (bannerArray[position].font==1){
                banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/hansuwon.ttf"))
            }else if (bannerArray[position].font==2){
                banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/uljiro.ttf"))
            }else if (bannerArray[position].font==3){
                banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/hanna.ttf"))
            }else if (bannerArray[position].font==4){
                banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/yanolza.ttf"))
            }else if (bannerArray[position].font==5){
                banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/jua.ttf"))
            }else if (bannerArray[position].font==6){
                banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/tvn.ttf"))
            }


            // 글자 색 받기
            if(bannerArray[position].color ==0 ) {
                banner_text.setTextColor(Color.parseColor("#ffffff"))
            }else if(bannerArray[position].color ==1 ) {
                banner_text.setTextColor(Color.parseColor("#3c3eda"))
            }else if(bannerArray[position].color ==2 ) {
                banner_text.setTextColor(Color.parseColor("#0191b6"))
            }else if(bannerArray[position].color ==3 ) {
                banner_text.setTextColor(Color.parseColor("#f9d80d"))
            }else if(bannerArray[position].color ==4 ) {
                banner_text.setTextColor(Color.parseColor( "#ff7b17"))
            }else if(bannerArray[position].color ==5 ) {
                banner_text.setTextColor(Color.parseColor("#f637f3"))
            }

            // 백그라운드 색 변경

            if (bannerArray[position].background ==0){
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#000000"))
            }else if (bannerArray[position].background ==1){
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#3c3eda"))
            }else if (bannerArray[position].background ==2){
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#0191b6"))
            }else if (bannerArray[position].background ==3){
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#f9d80d"))
            }else if (bannerArray[position].background ==4){
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#ff7b17"))
            }else if (bannerArray[position].background ==5){
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#f637f3"))
            }

            //글자 크기 변경
            if (bannerArray[position].size ==0){
                banner_text.setTextSize(Dimension.SP, 30.0f)
            } else if (bannerArray[position].size == 1){
                banner_text.setTextSize(Dimension.SP, 50.0f)
            } else if (bannerArray[position].size ==2){
                banner_text.setTextSize(Dimension.SP, 70.0f)
            } else if (bannerArray[position].size ==3){
                banner_text.setTextSize(Dimension.SP, 90.0f)
            }
            banner_text.setOnClickListener {


                var intent = Intent(c, MakingCheerUpTextActivity::class.java)
                intent.putExtra("edt_making_text",bannerArray[position].text)
                intent.putExtra("text_size",bannerArray[position].size)
                intent.putExtra("background_color",bannerArray[position].background)
                intent.putExtra("text_color", bannerArray[position].color)
                intent.putExtra("speed",bannerArray[position].speed)
                intent.putExtra("direction",bannerArray[position].direction)
                intent.putExtra("font",bannerArray[position].font)
                intent.putExtra("effect0",bannerArray[position].blink)
                intent.putExtra("effect1",bannerArray[position].outline)
                intent.putExtra("effect2",bannerArray[position].shining)
                intent.putExtra("effect3",bannerArray[position].shadow)
                intent.putExtra("idx",bannerArray[position].idx)
                c.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))

            }

            delete_image.onClick {
                /**
                 * TODO 2020.05.02 팝업 띄우기
                 */
                delete_image.setImageResource(R.drawable.ic_delete_red)
                var intent = Intent(c, DeleteBannerPopUpActivity::class.java)
                intent.putExtra("bannerIdx",bannerArray[position].idx)
                c.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK))

                for (i in 0..itemCount) {
                    notifyItemChanged(
                        i,
                        "disable"
                    )
                }


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