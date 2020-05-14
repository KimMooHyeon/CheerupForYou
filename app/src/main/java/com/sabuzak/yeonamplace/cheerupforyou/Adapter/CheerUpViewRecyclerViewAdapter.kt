package com.sabuzak.yeonamplace.cheerupforyou.Adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Dimension
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sabuzak.yeonamplace.cheerupforyou.CheerUpViewActivity
import com.sabuzak.yeonamplace.cheerupforyou.Data.CheerUpViewData
import com.sabuzak.yeonamplace.cheerupforyou.OutlineTextView
import com.sabuzak.yeonamplace.cheerupforyou.R
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.windowManager

class CheerUpViewRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<CheerUpViewData>) :
    RecyclerView.Adapter<CheerUpViewRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_cheerupview_item, viewgroup, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tv_rv_item.text=dataList[position].text

        //효과 받기

        if(dataList[position].effect1 == 1) {
            //있는거
            holder.tv_rv_item.setStroke(true)
            holder.tv_rv_item.draw(Canvas())
        }

        if(dataList[position].effect2==1) {
            holder.tv_rv_item.setShadowLayer(15.0f,0.0f,0.0f, Color.parseColor("#ffffff"))
        }
        if(dataList[position].effect3==1) {
            holder.tv_rv_item.setShadowLayer(4.0f,8.0f,3.0f,Color.parseColor("#2AEFF5"))
        }


        // 폰트 받기
        if(dataList[position].font==0){
            holder.tv_rv_item.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/nanum.ttf"))
        }else if (dataList[position].font==1){
            holder.tv_rv_item.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/hansuwon.ttf"))
        }else if (dataList[position].font==2){
            holder.tv_rv_item.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/uljiro.ttf"))
        }else if (dataList[position].font==3){
            holder.tv_rv_item.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/hanna.ttf"))
        }else if (dataList[position].font==4){
            holder.tv_rv_item.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/yanolza.ttf"))
        }else if (dataList[position].font==5){
            holder.tv_rv_item.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/jua.ttf"))
        }else if (dataList[position].font==6){
            holder.tv_rv_item.setTypeface(Typeface.createFromAsset(ctx.getAssets(), "font/tvn.ttf"))
        }


        // 글자 색 받기
        if(dataList[position].text_color ==0 ) {
            holder.tv_rv_item.setTextColor(Color.parseColor("#ffffff"))
        }else if(dataList[position].text_color ==1 ) {
            holder.tv_rv_item.setTextColor(Color.parseColor("#3c3eda"))
        }else if(dataList[position].text_color ==2 ) {
            holder.tv_rv_item.setTextColor(Color.parseColor("#0191b6"))
        }else if(dataList[position].text_color ==3 ) {
            holder.tv_rv_item.setTextColor(Color.parseColor("#f9d80d"))
        }else if(dataList[position].text_color ==4 ) {
            holder.tv_rv_item.setTextColor(Color.parseColor( "#ff7b17"))
        }else if(dataList[position].text_color ==5 ) {
            holder.tv_rv_item.setTextColor(Color.parseColor("#f637f3"))
        }

        //글자 크기 변경
        //글자 고장 50 으로
        if (dataList[position].text_size ==0){
            holder.tv_rv_item.setTextSize(Dimension.SP, 30.0f)
        } else if (dataList[position].text_size == 3){
            holder.tv_rv_item.setTextSize(Dimension.SP, 50.0f)
        } else if (dataList[position].text_size ==2){
            holder.tv_rv_item.setTextSize(Dimension.SP, 70.0f)
        } else if (dataList[position].text_size ==0){
            holder.tv_rv_item.setTextSize(Dimension.SP, 90.0f)
        }
        // 백그라운드 색 변경

        if (dataList[position].background_color ==6){
            holder.tv_rv_item.setTextSize(Dimension.SP, 48.0f)
            holder.hs_rv_item.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.drawable.back_goldenchild_01))
        }else if (dataList[position].background_color ==7){
            holder.hs_rv_item.setBackgroundColor(Color.parseColor("#c80714"))
        }else if (dataList[position].background_color ==8){
            holder.hs_rv_item.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.drawable.back_very_01))
        }else if (dataList[position].background_color ==9){
            holder.hs_rv_item.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.drawable.back_onoff_01))
        }else if (dataList[position].background_color ==10){
            holder.hs_rv_item.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.drawable.back_ownus_01))
        }else if (dataList[position].background_color ==11){
            holder.hs_rv_item.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.drawable.back_pentagon_02))
        }else if (dataList[position].background_color ==12){
            holder.hs_rv_item.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.drawable.back_too_01))
        }


        //중앙 정렬
/*        val display = ctx.windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        var  screenWidth = point.x.toFloat()
        if(holder.tv_rv_item.width < screenWidth){

            val params = holder.ll_rv_item.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.CENTER
            holder.ll_rv_item.layoutParams = params

        }else{
            val params = holder.ll_rv_item.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.CENTER_VERTICAL
            holder.ll_rv_item.layoutParams = params
        }*/

        holder.tv_rv_item.setOnClickListener {
            ctx.startActivity<CheerUpViewActivity>("edt_making_text" to dataList[position].text,"text_size" to dataList[position].text_size , "background_color" to dataList[position].background_color , "text_color" to dataList[position].text_color , "speed" to dataList[position].speed , "direction" to dataList[position].direction,"font" to dataList[position].font , "effect0" to dataList[position].effect0,"effect1" to dataList[position].effect1,"effect2" to dataList[position].effect2,"effect3" to dataList[position].effect3)

        }



    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         var tv_rv_item=itemView.findViewById(R.id.tv_rv_item) as OutlineTextView
         var hs_rv_item = itemView.findViewById(R.id.hs_rv_item) as HorizontalScrollView
        var ll_rv_item = itemView.findViewById(R.id.ll_rv_item) as LinearLayout

    }

}