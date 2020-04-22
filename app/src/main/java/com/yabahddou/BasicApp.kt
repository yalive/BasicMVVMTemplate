package com.yabahddou

import android.app.Application
import com.yabahddou.di.AppComponent
import com.yabahddou.di.DaggerAppComponent

/**
 ***************************************
 * Created by Y.Abdelhadi on 4/22/20.
 ***************************************
 */

class BasicApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: BasicApp
            private set
    }
}