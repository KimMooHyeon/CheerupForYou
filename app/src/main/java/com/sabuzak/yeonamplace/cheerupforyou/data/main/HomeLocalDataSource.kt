package com.sabuzak.yeonamplace.cheerupforyou.data.main

import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {
    fun getAll(): Flow<List<Banner>>
    fun bannerByIdx(idx: Int): Banner
    fun getCount(): Int
    suspend fun insert(banner: Banner)
    suspend fun delete(banner: Banner)
    suspend fun deleteByIdx(idx: Int)
    suspend fun update(banner: Banner)
}

class HomeLocalDataSourceImpl(private val bannerDao: BannerDao) : HomeLocalDataSource {
    override fun getAll(): Flow<List<Banner>> {
        return bannerDao.getAll()
    }

    override fun bannerByIdx(idx: Int): Banner {
        return bannerDao.getBannerByIdx(idx)
    }

    override fun getCount(): Int {
        return bannerDao.getCount()
    }

    override suspend fun insert(banner: Banner) {
        bannerDao.insert(banner)
    }

    override suspend fun delete(banner: Banner) {
        bannerDao.delete(banner)
    }

    override suspend fun deleteByIdx(idx: Int) {
        bannerDao.deleteBannerByIdx(idx)
    }

    override suspend fun update(banner: Banner) {
        bannerDao.update(banner)
    }
}