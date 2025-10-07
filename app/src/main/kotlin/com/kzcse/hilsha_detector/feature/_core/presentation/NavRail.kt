package com.kzcse.hilsha_detector.feature._core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.hilsha_detector.R
import com.kzcse.hilsha_detector.core.ui.SpacerHorizontal
import com.kzcse.hilsha_detector.core.ui.TextView

@Composable
fun NavRail(
    modifier: Modifier = Modifier,
    selectedRoute: BottomBarItem,
    onHomeClick: () -> Unit,
    onManualRequest: () -> Unit,
    onRecognizeRequest: () -> Unit,
    onAboutUsRequest: () -> Unit,
    onAboutAppRequest: () -> Unit,
) {
    Surface(
        shadowElevation = 16.dp,
        tonalElevation = 16.dp
    ) {
        Column(
            modifier = modifier
                .width(IntrinsicSize.Max)
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            _NavRailItem(
                resId = R.drawable.ic_home,
                onClick = onHomeClick,
                selected = selectedRoute == BottomBarItem.Home,
                label = "Home",
                onPositioned = {}
            )
            _NavRailItem(
                resId = R.drawable.ic_user_manual,
                onClick = onManualRequest,
                selected = selectedRoute == BottomBarItem.UserManual,
                label = "Manual",
                onPositioned = {}
            )
            _NavRailItem(
                resId = R.drawable.ic_about_app,
                onClick = onAboutAppRequest,
                selected = selectedRoute == BottomBarItem.AboutApp,
                label = "About App",
                onPositioned = {}
            )
            _NavRailItem(
                resId = R.drawable.ic_about_us,
                onClick = onAboutUsRequest,
                selected = selectedRoute == BottomBarItem.AboutUs,
                label = "About Us",
                onPositioned = {}
            )
        }
    }

}

@Composable
fun _NavRailItem(
    modifier: Modifier = Modifier,
    label: String,
    resId: Int,
    selected: Boolean,
    onClick: () -> Unit,
    onPositioned:(IntOffset)-> Unit
) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clickable{
            onClick()
        }

    ) {
        Icon(
            modifier = modifier
                .size(25.dp),
            painter = painterResource(resId),
            contentDescription = label,
            tint = if (selected) MaterialTheme.colorScheme.primary
            else  MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )
        SpacerHorizontal(16)
        TextView(
            text = label,
            color = if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
            fontWeight = if (selected) FontWeight.W500 else FontWeight.W400,
            maxLines = 1,
            fontSize = 13.sp,
            modifier = Modifier
        )
    }


}

