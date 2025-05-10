package com.kzcse.tfliteconcept.presenation

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.tfliteconcept.data.Classifier
import com.kzcse.tfliteconcept.domain.Logger
import com.kzcse.tfliteconcept.domain.CustomException
import com.kzcse.tfliteconcept.presenation.core.GlobalMessenger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassificationScreen(
    bitmap: Bitmap,
    navigationIcon: @Composable () -> Unit
) {
    val tag="ClassificationScreen";
    val context = LocalContext.current
    var result by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            delay(2000) // Simulate processing delay for better UX
            result = try {
                Classifier(context).classifyOrThrow(bitmap)
            } catch (e:Throwable){
                if(e is CustomException)
                    GlobalMessenger.updateMessage(e)

                Logger.error(tag,e)
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
