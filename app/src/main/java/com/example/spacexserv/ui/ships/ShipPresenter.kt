package com.example.spacexserv.ui.ships

import android.annotation.SuppressLint
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.spacexserv.data.api.RetrofitInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ShipPresenter : MvpPresenter<ShipView>() {
    private var retrofitInstance: RetrofitInstance = RetrofitInstance


    @SuppressLint("CheckResult")
    fun getShipById(id: String) {
        retrofitInstance.getHttpApi().getShipById(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({s ->
                Log.d("Artem","Ship arrived in presenter ${s.id}")
                viewState.onLoaded(s)
            }, {
                Log.d("Artem","Ship arrived in presenter ERROR ")
                viewState.onError()
            })
    }


}