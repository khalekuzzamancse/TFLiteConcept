package com.kzcse.tfliteconcept.ui.u

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeRoute(
    onAppInfoRequest: () -> Unit,
    navigationIcon: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = navigationIcon
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeTitle()
            Spacer(modifier = Modifier.height(16.dp))
            HomeIntroduction()
            Spacer(modifier = Modifier.height(24.dp))
            FeatureList() // Modified Feature Section
            Spacer(modifier = Modifier.height(24.dp))
            CallToActionButton(
                onClick = onAppInfoRequest
            )
        }
    }
}

@Composable
private fun HomeTitle() {
    Text(
        text = "Welcome to Hilsha Fish Detector",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun HomeIntroduction() {
    Text(
        text = "Detect Hilsha fish with ease! Our AI model identifies five different Hilsha classes accurately.",
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun FeatureList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max)
    ) {
        FeaturesSection(
            title = "Supported Features", features = listOf(
                "✔ Detects 5 Hilsha classes: Chondona, Gurta, Healthy Ilish, Jhatka Ilish, and Others",
                "✔ Capture images directly from the camera",
                "✔ Select images from your device's gallery",
                "✔ Use images from the app’s integrated gallery"
            )
        )
    }
}

@Composable
private fun FeaturesSection(title: String, features: List<String>) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        features.forEach { feature ->
            FeatureItem(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                text = feature
            )
        }
    }
}

@Composable
private fun FeatureItem(modifier: Modifier, text: String) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun CallToActionButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Icon(Icons.Outlined.Info, contentDescription = "about us")
        Spacer(Modifier.width(4.dp))
        Text(text = "More Information", color = MaterialTheme.colorScheme.onPrimary)
    }
}
