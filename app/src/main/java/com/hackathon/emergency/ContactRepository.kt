package com.hackathon.emergency

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ContactRepository{

    fun getContact(): Flow<PagingData<Data>>

}