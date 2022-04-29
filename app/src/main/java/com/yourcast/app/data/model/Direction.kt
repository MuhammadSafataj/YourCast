package com.yourcast.app.data.model

data class Direction(
    val name: String,
    val local_names: LocalNames,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String,
)
