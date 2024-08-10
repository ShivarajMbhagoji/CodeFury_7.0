package com.hackathon.emergency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmergencyContactViewModel @Inject constructor(
    private val emergencyUseCases: EmergencyUseCases
): ViewModel(){
    val datas=emergencyUseCases.getContacts(
    ).cachedIn(viewModelScope)
}