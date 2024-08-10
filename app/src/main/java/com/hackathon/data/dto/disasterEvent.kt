package com.hackathon.data.dto

data class disasterEvent(
    val `data`: List<Data>,
    val hasMore: Boolean,
    val `object`: String,
    val page: Int,
    val totalResults: Int
)