package com.example.spacexserv.ui.launches

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.spacexserv.model.launches.Ship
import com.example.spacexserv.model.ships.Ships

@StateStrategyType(SingleStateStrategy::class)
interface LaunchesView : MvpView {
    fun onVideoSetup(link: String)
    fun onListSetup(ships: List<Ship>)
    fun addShip(ship: Ships)
    fun onConnectionAbsence()
    fun onUnknownError()
}