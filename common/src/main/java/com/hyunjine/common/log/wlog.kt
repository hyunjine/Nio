package com.hyunjine.common.log

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun startLog() {
    Napier.base(DebugAntilog())
}

fun wlog(vararg msg: Any?) {
    val stackTrace = Thread.currentThread().stackTrace
    val currentMethodIndex = stackTrace.indexOfFirst { println(it);it.methodName == "wlog" }
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
    Napier.i(tag = tag, message = msg.toString())
}

private fun d(
    tag: String,
    msg: Any?
) {
    Napier.d(tag = tag, message = msg.toString())
}