package com.hyunjine.nio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hyunjine.nio.clothes.ClothesScreen
import com.hyunjine.nio.home.HomeScreen
import com.hyunjine.nio.ui.theme.NioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NioTheme {

                ClothesScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding()
                        .padding(horizontal = 22.dp)
                )
//                HomeScreen(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .statusBarsPadding()
//                        .navigationBarsPadding()
//                        .padding(horizontal = 22.dp)
//                )
            }
        }
    }
}

