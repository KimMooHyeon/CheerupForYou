package com.sabuzak.yeonamplace.cheerupforyou.data.makingcheeruptext

import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner

interface MakingCheerUpTextLocalDataSource {
    suspend fun insert(banner: Banner)
    suspend fun update(banner: Banner)
}

class MakingCheerUpTextLocalDataSourceImpl(private val bannerDao: BannerDao) : MakingCheerUpTextLocalDataSource {
    override suspend fun insert(banner: Banner) {
        bannerDao.insert(banner)
    }
    override suspend fun update(banner: Banner) {
        bannerDao.update(banner)
    }
}