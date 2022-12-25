package com.sabuzak.yeonamplace.cheerupforyou.presentation.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import androidx.annotation.Dimension
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sabuzak.yeonamplace.cheerupforyou.R
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import com.sabuzak.yeonamplace.cheerupforyou.databinding.BannerRecyclerviewItemBinding
import com.sabuzak.yeonamplace.cheerupforyou.presentation.util.CheerUpCustomView

class BannerRecyclerViewAdapter(private val listener: BannerListener) :
    ListAdapter<Banner, BannerRecyclerViewAdapter.BannerViewHolder>(BannerDiffUtil) {

    inner class BannerViewHolder(private val binding: BannerRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bannerItem: Banner) {
            binding.item = bannerItem

            binding.deleteImage.setOnClickListener {
                listener.chooseDeleteBanner(getItem(layoutPosition).idx)
            }
            binding.bannerText.setOnClickListener {
                listener.choosePlayBanner(getItem(layoutPosition))
            }
            // 폰트 받기

            if (getItem(position).font == 0) {
                binding.bannerText.typeface = Typeface.createFromAsset(binding.bannerText.context.assets, "font/nanum.ttf")
            } else if (getItem(position).font == 1) {
                binding.bannerText.typeface = Typeface.createFromAsset(binding.bannerText.context.assets, "font/hansuwon.ttf")
            } else if (getItem(position).font == 2) {
                binding.bannerText.typeface = Typeface.createFromAsset(binding.bannerText.context.assets, "font/uljiro.ttf")
            } else if (getItem(position).font == 3) {
                binding.bannerText.typeface = Typeface.createFromAsset(binding.bannerText.context.assets, "font/hanna.ttf")
            } else if (getItem(position).font == 4) {
                binding.bannerText.typeface = Typeface.createFromAsset(binding.bannerText.context.assets, "font/yanolza.ttf")
            } else if (getItem(position).font == 5) {
                binding.bannerText.typeface = Typeface.createFromAsset(binding.bannerText.context.assets, "font/jua.ttf")
            } else if (getItem(position).font == 6) {
                binding.bannerText.typeface = Typeface.createFromAsset(binding.bannerText.context.assets, "font/tvn.ttf")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding: BannerRecyclerviewItemBinding =
            BannerRecyclerviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BannerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(getItem(position)?:return)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int, payloads: MutableList<Any>) {

        val delete_image = holder.itemView.findViewById<ImageView>(R.id.delete_image)
        val banner_text = holder.itemView.findViewById<CheerUpCustomView>(R.id.banner_text)
        val hs_rv_main_item = holder.itemView.findViewById<HorizontalScrollView>(R.id.hs_rv_main_item)

        if (payloads.size > 0) {
            if (payloads[0] == "active") {

                delete_image.visibility = View.VISIBLE
            } else if (payloads[0] == "disable") {
                delete_image.setImageResource(R.drawable.ic_delete)
                delete_image.visibility = View.GONE
            }
        } else {

            if (getItem(position).outline!!) {
                //있는거
                banner_text.setStroke(true)
                banner_text.draw(Canvas())
            }

            if (getItem(position).shining!!) {
                banner_text.setShadowLayer(15.0f, 0.0f, 0.0f, Color.parseColor("#ffffff"))
            }
            if (getItem(position).shadow!!) {
                banner_text.setShadowLayer(4.0f, 8.0f, 3.0f, Color.parseColor("#2AEFF5"))
            }




            // 글자 색 받기
            if (getItem(position).color == 0) {
                banner_text.setTextColor(Color.parseColor("#ffffff"))
            } else if (getItem(position).color == 1) {
                banner_text.setTextColor(Color.parseColor("#3c3eda"))
            } else if (getItem(position).color == 2) {
                banner_text.setTextColor(Color.parseColor("#0191b6"))
            } else if (getItem(position).color == 3) {
                banner_text.setTextColor(Color.parseColor("#f9d80d"))
            } else if (getItem(position).color == 4) {
                banner_text.setTextColor(Color.parseColor("#ff7b17"))
            } else if (getItem(position).color == 5) {
                banner_text.setTextColor(Color.parseColor("#f637f3"))
            }

            // 백그라운드 색 변경

            if (getItem(position).background == 0) {
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#000000"))
            } else if (getItem(position).background == 1) {
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#3c3eda"))
            } else if (getItem(position).background == 2) {
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#0191b6"))
            } else if (getItem(position).background == 3) {
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#f9d80d"))
            } else if (getItem(position).background == 4) {
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#ff7b17"))
            } else if (getItem(position).background == 5) {
                hs_rv_main_item.setBackgroundColor(Color.parseColor("#f637f3"))
            }

            //글자 크기 변경
            if (getItem(position).size == 0) {
                banner_text.setTextSize(Dimension.SP, 30.0f)
            } else if (getItem(position).size == 1) {
                banner_text.setTextSize(Dimension.SP, 50.0f)
            } else if (getItem(position).size == 2) {
                banner_text.setTextSize(Dimension.SP, 70.0f)
            } else if (getItem(position).size == 3) {
                banner_text.setTextSize(Dimension.SP, 90.0f)
            }

            holder.bind(getItem(position)?:return)
        }
    }

    companion object{
        val BannerDiffUtil = object : DiffUtil.ItemCallback<Banner>() {
            override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
                return oldItem == newItem
            }
        }
    }
}