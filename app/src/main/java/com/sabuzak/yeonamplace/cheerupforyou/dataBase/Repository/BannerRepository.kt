package com.sabuzak.yeonamplace.cheerupforyou.dataBase.Repository

import androidx.lifecycle.LiveData
import com.sabuzak.yeonamplace.cheerupforyou.dataBase.Dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.dataBase.Entity.Banner

class BannerRepository(private val bannerDao:BannerDao){

    val allBanner:LiveData<List<Banner>> = bannerDao.getAll()

    fun bannerByIdx(idx:Int):Banner {
        return bannerDao.getBannerByIdx(idx)
    }

    fun getCount():Int{
        return bannerDao.getCount()
    }

    suspend fun insert(banner:Banner){
        bannerDao.insert(banner)
    }
    suspend fun delete(banner: Banner){
        bannerDao.delete(banner)
    }

    suspend fun deleteByIdx(idx:Int){
        bannerDao.deleteBannerByIdx(idx)
    }
    suspend fun update(banner: Banner){
        bannerDao.update(banner)
    }
}