package com.hackathon.data.dto

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface DisasterRepo{
    fun getEvents():Flow<PagingData<Data>>
}