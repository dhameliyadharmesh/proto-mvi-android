package com.proto.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.proto.mvi.ui.theme.ProtoMVITheme
import com.proto.mvi.ui.theme.WrapperScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ProtoMVITheme {
                WrapperScreen {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}
