package com.kzcse.tfliteconcept.ui.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PermDeviceInformation
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PermDeviceInformation

//TODO

sealed interface NavDestination : Destination {
    data object Home : NavDestination {
        override val route = "Home"
    }

    data object UseManual : NavDestination {
        override val route = "UseManual"
    }

    data object AboutApp : NavDestination {
        override val route = "AboutApp"
    }

    data object Recognize : NavDestination {
        override val route = "ImageGallery"
    }
    data object Process : NavDestination {
        override val route = "Process"
    }
    data object MediaPicker : NavDestination {
        override val route = "MediaPicker"
    }

    data object AboutUs : NavDestination {
        override val route = "AboutUs"
    }
}

object NavDestinationBuilder {

    val navGroups = listOf(group1(), group2(), group3(), group6())

    val allDestinations: List<Destination> = navGroups.flatMap { group ->
        group.items.map { it.destination }
    }

    private fun group1() = NavGroup(
        items = listOf(
            NavigationItem(
                label = "Home",
                unFocusedIcon = Icons.Outlined.Home,
                focusedIcon = Icons.Filled.Home,
                destination = NavDestination.Home
            ),
        )
    )

    private fun group2() = NavGroup(
        items = listOf(
            NavigationItem(
                label = "User Manual",
                unFocusedIcon = Icons.AutoMirrored.Outlined.MenuBook,
                focusedIcon =Icons.AutoMirrored.Filled.MenuBook,
                destination = NavDestination.UseManual
            ),
            NavigationItem(
                label = "About App",
                unFocusedIcon = Icons.Outlined.PermDeviceInformation,
                focusedIcon = Icons.Filled.PermDeviceInformation,
                destination = NavDestination.AboutApp
            ),
        )
    )

    private fun group3() = NavGroup(
        items = listOf(
            NavigationItem(
                label = "Recognize",
                unFocusedIcon = Icons.Outlined.Fingerprint,
                focusedIcon = Icons.Filled.Fingerprint,
                destination = NavDestination.Recognize
            ),

        )
    )


    private fun group6() = NavGroup(
        items = listOf(
            NavigationItem(
                label = "About Us",
                unFocusedIcon = Icons.Outlined.Info,
                focusedIcon = Icons.Filled.Info,
                destination = NavDestination.AboutUs
            ),
        )
    )
}
