package com.hyunjine.lock


import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.os.Handler
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import com.hyunjine.common.log.wlog

class AppTraceService : AccessibilityService() {
    companion object {
        private val LOCK_APP_PACKAGES: Set<String> = setOf(
            "com.instagram.android", // 인스타
            "com.google.android.youtube", // 유튜브
            "com.sec.android.app.sbrowser" // 삼성 인터넷
        )
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return
        wlog(event.packageName)
        if (LOCK_APP_PACKAGES.contains(event.packageName)) {
            Handler(mainLooper).post {
                wlog("in")
                Toast.makeText(this, "시발롬아", Toast.LENGTH_SHORT).show()
            }

        }

//        val packageName = event.packageName?.toString()

    }

    override fun onInterrupt() {}

}