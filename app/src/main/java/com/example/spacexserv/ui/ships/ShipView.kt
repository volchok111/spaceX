package com.example.spacexserv.ui.ships

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.spacexserv.model.ships.Ships

@StateStrategyType(SingleStateStrategy::class)
interface ShipView : MvpView {
    fun onLoading()
    fun onError()
    fun onLoaded(ship: Ships)
}
