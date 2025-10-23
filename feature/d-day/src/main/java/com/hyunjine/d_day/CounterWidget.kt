package com.hyunjine.d_day

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

object CounterWidget: GlanceAppWidget() {
    // widget body
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // use provideContent, you can use composable function
        provideContent {
            Text(
                text = "0",
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
//                    color = GlanceTheme.colors. ColorProvider(123),
                    fontSize = 26.sp
                )
            )

            Button(
                text = "추가",
                onClick = {

                }
            )
        }
    }
}