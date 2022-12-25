package com.sabuzak.yeonamplace.cheerupforyou.di

import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.makingcheeruptext.MakingCheerUpTextLocalDataSource
import com.sabuzak.yeonamplace.cheerupforyou.data.makingcheeruptext.MakingCheerUpTextLocalDataSourceImpl
import com.sabuzak.yeonamplace.cheerupforyou.data.makingcheeruptext.MakingCheerUpTextRepository
import com.sabuzak.yeonamplace.cheerupforyou.data.makingcheeruptext.MakingCheerUpTextRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MakingCheerUpTextModule {
    @Singleton
    @Provides
    fun provideMakingCheerUpTextLocalDataSource(dao: BannerDao): MakingCheerUpTextLocalDataSource =
        MakingCheerUpTextLocalDataSourceImpl(dao)

    @Singleton
    @Provides
    fun provideMakingCheerUpTextRepository(
        deletePopUpLocalDataSource: MakingCheerUpTextLocalDataSource
    ): MakingCheerUpTextRepository =
        MakingCheerUpTextRepositoryImpl(deletePopUpLocalDataSource)
}
