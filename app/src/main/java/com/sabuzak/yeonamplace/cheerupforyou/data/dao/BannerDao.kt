package com.sabuzak.yeonamplace.cheerupforyou.data.dao

import androidx.room.*
import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {
    @Insert
    suspend fun insert(banner: Banner): Long

    @Query("SELECT * FROM BANNER_TB")
    fun getAll(): Flow<List<Banner>>

    @Query("SELECT COUNT(*) FROM BANNER_TB")
    fun getCount(): Int

    @Query("SELECT * FROM BANNER_TB WHERE idx = :idx")
    fun getBannerByIdx(idx: Int): Banner

    @Query("DELETE FROM BANNER_TB WHERE idx = :idx")
    suspend fun deleteBannerByIdx(idx: Int)

    @Delete
    suspend fun delete(banner: Banner)

    @Update
    suspend fun update(banner: Banner)

}