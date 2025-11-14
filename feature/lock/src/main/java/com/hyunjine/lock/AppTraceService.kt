package com.hyunjine.lock


import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.hyunjine.common.log.wlog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class AppTraceService : AccessibilityService(), CoroutineScope {
    private val deviceShield: DeviceShield by lazy {
        DeviceShield(this)
    }

    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    companion object {
        private val LOCK_APP_PACKAGES: Set<String> = setOf(
            "com.instagram.android", // 인스타
            "com.google.android.youtube", // 유튜브
            "com.sec.android.app.sbrowser" // 삼성 인터넷
        )
    }


    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) return

        val pkg = event.packageName ?: return
        wlog(pkg, AccessibilityEvent.eventTypeToString(event.eventType), LOCK_APP_PACKAGES.contains(pkg))
        if (LOCK_APP_PACKAGES.contains(pkg)) {
            deviceShield.show()
        } else {
            deviceShield.hide()
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    override fun onInterrupt() {

    }

}