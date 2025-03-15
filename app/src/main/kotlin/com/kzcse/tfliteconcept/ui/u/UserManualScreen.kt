package com.kzcse.tfliteconcept.ui.u

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Crop
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserManualScreen(
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
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ManualTitle()
            Spacer(modifier = Modifier.height(16.dp))
            InstructionStep(
                stepNumber = 1,
                title = "Pick an Image",
                description = "You can select an image directly from your device’s camera or gallery.",
                icon = Icons.Outlined.Camera
            )

            InstructionStep(
                stepNumber = 2,
                title = "Crop the Image",
                description = "Ensure the selected image is cropped to **224x224 pixels**, focusing only on the fish. This improves detection accuracy.",
                icon = Icons.Outlined.Crop
            )

            InstructionStep(
                stepNumber = 3,
                title = "AI Detection",
                description = "Once cropped, the AI will analyze the image and detect the type of Hilsha fish among these five classes:\n\n" +
                        "✔ Chondona\n✔ Gurta\n✔ Healthy Ilish\n✔ Jhatka Ilish\n✔ Others",
                icon = Icons.Outlined.Search
            )

            InstructionStep(
                stepNumber = 4,
                title = "Results & Information",
                description = "The app will display the detected fish type along with additional information about that class.",
                icon = Icons.Outlined.Info
            )


        }
    }
}

@Composable
fun ManualTitle() {
    Text(
        text = "How to Use the Hilsha Detector",
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun InstructionStep(
    stepNumber: Int,
    title: String,
    description: String,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Step $stepNumber: $title",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }
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
        Icon(Icons.Outlined.Home, contentDescription = "Home")
        Spacer(Modifier.width(4.dp))
        Text(text = "Back to Home", color = MaterialTheme.colorScheme.onPrimary)
    }
}
