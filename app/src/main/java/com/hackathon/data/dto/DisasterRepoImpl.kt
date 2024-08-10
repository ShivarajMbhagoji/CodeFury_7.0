package com.hackathon.data.dto

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class DisasterRepoImpl(
    private val disastersApi: DisastersApi
) : DisasterRepo {
    override fun getEvents(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(disastersApi)
            }
        ).flow
    }
}