package com.kzcse.tfliteconcept.presenation

import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import com.kzcse.tfliteconcept.domain.Constants
import com.kzcse.tfliteconcept.domain.Logger
import com.smarttoolfactory.cropper.ImageCropper
import com.smarttoolfactory.cropper.model.OutlineType
import com.smarttoolfactory.cropper.model.RectCropShape
import com.smarttoolfactory.cropper.settings.CropDefaults
import com.smarttoolfactory.cropper.settings.CropOutlineProperty


@Composable
fun ImageCropScreen(
    imageBitmap: ImageBitmap,
    onCropped: (Bitmap) -> Unit,
    onBack:()->Unit
) {
    BackHandler(onBack=onBack)
    var crop by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            Button(onClick = {
                crop = true
            }) {
                Icon(
                    imageVector = Icons.Outlined.Done,
                    contentDescription = "crop",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) {
        ImageCropper(
            modifier = Modifier.padding(it),
            imageBitmap = imageBitmap,
            contentDescription = "",
            crop = crop,
            cropProperties = CropDefaults.properties(
                handleSize = 220f,
                cropOutlineProperty = CropOutlineProperty(
                    outlineType = OutlineType.Rect,
                    cropOutline = RectCropShape(1, "Custom") // 1:1 aspect ratio
                ),
                requiredSize = IntSize(Constants.EXPECTED_IMAGE_WIDTH, Constants.EXPECTED_IMAGE_HEIGHT) // final cropped size
            ),
            onCropStart = {
                Logger.off("MainActivity::Crop", "Started")
            },
            onCropSuccess = { imageBitmap ->
                val width = imageBitmap.width
                val height = imageBitmap.height
                onCropped(imageBitmap.asAndroidBitmap())
                Logger.off("MainActivity::Cropped", "($width,$height)")
                crop = false // Reset crop flag after success
            }
        )
    }

}