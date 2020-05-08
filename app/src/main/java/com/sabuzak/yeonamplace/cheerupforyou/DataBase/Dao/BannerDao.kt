package com.sabuzak.yeonamplace.cheerupforyou.DataBase.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sabuzak.yeonamplace.cheerupforyou.DataBase.Entity.Banner

@Dao
interface BannerDao {
    @Insert
    suspend fun insert(banner: Banner):Long
    @Query("SELECT * FROM BANNER_TB")
    fun getAll(): LiveData<List<Banner>>

    @Query("SELECT COUNT(*) FROM BANNER_TB")
    fun getCount(): Int

    @Query("SELECT * FROM BANNER_TB WHERE idx = :idx")
    fun getBannerByIdx(idx:Int):Banner

    @Query("DELETE FROM BANNER_TB WHERE idx = :idx")
    suspend fun deleteBannerByIdx(idx:Int)

    @Delete
    suspend fun delete(banner: Banner)

    @Update
    suspend fun update(banner: Banner)

}