package com.example.spacexserv.data.api


import com.example.spacexserv.model.launches.PastLaunches
import com.example.spacexserv.model.ships.Ships
import io.reactivex.Observable

import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("/rest/launch-latest")
    fun getLaunches(): Observable<PastLaunches>

    @GET("/rest/ship/{id}")
     fun getShipById(@Path("id")id: String): Observable<Ships>
}