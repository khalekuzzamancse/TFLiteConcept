package com.kzcse.tfliteconcept.presenation

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.kzcse.tfliteconcept.domain.Logger
import com.smarttoolfactory.cropper.ImageCropper
import com.smarttoolfactory.cropper.model.OutlineType
import com.smarttoolfactory.cropper.model.RectCropShape
import com.smarttoolfactory.cropper.settings.CropDefaults
import com.smarttoolfactory.cropper.settings.CropOutlineProperty


@Composable
fun ImageCropScreen(imageBitmap: ImageBitmap, onCropped: (Bitmap) -> Unit) {
    var crop by remember { mutableStateOf(false) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            crop = true
        }) {
            Text("Crop")
        }
        ImageCropper(
            imageBitmap = imageBitmap,
            contentDescription = "",
            crop = crop,
            cropProperties = CropDefaults.properties(
                handleSize = 220f,
                cropOutlineProperty = CropOutlineProperty(
                    outlineType = OutlineType.Rect,
                    cropOutline = RectCropShape(1, "Custom") // 1:1 aspect ratio
                ),
                requiredSize = IntSize(224, 244) // final cropped size
            ),
            onCropStart = {
                Logger.on("MainActivity::Crop", "Started")
            },
            onCropSuccess = { imageBitmap ->
                val width = imageBitmap.width
                val height = imageBitmap.height
                onCropped(imageBitmap.asAndroidBitmap())
                Logger.on("MainActivity::Cropped", "($width,$height)")
                crop = false // Reset crop flag after success
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

    }
}