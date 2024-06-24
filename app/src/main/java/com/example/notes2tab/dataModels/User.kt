package com.example.notes2tab.dataModels

data class User(
    val uid: String = "",
    val username: String = "",
    val email: String = "",
    val favorites: List<String> = listOf(),
)
