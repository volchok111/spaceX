package com.example.spacexserv.ui.launches

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.spacexserv.data.api.RetrofitInstance
import com.example.spacexserv.model.launches.Ship
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

@InjectViewState
class LaunchesPresenter: MvpPresenter<LaunchesView>() {
    private var retrofitInstance: RetrofitInstance = RetrofitInstance
    private val compositeDisposable = CompositeDisposable()

    fun startInitialization(context: Context?) {


            compositeDisposable.add(
                retrofitInstance.getHttpApi().getLaunches()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ s ->
                        run {
                            viewState.onVideoSetup(s.links.video_link.split("be/")[1])
                            getEachShip(s.ships)
                        }
                    }, {
                        viewState.onUnknownError()
                    })
            )


        }


        private fun getEachShip(shipList: List<Ship>) {
            shipList.forEach { each ->
                compositeDisposable.add(
                    retrofitInstance.getHttpApi().getShipById(each.id)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ s ->
                            viewState.addShip(s)
                            Log.d("Artem","Ship sending from presenter ${s.id}")
                        }, {
                            viewState.onUnknownError()
                        })
                )
            }
        }

    fun dispose() {
        compositeDisposable.dispose()
    }

    companion object {
        private val dataList = ArrayList<Ship>()
    }

    }

