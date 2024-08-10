package com.hackathon.emergency

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hackathon.data.dto.DisastersApi

import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl (
    private val disastersApi: DisastersApi
): ContactRepository {
    override fun getContact(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ContactsPagingSource(disastersApi)
            }
        ).flow
    }
}