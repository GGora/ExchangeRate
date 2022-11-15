package com.gg.exchangerate

import android.app.Application
import android.util.Log
//import com.gg.data.di.DataComponent
import com.gg.data.di.DataModule
import com.gg.exchangerate.di.AppComponent
import com.gg.exchangerate.di.AppModule
import com.gg.exchangerate.di.DaggerAppComponent

class MainApplication : Application() {


    override fun onCreate() {
        Log.d(TAG, "onCreate")
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .context(this)
            .application(this)
            .build()
    }

    companion object {
        private val TAG = MainApplication::class.simpleName
        @JvmStatic
        lateinit var appComponent: AppComponent
            private set

    }
}