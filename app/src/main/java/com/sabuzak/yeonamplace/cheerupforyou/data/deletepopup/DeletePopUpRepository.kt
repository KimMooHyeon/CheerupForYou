package com.sabuzak.yeonamplace.cheerupforyou.data.deletepopup

import javax.inject.Inject

interface DeletePopUpRepository {
    suspend fun deleteByIdx(idx: Int)
}

class DeletePopUpRepositoryImpl @Inject constructor(
    private val homeLocalDataSource: DeletePopUpLocalDataSource
) : DeletePopUpRepository {
    override suspend fun deleteByIdx(idx: Int) {
        return homeLocalDataSource.deleteByIdx(idx)
    }
}