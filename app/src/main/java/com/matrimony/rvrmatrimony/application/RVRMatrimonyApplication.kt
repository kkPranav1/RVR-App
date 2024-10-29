package com.matrimony.rvrmatrimony.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RVRMatrimonyApplication : Application() {
    companion object {

    }

    override fun onCreate() {
        super.onCreate()
    }
}