package com.hyunjine.timer.running.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.IntentCompat
import androidx.lifecycle.LifecycleService
import com.hyunjine.timer.main.model.TimerState

class TimerRunningService: LifecycleService() {
    companion object {
        const val CHANNEL_ID = "TimerRunningService"
        const val NOTIFICATION_ID = 1000
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("서비스 실행 중")
            .setContentText("백그라운드 작업을 수행하고 있습니다.")
//            .setSmallIcon(R.drawable.ic_notification)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
        return START_STICKY
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "타이머",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }
}

