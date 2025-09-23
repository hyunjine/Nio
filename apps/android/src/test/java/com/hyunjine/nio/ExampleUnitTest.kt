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

class A: Flow<Int> {
    override suspend fun collect(collector: FlowCollector<Int>) {

        TODO("Not yet implemented")
    }

}