package com.hackathon.data.dto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GlobalEventsViewModel @Inject constructor(
    private val eventsUseCases: EventsUseCases
):ViewModel(){
    val events=eventsUseCases.getEvents(
    ).cachedIn(viewModelScope)
}