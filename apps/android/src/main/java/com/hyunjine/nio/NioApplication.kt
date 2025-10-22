package com.hyunjine.nio

import android.app.Application
import com.hyunjine.common.log.startLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NioApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startLog()
    }
}