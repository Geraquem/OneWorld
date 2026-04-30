package com.mmfsin.oneworld.utils

import android.content.Context
import android.content.Intent
import com.mmfsin.oneworld.presentation.BedRockActivity

fun Context.openBedRockActivity(screen: String, strArgs: String? = null) {
    val intent = Intent(this, BedRockActivity::class.java)
    intent.putExtra(BEDROCK_SCREEN_ARGS, screen)
    strArgs?.let { intent.putExtra(BEDROCK_STR_ARGS, strArgs) }
    startActivity(intent)
}