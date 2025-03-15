package com.kzcse.tfliteconcept.ui.u

import android.graphics.Bitmap
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph

import com.kzcse.tfliteconcept.ui.drawer.NavDestination



fun NavController.createNavGraph(
    isNavRailMode: Boolean,
    openDrawerRequest: () -> Unit,
    onAppInfoRequest:()->Unit,
    onProcessRequest:(Bitmap)->Unit,
): NavGraph {
    return createGraph(startDestination = NavDestination.Home.route) {
        composable(NavDestination.Home.route) {
            HomeRoute(
                onAppInfoRequest = onAppInfoRequest,
                navigationIcon = {
                    _DrawerIcon(
                        isNavRailMode = isNavRailMode,
                        onClick = openDrawerRequest
                    )
                }
            )

        }
        composable(NavDestination.ImageGallery.route) {
           GalleryScreen(
                navigationIcon={
                    _DrawerIcon(
                        isNavRailMode = isNavRailMode,
                        onClick = openDrawerRequest
                    )
                },
                onImageClick=onProcessRequest
            )


        }
        composable(NavDestination.UseManual.route) {
            UserManualScreen{
                _DrawerIcon(
                    isNavRailMode = isNavRailMode,
                    onClick = openDrawerRequest
                )
            }

        }
        composable(NavDestination.AboutApp.route) {
            AppInfoPage{
                _DrawerIcon(
                    isNavRailMode = isNavRailMode,
                    onClick = openDrawerRequest
                )
            }
        }
        composable(NavDestination.AboutUs.route) {

            AboutUsPage(navigationIcon = {
                _DrawerIcon(
                    isNavRailMode = isNavRailMode,
                    onClick = openDrawerRequest
                )
            })
        }
        composable(
           route = NavDestination.Process.route
        ) {
            MainViewModel.processImage?.let { bitmap->
                ClassificationScreen(
                    bitmap = bitmap,
                    navigationIcon = {
                    _DrawerIcon(
                        isNavRailMode = isNavRailMode,
                        onClick = openDrawerRequest
                    )
                })
            }

        }




    }

}

@Composable
private fun _DrawerIcon(
    modifier: Modifier = Modifier,
    isNavRailMode: Boolean,
    onClick: () -> Unit,
) {
    if (!isNavRailMode) {
        IconButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            Icon(Icons.Default.Menu, contentDescription = "navigation")
        }
    }
}
