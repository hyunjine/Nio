package com.hyunjine.nio

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hyunjine.clothes.list.ClothesScreen
import com.hyunjine.clothes.main.HomeScreen
import com.hyunjine.lock.AppTraceService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
//        startForegroundService(Intent(this, AppTraceService::class.java))
        setContent {
            NioTheme {
                NioApp()
            }
        }
    }
}

@Composable
fun NioApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NioScreen.Home.name,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 22.dp)
    ) {
        composable(
            route = NioScreen.Home.name
        ) {
            HomeScreen(
                onClick = { navController.navigate(NioScreen.Clothes.name) }
            )
        }
        composable(
            route = NioScreen.Clothes.name
        ) {
            ClothesScreen()
        }
    }
}

enum class NioScreen {
    Home,
    Clothes
}