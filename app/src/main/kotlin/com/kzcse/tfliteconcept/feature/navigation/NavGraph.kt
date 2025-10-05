package com.kzcse.tfliteconcept.feature.navigation


import android.graphics.Bitmap
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.kzcse.tfliteconcept.feature._core.presentation.NavDestination
import com.kzcse.tfliteconcept.feature.home.HomeRoute
import com.kzcse.tfliteconcept.feature.misc.AboutUsPage
import com.kzcse.tfliteconcept.feature.misc.AppInfoPage
import com.kzcse.tfliteconcept.feature.misc.UserManualScreen
import com.kzcse.tfliteconcept.feature.recognize.ClassificationScreen
import com.kzcse.tfliteconcept.feature.recognize.GalleryScreen
import com.kzcse.tfliteconcept.feature.recognize.MediaPicker
import kotlinx.serialization.Serializable

private sealed interface Route2: NavKey {
   @Serializable data object Gallery: Route2
  @Serializable  data object Process: Route2
}

@Composable
fun ClassifierNavGraph(
    modifier: Modifier = Modifier
) {
    val backStack= rememberNavBackStack(Route2.Gallery)
    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Route2.Gallery -> {
                    NavEntry(key) {
                        NavigationViewModel.processImage?.let { bitmap ->
                            ClassificationScreen(
                                bitmap = bitmap,
                                navigationIcon = {
//                                    _DrawerIcon(
//                                        isNavRailMode = isNavRailMode,
//                                        onClick = openDrawerRequest
//                                    )
                                })

                        }
                    }
                }
                is Route2.Process -> {
                    NavEntry(key) {
                        NavigationViewModel.processImage?.let { bitmap ->
                            ClassificationScreen(
                                bitmap = bitmap,
                                navigationIcon = {
//                                    _DrawerIcon(
//                                        isNavRailMode = isNavRailMode,
//                                        onClick = openDrawerRequest
//                                    )
                                })
                        }

                    }
                }

                else -> throw RuntimeException("Invalid root")
            }
        }
    )
}

fun NavController.createNavGraph(
    isNavRailMode: Boolean,
    openDrawerRequest: () -> Unit,
    onAppInfoRequest: () -> Unit,
    onProcessRequest: (Bitmap) -> Unit,
    onMediaPickRequest: () -> Unit,
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
        composable(NavDestination.Recognize.route) {
            GalleryScreen(
                navigationIcon = {
                    _DrawerIcon(
                        isNavRailMode = isNavRailMode,
                        onClick = openDrawerRequest
                    )
                },
                onProcessRequest = onProcessRequest,
            )


        }
        composable(NavDestination.UseManual.route) {
            UserManualScreen {
                _DrawerIcon(
                    isNavRailMode = isNavRailMode,
                    onClick = openDrawerRequest
                )
            }

        }
        composable(NavDestination.AboutApp.route) {
            AppInfoPage {
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
//            MainViewModel.processImage?.let { bitmap ->
//                ClassificationScreen(
//                    bitmap = bitmap,
//                    navigationIcon = {
//                        _DrawerIcon(
//                            isNavRailMode = isNavRailMode,
//                            onClick = openDrawerRequest
//                        )
//                    })
//            }

        }
        composable(
            route = NavDestination.MediaPicker.route
        ) {
            MediaPicker(
                navigationIcon = {
                    _DrawerIcon(
                        isNavRailMode = isNavRailMode,
                        onClick = openDrawerRequest
                    )
                },
                onBitmapSelected = { bitmap ->
                    bitmap?.let(onProcessRequest)
                }
            )

        }

        composable(
            route = NavDestination.Crop.route
        ) {
//            ImageCropScreen(
//                bitmap = ,
//                onImageCropped = {
//                    man
//                }
//            )

        }


    }

}

@Composable
 fun _DrawerIcon(
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
