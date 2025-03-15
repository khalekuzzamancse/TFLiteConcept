package com.kzcse.tfliteconcept.ui.u

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    navigationIcon: @Composable () -> Unit,
    onImageClick: (Bitmap) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Gallery",
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 16.dp)
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


            ImageGallery(
                images = galleryImages,
                onImageClick = onImageClick
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
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
        androidx.compose.ui.platform.LocalContext.current.resources, imageRes
    )

    Box(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
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
