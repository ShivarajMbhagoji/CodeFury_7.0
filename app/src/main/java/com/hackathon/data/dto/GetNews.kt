package com.hackathon.data.dto

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GetEvents(
    private val disasterRepo: DisasterRepo
) {
    operator fun invoke(): Flow<PagingData<Data>> {
        return disasterRepo.getEvents()
    }
}