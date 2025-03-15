package com.kzcse.tfliteconcept.ui.u

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.tfliteconcept.R

@Preview
@Composable
private fun AppInfoPagePreview() {
    AppInfoPage{}
    
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppInfoPage(navigationIcon: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    navigationIcon()
                },
                modifier = Modifier.height(30.dp)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hilsha Fish Detection App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "This application is designed to detect various types of Hilsha fish using advanced AI models. The supported classes for detection are:",
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
            text = "Supported Hilsha Classes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),

            ) {


            // Chondona
            ClassItem(
                imageRes = R.drawable.chondona,
                className = "Chondona"
            )

            // Gurta
            ClassItem(
                imageRes = R.drawable.gurta,
                className = "Gurta"
            )

            // Healthy Ilish
            ClassItem(
                imageRes = R.drawable.healthy_ilish,
                className = "Healthy Ilish"
            )

            // Jhatka Ilish
            ClassItem(
                imageRes = R.drawable.jhatka_ilish,
                className = "Jhatka Ilish"
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
