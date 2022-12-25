package com.sabuzak.yeonamplace.cheerupforyou.di

import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeLocalDataSource
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeLocalDataSourceImpl
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeRepository
import com.sabuzak.yeonamplace.cheerupforyou.data.main.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {

    @Singleton
    @Provides
    fun provideHomeLocalDataSource(dao: BannerDao): HomeLocalDataSource =
        HomeLocalDataSourceImpl(dao)

    @Singleton
    @Provides
    fun provideHomeRepository(
        homeLocalDataSource: HomeLocalDataSource
    ): HomeRepository =
        HomeRepositoryImpl(homeLocalDataSource)
}
