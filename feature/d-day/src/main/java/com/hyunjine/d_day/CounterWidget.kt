package com.hyunjine.d_day

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.hyunjine.common.log.wlog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CounterWidget: GlanceAppWidget() {
    companion object {
        var count = 0 // static 변수로 상태 유지
    }
    override val stateDefinition: GlanceStateDefinition<LocalDateTime>
        get() = object: GlanceStateDefinition<LocalDateTime> {
            override suspend fun getDataStore(
                context: Context,
                fileKey: String
            ): DataStore<LocalDateTime> {
                return TodoDataStore(context)
            }

            override fun getLocation(context: Context, fileKey: String): File {
                throw NotImplementedError("Not implemented for Todo App Widget State Definition")
            }
        }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // use provideContent, you can use composable function
        wlog("update")
        provideContent {
            Column {
                Text("Count: $count")
                Button(
                    text = "Increment",
                    onClick = actionRunCallback<IncrementAction>()
                )
            }
//            Widget(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@SuppressLint("RestrictedApi")
@Composable
@Preview()
fun Widget(text: String) {
    Text(
        text = text,
        modifier = GlanceModifier
            .background(Color.Red)
            .padding(20.dp),
        style = TextStyle(
            color = ColorProvider(Color.Black),
            fontSize = 20.sp
        ),
    )
}

class IncrementAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        CounterWidget.count += 1 // static으로 증가
        wlog(CounterWidget.count)
        CounterWidget().update(context, glanceId) // UI 갱신
    }
}

class TodoDataStore(private val context: Context): DataStore<LocalDateTime> {
    override val data: Flow<LocalDateTime>
        get() {
            val todoDbHelper = TodoDbHelper(context)
            val dbHelper = DbHelper(todoDbHelper.readableDatabase)
            val todoDao = TodoDao(dbHelper)
            return flow { emit(todoDao.findAll()) }
        }

    override suspend fun updateData(transform: suspend (t: LocalDateTime) -> LocalDateTime): LocalDateTime {
        throw NotImplementedError("Not implemented in Todo Data Store")
    }
}