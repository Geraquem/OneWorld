package com.mmfsin.oneworld.presentation.bedrock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mmfsin.oneworld.presentation.core.navigation.NavCreateEvent
import com.mmfsin.oneworld.presentation.core.navigation.NavEditProfile
import com.mmfsin.oneworld.presentation.core.navigation.NavEventDetail
import com.mmfsin.oneworld.presentation.core.navigation.NavLogin
import com.mmfsin.oneworld.presentation.core.navigation.NavUserProfile
import com.mmfsin.oneworld.presentation.core.theme.OneWorldTheme
import com.mmfsin.oneworld.utils.BEDROCK_NAV_GRAPH
import com.mmfsin.oneworld.utils.BEDROCK_STR_ARGS
import com.mmfsin.oneworld.utils.NAV_CREATE_EVENT
import com.mmfsin.oneworld.utils.NAV_EDIT_PROFILE
import com.mmfsin.oneworld.utils.NAV_EVENT_DETAIL
import com.mmfsin.oneworld.utils.NAV_LOGIN
import com.mmfsin.oneworld.utils.NAV_USER_PROFILE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BedRockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OneWorldTheme {
                val navGraph = intent.getStringExtra(BEDROCK_NAV_GRAPH)
                val strArgs = intent.getStringExtra(BEDROCK_STR_ARGS)

                when (navGraph) {
                    /** Events */
                    NAV_EVENT_DETAIL -> NavEventDetail(strArgs)
                    NAV_CREATE_EVENT -> NavCreateEvent()

                    /** Users */
                    NAV_LOGIN -> NavLogin()
                    NAV_EDIT_PROFILE -> NavEditProfile()
                    NAV_USER_PROFILE -> NavUserProfile()
                    else -> finish()
                }
            }
        }
    }
}