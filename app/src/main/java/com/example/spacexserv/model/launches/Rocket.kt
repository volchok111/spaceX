package com.example.spacexserv.model.launches

data class Rocket(
    val fairings: Fairings,
    val first_stage: FirstStage,
    val rocket: RocketX,
    val rocket_name: String,
    val rocket_type: String,
    val second_stage: SecondStage
)