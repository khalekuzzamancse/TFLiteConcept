package com.kzcse.tfliteconcept.feature.recognize

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediaPicker(
    navigationIcon: @Composable () -> Unit,
    onBitmapSelected: (Bitmap?) -> Unit
) {


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = navigationIcon,
                title = { Text(text = "Media Picker") }
            )
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize(), contentAlignment = Alignment.Center) {


        }

    }

}

