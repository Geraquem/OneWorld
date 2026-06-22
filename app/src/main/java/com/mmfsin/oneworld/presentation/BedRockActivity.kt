package com.mmfsin.oneworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.mmfsin.oneworld.presentation.core.navigation.navigation.NavCreateEvent
import com.mmfsin.oneworld.presentation.core.navigation.navigation.NavEditProfile
import com.mmfsin.oneworld.presentation.core.theme.OneWorldTheme
import com.mmfsin.oneworld.utils.BEDROCK_NAV_GRAPH
import com.mmfsin.oneworld.utils.NAV_CREATE_EVENT
import com.mmfsin.oneworld.utils.NAV_EDIT_PROFILE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BedRockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneWorldTheme {
                val navGraph = intent.getStringExtra(BEDROCK_NAV_GRAPH)
                when (navGraph) {
                    NAV_EDIT_PROFILE -> NavEditProfile()
                    NAV_CREATE_EVENT -> NavCreateEvent()
                    else -> finish()
                }
            }
        }
    }
}