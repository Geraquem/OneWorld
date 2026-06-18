package com.mmfsin.oneworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.mmfsin.oneworld.presentation.core.theme.OneWorldTheme
import com.mmfsin.oneworld.presentation.createevent.CreateEventScreen
import com.mmfsin.oneworld.presentation.editprofile.EditProfileScreen
import com.mmfsin.oneworld.utils.BEDROCK_SCREEN_ARGS
import com.mmfsin.oneworld.utils.BEDROCK_STR_ARGS
import com.mmfsin.oneworld.utils.CREATE_EVENT
import com.mmfsin.oneworld.utils.EDIT_PROFILE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BedRockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneWorldTheme {
                val screen = intent.getStringExtra(BEDROCK_SCREEN_ARGS)
                val strArg = intent.getStringExtra(BEDROCK_STR_ARGS)

                Scaffold { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        when (screen) {
                            CREATE_EVENT -> CreateEventScreen(onBack = { finish() })
                            EDIT_PROFILE -> EditProfileScreen(onBack = { finish() })

                            else -> finish()
                        }
                    }
                }
            }
        }
    }
}