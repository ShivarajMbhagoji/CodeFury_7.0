package com.hackathon.data.dto

import com.hackathon.emergency.emergencyNumber
import retrofit2.http.GET
import retrofit2.http.Headers

interface DisastersApi{

    @Headers(
        "Authorization:sk_live_2ee8233d-ad20-41d4-931a-8f3eb7f9a5af"
    )
    @GET("events")
    suspend fun getEvents(

    ):disasterEvent


    @Headers(
        "Authorization:sk_live_2ee8233d-ad20-41d4-931a-8f3eb7f9a5af"
    )
    @GET("countries")
    suspend fun getContact(

    ): emergencyNumber
}