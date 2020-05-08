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
import androidx.annotation.Dimension
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner
import com.sabuzak.yeonamplace.cheerupforyou.MakingCheerUpTextActivity
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.popup.DeleteBannerPopUpActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.banner_recyclerview_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

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
        Log.d("payload",payloads.toString())
        if(payloads.size > 0) {
            if (payloads[0] == "active") {
                holder.itemView.delete_image.visibility = View.VISIBLE
            } else if (payloads[0] == "disable") {
                holder.itemView.delete_image.setImageResource(R.drawable.ic_delete)
                holder.itemView.delete_image.visibility = View.GONE
            }
        }
        else{

            holder.itemView.banner_text.setText(bannerArray[position].text)
            //효과 받기

            if(bannerArray[position].outline!! ) {
                //있는거
                holder.itemView.banner_text.setStroke(true)
                holder.itemView.banner_text.draw(Canvas())
            }

            if(bannerArray[position].shining!!) {
                holder.itemView.banner_text.setShadowLayer(15.0f,0.0f,0.0f, Color.parseColor("#ffffff"))
            }
            if(bannerArray[position].shadow!!) {
                holder.itemView.banner_text.setShadowLayer(4.0f,8.0f,3.0f, Color.parseColor("#2AEFF5"))
            }


            // 폰트 받기
            if(bannerArray[position].font==0){
                holder.itemView.banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/nanum.ttf"))
            }else if (bannerArray[position].font==1){
                holder.itemView.banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/hansuwon.ttf"))
            }else if (bannerArray[position].font==2){
                holder.itemView.banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/uljiro.ttf"))
            }else if (bannerArray[position].font==3){
                holder.itemView.banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/hanna.ttf"))
            }else if (bannerArray[position].font==4){
                holder.itemView.banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/yanolza.ttf"))
            }else if (bannerArray[position].font==5){
                holder.itemView.banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/jua.ttf"))
            }else if (bannerArray[position].font==6){
                holder.itemView.banner_text.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/tvn.ttf"))
            }


            // 글자 색 받기
            if(bannerArray[position].color ==0 ) {
                holder.itemView.banner_text.setTextColor(Color.parseColor("#ffffff"))
            }else if(bannerArray[position].color ==1 ) {
                holder.itemView.banner_text.setTextColor(Color.parseColor("#3c3eda"))
            }else if(bannerArray[position].color ==2 ) {
                holder.itemView.banner_text.setTextColor(Color.parseColor("#0191b6"))
            }else if(bannerArray[position].color ==3 ) {
                holder.itemView.banner_text.setTextColor(Color.parseColor("#f9d80d"))
            }else if(bannerArray[position].color ==4 ) {
                holder.itemView.banner_text.setTextColor(Color.parseColor( "#ff7b17"))
            }else if(bannerArray[position].color ==5 ) {
                holder.itemView.banner_text.setTextColor(Color.parseColor("#f637f3"))
            }

            // 백그라운드 색 변경

            if (bannerArray[position].background ==0){
                holder.itemView.hs_rv_main_item.setBackgroundColor(Color.parseColor("#000000"))
            }else if (bannerArray[position].background ==1){
                holder.itemView.hs_rv_main_item.setBackgroundColor(Color.parseColor("#3c3eda"))
            }else if (bannerArray[position].background ==2){
                holder.itemView.hs_rv_main_item.setBackgroundColor(Color.parseColor("#0191b6"))
            }else if (bannerArray[position].background ==3){
                holder.itemView.hs_rv_main_item.setBackgroundColor(Color.parseColor("#f9d80d"))
            }else if (bannerArray[position].background ==4){
                holder.itemView.hs_rv_main_item.setBackgroundColor(Color.parseColor("#ff7b17"))
            }else if (bannerArray[position].background ==5){
                holder.itemView.hs_rv_main_item.setBackgroundColor(Color.parseColor("#f637f3"))
            }

            //글자 크기 변경
            if (bannerArray[position].size ==0){
                holder.itemView.banner_text.setTextSize(Dimension.SP, 30.0f)
            } else if (bannerArray[position].size == 1){
                holder.itemView.banner_text.setTextSize(Dimension.SP, 50.0f)
            } else if (bannerArray[position].size ==2){
                holder.itemView.banner_text.setTextSize(Dimension.SP, 70.0f)
            } else if (bannerArray[position].size ==3){
                holder.itemView.banner_text.setTextSize(Dimension.SP, 90.0f)
            }
            holder.itemView.banner_text.setOnClickListener {
                ctx.startActivity<MakingCheerUpTextActivity>(
                    "edt_making_text" to bannerArray[position].text,
                    "text_size" to bannerArray[position].size ,
                    "background_color" to bannerArray[position].background,
                    "text_color" to bannerArray[position].color ,
                    "speed" to bannerArray[position].speed ,
                    "direction" to bannerArray[position].direction,
                    "font" to bannerArray[position].font ,
                    "effect0" to bannerArray[position].blink,
                    "effect1" to bannerArray[position].outline,
                    "effect2" to bannerArray[position].shining,
                    "effect3" to bannerArray[position].shadow,
                    "idx" to bannerArray[position].idx)
            }




            holder.itemView.delete_image.onClick {
                /**
                 * TODO 2020.05.02 팝업 띄우기
                 */
                holder.itemView.delete_image.setImageResource(R.drawable.ic_delete_red)
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