package com.sabuzak.yeonamplace.cheerupforyou.data.makingcheeruptext

import com.sabuzak.yeonamplace.cheerupforyou.data.model.Banner
import javax.inject.Inject

interface MakingCheerUpTextRepository {
    suspend fun insert(banner: Banner)
    suspend fun update(banner: Banner)
}

class MakingCheerUpTextRepositoryImpl @Inject constructor(
    private val makingCheerUpTextDataSource: MakingCheerUpTextLocalDataSource
) : MakingCheerUpTextRepository {
    override suspend fun insert(banner: Banner) {
        return makingCheerUpTextDataSource.insert(banner)
    }
    override suspend fun update(banner: Banner) {
        return makingCheerUpTextDataSource.update(banner)
    }
}