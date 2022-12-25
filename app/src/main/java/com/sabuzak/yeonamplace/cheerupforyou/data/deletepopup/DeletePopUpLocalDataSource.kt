package com.sabuzak.yeonamplace.cheerupforyou.data.deletepopup

import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao

interface DeletePopUpLocalDataSource {
    suspend fun deleteByIdx(idx: Int)
}

class DeletePopUpLocalDataSourceImpl(private val bannerDao: BannerDao) : DeletePopUpLocalDataSource {
    override suspend fun deleteByIdx(idx: Int) {
        bannerDao.deleteBannerByIdx(idx)
    }
}