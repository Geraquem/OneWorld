package com.mmfsin.oneworld.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.mmfsin.oneworld.R
import com.mmfsin.oneworld.presentation.BedRockActivity
import com.mmfsin.oneworld.presentation.core.components.MediumText
import com.mmfsin.oneworld.presentation.core.theme.OrangeMedium

fun Context.openBedRockActivity(screen: String, strArgs: String? = null) {
    val intent = Intent(this, BedRockActivity::class.java)
    intent.putExtra(BEDROCK_SCREEN_ARGS, screen)
    strArgs?.let { intent.putExtra(BEDROCK_STR_ARGS, strArgs) }
    startActivity(intent)
}

fun Context.openBedRockActivity(navGraph: String) {
    val intent = Intent(this, BedRockActivity::class.java)
    intent.putExtra(BEDROCK_NAV_GRAPH, navGraph)
    startActivity(intent)
}

@Composable
fun ImagePicker(onImageSelected: (Uri) -> Unit) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri -> uri?.let { onImageSelected(it) } }

    TextButton(onClick = {launcher.launch("image/*")}) {
        MediumText(
            R.string.edit_profile_image,
            color = OrangeMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}