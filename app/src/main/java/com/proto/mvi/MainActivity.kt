package com.proto.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.proto.mvi.app.AppViewModel
import com.proto.mvi.ui.theme.ProtoMVITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appViewModel: AppViewModel = hiltViewModel()
            val isDarkTheme by appViewModel
                .isDarkTheme
                .collectAsStateWithLifecycle()

            val navController = rememberNavController()
            ProtoMVITheme(darkTheme = isDarkTheme) {
                AppNavHost(navController = navController,appViewModel)
            }
        }
    }
}
