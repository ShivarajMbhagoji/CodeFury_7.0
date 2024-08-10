package com.hackathon.emergency

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GetContacts(
    private val contactRepository: ContactRepository
) {
    operator fun invoke(): Flow<PagingData<Data>> {
        return contactRepository.getContact()
    }
}