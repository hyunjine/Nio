package com.hyunjine.timer.running.service

import com.hyunjine.common.extension.seconds
import com.hyunjine.timer.main.model.TimerState
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

private sealed interface Event {
    data class Start(
        val id: Int,
        val name: String,
        val wholeDuration: Duration,
        val startTime: LocalDateTime
    ) : Event

    data object Resume : Event

    data object Pause : Event

    data object Finish : Event
}

object TimerManager {
    private val mutex = Mutex()

    private val event: MutableSharedFlow<Event> = MutableSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val runningTimer: StateFlow<TimerInfo?> = event.flatMapLatest { e ->
        when (e) {
            is Event.Start, is Event.Resume -> Observable.interval(1, TimeUnit.SECONDS)
                .map { e }
                .asFlow()
            else -> flowOf(e)
        }
    }.runningFold<Event, TimerInfo?>(null) { info, event ->
        when (event) {
            is Event.Start -> {
                info?.copy(
                    currentDuration = info.currentDuration - 1.seconds
                ) ?: TimerInfo(
                    id = event.id,
                    name = event.name,
                    state = TimerState.Running,
                    wholeDuration = event.wholeDuration,
                    currentDuration = event.wholeDuration,
                    finishTime = event.startTime.plusSeconds(event.wholeDuration.inWholeSeconds)
                )
            }
            is Event.Resume -> {
                requireNotNull(info)
                info.copy(
                    state = TimerState.Running,
                    currentDuration = info.currentDuration - 1.seconds,
                    finishTime = LocalDateTime.now().plusSeconds(info.currentDuration.inWholeSeconds)
                )
            }
            is Event.Pause -> {
                requireNotNull(info)
                info.copy(state = TimerState.Paused)
            }
            is Event.Finish -> null
        }
    }.stateIn(scope = CoroutineScope(Dispatchers.Main), started = SharingStarted.Eagerly, null)

    suspend fun run(
        id: Int,
        name: String,
        wholeDuration: Duration
    ): Boolean = mutex.withLock {
        if (runningTimer.value != null) {
            return false
        }
        event.emit(Event.Start(id = id, name = name, wholeDuration = wholeDuration, startTime = LocalDateTime.now()))
        true
    }

    suspend fun resume(): Boolean = mutex.withLock {
        if (runningTimer.value == null) {
            return false
        }
        event.emit(Event.Resume)
        true
    }

    suspend fun pause(): Boolean = mutex.withLock {
        if (runningTimer.value == null) {
            return false
        }
        event.emit(Event.Pause)
        true
    }

    suspend fun finish(): Boolean = mutex.withLock {
        if (runningTimer.value == null) {
            return false
        }
        event.emit(Event.Finish)
        true
    }
}

data class TimerInfo(
    val id: Int,
    val name: String,
    val state: TimerState,
    val wholeDuration: Duration,
    val currentDuration: Duration,
    val finishTime: LocalDateTime
)