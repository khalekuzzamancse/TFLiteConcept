package com.kzcse.tfliteconcept.ui.u

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.tfliteconcept.R

val galleryImages = listOf(
    R.drawable.chondona_01,
    R.drawable.chondona_02,
    R.drawable.gurta_01,
    R.drawable.gurta_02,
    R.drawable.healthy_02,
    R.drawable.healthy_02,
    R.drawable.jhatka_01,
    R.drawable.jhatka_02,
    R.drawable.other_01,
    R.drawable.other_02
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun GalleryScreen(
    navigationIcon: @Composable () -> Unit,
    onImageClick: (Bitmap) -> Unit,
    onNavigation: () -> Unit,
) {
    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        val bitmap = uri?.let {
            if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                ImageDecoder.decodeBitmap(source)
            }
        }
        if (bitmap != null) {
            onImageClick(bitmap)
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            onImageClick(bitmap)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Gallery",
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.primary,
                    )
                },
                navigationIcon = navigationIcon
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                BottomSheetItem(
                    label = "From Gallery",
                    icon = Icons.Filled.Photo,
                    onClick = {
                        galleryLauncher.launch("image/*")
                    }
                )
                BottomSheetItem(
                    label = "From Camera",
                    icon = Icons.Filled.CameraAlt,
                    onClick = {
                        cameraLauncher.launch(null)
                    }
                )
            }
            Spacer(Modifier.height(4.dp))
            HorizontalDivider()
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Pick from data set",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 16.sp
            )
            Spacer(Modifier.height(8.dp))
            ImageGallery(
                images = galleryImages,
                onImageClick = onImageClick
            )
        }
    }
}


@Composable
fun ImageGallery(
    images: List<Int>,
    onImageClick: (Bitmap) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(images) { imageRes ->
            ImageItem(imageRes = imageRes, onImageClick)
        }
    }
}

@Composable
fun ImageItem(imageRes: Int, onImageClick: (Bitmap) -> Unit) {
    val bitmap = BitmapFactory.decodeResource(
        LocalContext.current.resources, imageRes
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(12.dp))
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(12.dp))
            .clickable { onImageClick(bitmap) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Gallery Image",
            modifier = Modifier.fillMaxSize()
        )
    }
}
@Composable
fun BottomSheetItem(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier=Modifier
                .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(4.dp))
                .padding(8.dp)
        ) {
            Icon(
                imageVector = icon,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = label,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = label, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}
