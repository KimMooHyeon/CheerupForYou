package com.sabuzak.yeonamplace.cheerupforyou.data.main

import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface HomeRepository {
    fun getAll(): Flow<List<Banner>>
    fun bannerByIdx(idx: Int): Banner
    fun getCount(): Int
    suspend fun insert(banner: Banner)
    suspend fun delete(banner: Banner)
    suspend fun deleteByIdx(idx: Int)
    suspend fun update(banner: Banner)
}

class HomeRepositoryImpl @Inject constructor(
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {
    override fun getAll(): Flow<List<Banner>> {
        return homeLocalDataSource.getAll()
    }

    override fun bannerByIdx(idx: Int): Banner {
        return homeLocalDataSource.bannerByIdx(idx)
    }

    override fun getCount(): Int {
        return homeLocalDataSource.getCount()
    }

    override suspend fun insert(banner: Banner) {
        return homeLocalDataSource.insert(banner)
    }

    override suspend fun delete(banner: Banner) {
        return homeLocalDataSource.delete(banner)
    }

    override suspend fun deleteByIdx(idx: Int) {
        return homeLocalDataSource.deleteByIdx(idx)
    }

    override suspend fun update(banner: Banner) {
        return homeLocalDataSource.update(banner)
    }

}