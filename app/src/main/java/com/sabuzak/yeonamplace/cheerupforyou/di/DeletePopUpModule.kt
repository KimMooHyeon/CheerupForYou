package com.sabuzak.yeonamplace.cheerupforyou.di

import com.sabuzak.yeonamplace.cheerupforyou.data.dao.BannerDao
import com.sabuzak.yeonamplace.cheerupforyou.data.deletepopup.DeletePopUpLocalDataSource
import com.sabuzak.yeonamplace.cheerupforyou.data.deletepopup.DeletePopUpLocalDataSourceImpl
import com.sabuzak.yeonamplace.cheerupforyou.data.deletepopup.DeletePopUpRepository
import com.sabuzak.yeonamplace.cheerupforyou.data.deletepopup.DeletePopUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DeletePopUpModule {
    @Singleton
    @Provides
    fun provideHomeLocalDataSource(dao: BannerDao): DeletePopUpLocalDataSource =
        DeletePopUpLocalDataSourceImpl(dao)

    @Singleton
    @Provides
    fun provideHomeRepository(
        deletePopUpLocalDataSource: DeletePopUpLocalDataSource
    ): DeletePopUpRepository =
        DeletePopUpRepositoryImpl(deletePopUpLocalDataSource)
}
