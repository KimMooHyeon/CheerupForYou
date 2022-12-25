package com.sabuzak.yeonamplace.cheerupforyou.di

import android.content.Context
import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.dataBase.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.create(context)

    @Provides
    @Singleton
    fun provideAppDatabaseDao(db: AppDatabase
    ): BannerDao = db.bannerDao()
}