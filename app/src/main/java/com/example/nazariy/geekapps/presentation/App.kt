package com.example.nazariy.geekapps.presentation

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration



class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(configuration)
    }
}