package com.example.e_commerce

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyApplication :Application() {

    override fun onCreate() {
        super.onCreate()

        listenToInternet()
    }


    @SuppressLint("CheckResult")
    fun listenToInternet()
    {

        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet: Boolean ->
                Log.d(TAG ,"isConnectedToInternet $isConnectedToInternet" )
                // do something with isConnectedToInternet value
                FirebaseCrashlytics.getInstance().setCustomKey("connected_to_internet", isConnectedToInternet)
            }
    }


    companion object{
        private const val TAG = "TAG"
    }
}