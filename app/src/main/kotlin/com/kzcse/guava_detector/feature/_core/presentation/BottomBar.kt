@file:Suppress("ComposableNaming","Unused")

package com.kzcse.guava_detector.feature._core.presentation
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kzcse.guava_detector.core.ui.SpacerVertical
import com.kzcse.guava_detector.core.ui.TextView
import  com.kzcse.guava_detector.R

enum class BottomBarItem{
    Home, UserManual, Recognize, AboutUs,AboutApp
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedRoute: BottomBarItem,
    onHomeClick: () -> Unit,
    onManualRequest: () -> Unit,
    onRecognizeRequest: () -> Unit,
    onAboutUsRequest: () -> Unit,
    onAboutAppRequest: () -> Unit,
) {

    Surface(
        modifier = modifier,
    ) {
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFF4F7FC)),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                _BarItem(
                    resId = R.drawable.ic_home,
                    onClick = onHomeClick,
                    selected = selectedRoute == BottomBarItem.Home,
                    label = "Home",
                    onPositioned = {

                    }
                )
                _BarItem(
                    resId = R.drawable.ic_user_manual,
                    onClick = onManualRequest,
                    selected = selectedRoute == BottomBarItem.UserManual,
                    label = "Manual",
                    onPositioned = {

                    }
                )

                _BarItem(
                    resId = R.drawable.ic_about_app,
                    onClick = onAboutAppRequest,
                    selected = selectedRoute == BottomBarItem.AboutApp,
                    label = "About App",
                    onPositioned = {

                    }
                )
                _BarItem(
                    resId = R.drawable.ic_about_us,
                    onClick = onAboutUsRequest,
                    selected = selectedRoute == BottomBarItem.AboutUs,
                    label = "About Us",
                    onPositioned = {

                    }
                )
            }
        }

    }
    }



@Composable
fun _BarItem(
    modifier: Modifier = Modifier,
    label: String,
    resId: Int,
    selected: Boolean,
    onClick: () -> Unit,
    onPositioned:(IntOffset)-> Unit
) {
    Column(
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = Modifier


    ) {
        Icon(
            modifier = modifier
                .size(25.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onClick()
                },
            painter = painterResource(resId),
            contentDescription = label,
            tint = if (selected) MaterialTheme.colorScheme.primary
            else Color.Black.copy(alpha = 0.8f),
        )
        SpacerVertical(4)
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

