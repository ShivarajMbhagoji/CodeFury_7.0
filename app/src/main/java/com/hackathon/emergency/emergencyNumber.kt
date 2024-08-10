package com.hackathon.emergency

data class emergencyNumber(
    val `data`: List<Data>,
    val hasMore: Boolean,
    val `object`: String,
    val page: Int,
    val totalResults: Int
)