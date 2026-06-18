package com.mmfsin.oneworld.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mmfsin.oneworld.presentation.core.navigation.navigation.NavEditProfile
import com.mmfsin.oneworld.presentation.core.theme.OneWorldTheme
import com.mmfsin.oneworld.utils.BEDROCK_NAV_GRAPH
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
                    else -> finish()
                }


                //                val screen = intent.getStringExtra(BEDROCK_SCREEN_ARGS)
                //                val strArg = intent.getStringExtra(BEDROCK_STR_ARGS)
                //
                //                Scaffold { innerPadding ->
                //                    Box(Modifier.padding(innerPadding)) {
                //                        when (screen) {
                //                            CREATE_EVENT -> CreateEventScreen(onBack = { finish() })
                //                            EDIT_PROFILE -> EditProfileScreen(onBack = { finish() })
                //
                //                            else -> finish()
                //                        }
                //                    }
                //                }
            }
        }
    }
}