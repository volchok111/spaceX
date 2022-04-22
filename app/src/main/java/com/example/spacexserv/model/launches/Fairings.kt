package com.example.spacexserv.model.launches

data class Fairings(
    val recovered: Any,
    val recovery_attempt: Boolean,
    val reused: Boolean,
    val ship: String
)