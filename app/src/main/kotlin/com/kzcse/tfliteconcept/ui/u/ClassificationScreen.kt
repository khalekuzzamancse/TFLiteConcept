package com.kzcse.tfliteconcept.ui.u

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.tfliteconcept.Classifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassificationScreen(
    bitmap: Bitmap,
    navigationIcon: @Composable () -> Unit
) {
    val context = LocalContext.current
    var result by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(5000) // Simulate processing delay for better UX
            result = try {
                Classifier(context).classifyImage(bitmap)

            } catch (e:Exception){
                null
            } finally {
                isLoading = false
            }

        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Classification Result") },
                navigationIcon = navigationIcon
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            ImageWithProgress(
                bitmap = bitmap,
                isLoading = isLoading,
                isSuccess = result != null
            )

            Spacer(modifier = Modifier.height(24.dp))

            DisplayResult(result, isLoading)
        }
    }
}

@Composable
fun ImageWithProgress(bitmap: Bitmap, isLoading: Boolean, isSuccess: Boolean) {
    val borderColor = when {
        isLoading -> MaterialTheme.colorScheme.surfaceVariant // Neutral color during loading
        isSuccess -> MaterialTheme.colorScheme.primary         // Green border on success
        else -> MaterialTheme.colorScheme.error                // Red border on failure
    }

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp))
            .border(3.dp, borderColor, RoundedCornerShape(12.dp))
    ) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "Classified Image",
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        )

        if (isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .height(10.dp)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp).align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun DisplayResult(result: String?, isLoading: Boolean) {
    when {
        isLoading -> Text(
            text = "Classifying the image...",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        result == null -> Text(
            text = "Prediction Failed",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        else -> Text(
            text = "Result: $result",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}

@Composable
fun ScannerProgressBar(
    modifier: Modifier = Modifier,
    barColor: Color = MaterialTheme.colorScheme.primary,
    scanSpeed: Int = 1500
) {
    val infiniteTransition = rememberInfiniteTransition()
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(scanSpeed, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val canvasHeight = size.height
        val progressY = canvasHeight * animatedOffset

        drawLine(
            color = barColor,
            start = Offset(0f, progressY),
            end = Offset(size.width, progressY),
            strokeWidth = 8f,
            cap = StrokeCap.Round
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScannerProgressBar() {

}
