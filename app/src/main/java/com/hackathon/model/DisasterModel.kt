package com.hackathon.model


data class DisasterModel(
    val uid: String = "",
    val disasterId: String="",
    val title: String = "",
    val status: String="",
    val place: String="",
    val imgList: List<String> = emptyList()
)