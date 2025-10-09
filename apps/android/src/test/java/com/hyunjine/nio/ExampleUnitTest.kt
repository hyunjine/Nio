package com.hyunjine.nio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

fun main() {
    val n = readln().toInt()
    val arr = IntArray(n+1) { 0 }
    arr[2] = 3
    for (i in 3 .. n) {
        var result = 0
        var left = 1
        while (left <= i / 2) {
            val right = i - left
            if (arr[left] != 0 && arr[right] != 0) {
                result += arr[left] + arr[right]
            }
            left++
        }
        arr[i] = result
    }
    print(arr[n])
}