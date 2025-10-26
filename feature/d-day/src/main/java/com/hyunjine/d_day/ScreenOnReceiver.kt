package com.hyunjine.d_day

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import com.hyunjine.common.log.wlog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScreenOnReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_SCREEN_ON) {
            val job = CoroutineScope(Dispatchers.Main).launch {
                CounterWidget().updateAll(context)
                wlog("call")
            }
            job.invokeOnCompletion {
                job.cancel()
            }
        }
    }
}