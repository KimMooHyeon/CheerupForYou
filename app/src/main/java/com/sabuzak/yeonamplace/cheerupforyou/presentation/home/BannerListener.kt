package com.sabuzak.yeonamplace.cheerupforyou.presentation.home

import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner

interface BannerListener {
    fun chooseDeleteBanner(index: Int)
    fun choosePlayBanner(banner: Banner)
}
