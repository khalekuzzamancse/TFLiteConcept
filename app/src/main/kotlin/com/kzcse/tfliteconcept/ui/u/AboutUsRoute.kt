@file:Suppress("SpellCheckingInspection")

package com.kzcse.tfliteconcept.ui.u

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
private fun AboutPreview() {
    AboutUsPage({})

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsPage(navigationIcon: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title={},
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
            // "Supervised by" Heading
            SectionHeading()

            Spacer(modifier = Modifier.height(8.dp))

            // Supervisor Section
            SupervisorSection()
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider()

            Spacer(modifier = Modifier.height(24.dp))

            AIDeveloperSection()
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider()

            Spacer(modifier = Modifier.height(24.dp))

            // Developer Section
            DeveloperSection()
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(24.dp))

            // Copyright Notice
            CopyrightNotice()
        }
    }
}

@Composable
private fun SectionHeading() {
    Text(
        text = "Supervised by",
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary
    )
}


@Composable
private fun SupervisorSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            res = R.drawable.super_vicer,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sk. Shalauddin Kabir",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Lecturer",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        DeptAndUniversity()

        Text(
            text = "B.Sc. (Engg.) & M.Sc. (Engg.) in CSE (JUST)",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun AIDeveloperSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "AI Development by",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            res = R.drawable.ai_developer,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Shahadat Hossain",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
//        Text(
//            text = "Student",
//            fontSize = 16.sp,
//            color = MaterialTheme.colorScheme.secondary
//        )
//        DeptAndUniversity()

    }
}
@Composable
private fun DeveloperSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "App Developed by",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(
            res = R.drawable.developer,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Md. Khalekuzzman",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Student",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        DeptAndUniversity()

    }
}

@Composable
fun DeptAndUniversity(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Department of  CSE at Jashore University of Science and Technology (JUST)",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }

}


@Composable
fun CopyrightNotice() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 16.dp)
    ) {
        // Display the university logo
        Image(
            res = R.drawable.just_logo,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Copyright and University Attribution Text
        Text(
            text = "© 2025, Jashore University of Science and Technology (JUST)",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Hilsha Recoginer App",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Developed in the Department of Computer Science and Engineering(CSE)",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}



@Composable
private fun Image(modifier: Modifier = Modifier, res: Int) {
    Image(
        modifier = modifier,
        painter = painterResource(res),
        contentDescription = null
    )
}
