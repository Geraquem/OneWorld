package com.mmfsin.oneworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mmfsin.oneworld.presentation.core.navigation.NavigationWrapper
import com.mmfsin.oneworld.presentation.core.theme.OneWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            OneWorldTheme { NavigationWrapper() }
        }
    }
}