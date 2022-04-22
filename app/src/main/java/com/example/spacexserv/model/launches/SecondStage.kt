package com.example.spacexserv.model.launches

data class SecondStage(
    val block: Int,
    val payloads: List<Payload>
)