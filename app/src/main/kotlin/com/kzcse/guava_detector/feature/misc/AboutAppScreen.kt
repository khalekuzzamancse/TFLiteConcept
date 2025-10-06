package com.kzcse.guava_detector.feature.misc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.guava_detector.feature._core.presentation.ScreenStrategy
import  com.kzcse.guava_detector.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(bottomBar: @Composable () -> Unit, fab: @Composable () -> Unit) {
    ScreenStrategy(
        bottomBar = bottomBar,
        fab = fab,
        title = {
            Text(
                text = "Guava Detection App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    ) {
        Column(
            modifier = it
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "This application is designed to detect various types of Guava using advanced AI models. The supported classes for detection are:",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(8.dp))
            SupportedClassesSection()


            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Technical Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Our model was trained using TensorFlow and PyTorch frameworks and later converted to TensorFlow Lite for seamless mobile integration. TensorFlow Lite enables efficient inference on mobile devices, ensuring faster detection without compromising accuracy.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Justify
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            CopyrightNotice()
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SupportedClassesSection() {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Supported Guava Classes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),

            ) {

            ClassItem(
                imageRes = R.drawable.class_immature,
                className = "Immature"
            )

            ClassItem(
                imageRes = R.drawable.class_mature,
                className = "Mature"
            )

              ClassItem(
                imageRes = R.drawable.class_ripe,
                className = "Ripe"
            )

            ClassItem(
                imageRes = R.drawable.class_overripe,
                className = "Over Ripe"
            )

            Spacer(modifier = Modifier.height(12.dp))
        }
    }

}

@Composable
fun ClassItem(imageRes: Int, className: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = className,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = className,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
