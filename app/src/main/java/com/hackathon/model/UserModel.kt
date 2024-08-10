package com.hackathon.model


data class UserModel(
    val email: String = "",
    val password: String="",
    val name: String="",
    val uid: String = "",
    val disReported:Int = 0
)