package com.hackathon.data.dto

data class Data(
    val _deletedAt: Any,
    val alertlevel: String,
    val alertscore: Int,
    val createdAt: String,
    val description: String,
    val guid: String,
    val id: String,
    val landCountry: String,
    val latlong: List<Double>,
    val magnitudeUnit: String,
    val magnitudeValue: String,
    val severity: String,
    val title: String,
    val type: String,
    val updatedAt: String,
    val url: String
)