package com.hyunjine.nio.util.common_android

import android.util.Log
import kotlin.collections.joinToString

fun wlog(vararg msg: Any?) {
    val stackTrace = Thread.currentThread().stackTrace
    val currentMethodIndex = stackTrace.indexOfFirst { it.methodName == "log" }
    if (currentMethodIndex != -1 && currentMethodIndex + 1 < stackTrace.size) {
        val caller = stackTrace[currentMethodIndex + 1]
        val path = caller.toString()
            .split(".")
            .takeLast(3)
            .joinToString(", ")
            .replace("\$", "_")
        i(tag = "winter", msg = "Path = $path")
    }
    d(tag = "winter", msg = msg.joinToString(", "))
}

private fun i(
    tag: String,
    msg: Any?,
) {
    Log.i(tag, msg.toString())
}

private fun d(
    tag: String,
    msg: Any?
) {
    Log.d(tag, msg.toString())
}