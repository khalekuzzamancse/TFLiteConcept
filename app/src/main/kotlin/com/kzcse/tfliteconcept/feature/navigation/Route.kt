@file:Suppress("NewApi")

package com.kzcse.tfliteconcept.feature.navigation

import android.app.Activity
import android.graphics.Bitmap
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.kzcse.tfliteconcept.feature._core.logic.Logger
import com.kzcse.tfliteconcept.feature._core.presentation.DrawerHeader
import com.kzcse.tfliteconcept.feature._core.presentation.DrawerToNavRailDecorator
import com.kzcse.tfliteconcept.feature._core.presentation.NavDestination
import com.kzcse.tfliteconcept.feature._core.presentation.NavDestinationBuilder
import com.kzcse.tfliteconcept.feature._core.presentation.NavigationDrawerController
import com.kzcse.tfliteconcept.feature._core.presentation.NavigationEvent
import com.kzcse.tfliteconcept.feature.home.HomeRoute
import com.kzcse.tfliteconcept.feature.misc.AboutUsPage
import com.kzcse.tfliteconcept.feature.misc.AppInfoPage
import com.kzcse.tfliteconcept.feature.misc.UserManualScreen
import com.kzcse.tfliteconcept.feature.recognize.ClassificationScreen
import com.kzcse.tfliteconcept.feature.recognize.GalleryScreen
import kotlinx.serialization.Serializable
sealed interface  NonTopRoute{}
sealed interface Route {
    val route: String

    @Serializable
    data object Home : NavKey, Route {
        override val route = NavDestination.Home.route
    }

    @Serializable
    data object Recognize : NavKey, Route {
        override val route = NavDestination.Recognize.route
    }

    @Serializable
    data object UserManual : NavKey, Route {
        override val route = NavDestination.UseManual.route
    }

    @Serializable
    data object AppInfo : NavKey, Route {
        override val route = NavDestination.AboutApp.route
    }

    @Serializable
    data object AboutUs : NavKey, Route {
        override val route = NavDestination.AboutUs.route
    }

    @Serializable
    data object Process : NavKey, Route, NonTopRoute {
        override val route = "Process"
    }
}

class NavigationViewModel() : ViewModel() {
    val backStack: NavBackStack = mutableStateListOf()
    val controller = NavigationDrawerController()
    fun openDrawer() = controller.openDrawer()

    init {
        backStack.add(Route.Home)
        controller.select(NavDestination.Home.route)
    }

    companion object {
        var processImage: Bitmap? = null
    }

    /** Set the bitmap before navigation, passing bitmap via navigation is
    complex that is why doing this ...**/
    fun onSelect(destination: String) {
        controller.select(destination)
        when {
            Route.Home.route == destination -> pushIfNotExist(Route.Home)
            Route.Recognize.route == destination -> pushIfNotExist(Route.Recognize)
            Route.UserManual.route == destination -> pushIfNotExist(Route.UserManual)
            Route.AppInfo.route == destination -> pushIfNotExist(Route.AppInfo)
            Route.AboutUs.route == destination -> pushIfNotExist(Route.AboutUs)
            Route.Process.route == destination -> backStack.add(Route.Process)
            else -> {

            }
        }

    }


    private fun pushIfNotExist(route: NavKey) {
        if (backStack.lastOrNull() != route) {
            backStack.add(route)
        }
    }

    fun onBack() {
        if(backStack.lastOrNull() is NonTopRoute){
            backStack.removeAt(backStack.lastIndex)
            return
        }
        if (Route.Home !in backStack) {
            backStack.clear()
            backStack.add(Route.Home)
        } else {
            // Pop everything until only Home remains
            while (backStack.size > 1) {
                backStack.removeAt(backStack.lastIndex)
            }
        }
        // Always update selected to Home
        controller.select(NavDestination.Home.route)

    }


}

@Composable
fun NavigationRoot() {
    val viewModel = viewModel { NavigationViewModel() }
    val backStack = viewModel.backStack

    var backPressCountOnHome = remember { 0 }
    val context = LocalContext.current
    BackHandler {
        Logger.on(tag = "Route", "onBack")
        if (backStack.size == 1) {
            backPressCountOnHome++
        } else {
            backPressCountOnHome = 0//reset
        }
        if (backPressCountOnHome >= 2) {
            (context as? Activity)?.finish()
        }
        viewModel.onBack()


    }
    DrawerToNavRailDecorator(
        modifier = Modifier,
        groups = NavDestinationBuilder.navGroups,
        controller = viewModel.controller,
        onEvent = { event ->
            if (event is NavigationEvent.Selected) {
                Logger.on(tag = "Route", "$event")
                viewModel.onSelect(event.destination)
            }
        },
        header = {
            DrawerHeader()
        },

        content = {
            NavigationRoot(
                modifier = Modifier,
                viewModel = viewModel,
                isNavRailMode = false,
                openDrawerRequest = viewModel::openDrawer
            )

        }
    )
}

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    viewModel: NavigationViewModel,
    isNavRailMode: Boolean = false,
    openDrawerRequest: () -> Unit
) {
    val backStack = viewModel.backStack

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        onBack = {
            viewModel.onBack()
        },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
            rememberSceneSetupNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Route.Home -> {
                    NavEntry(key) {
                        HomeRoute(
                            onAppInfoRequest = {
                                viewModel.onSelect(NavDestination.AboutApp.route)
                            },
                            navigationIcon = {
                                _DrawerIcon(
                                    isNavRailMode = isNavRailMode,
                                    onClick = openDrawerRequest
                                )
                            }
                        )
                    }
                }

                is Route.Recognize -> {
                    NavEntry(key) {
                        GalleryScreen(
                            navigationIcon = {
                                _DrawerIcon(
                                    isNavRailMode = isNavRailMode,
                                    onClick = openDrawerRequest
                                )
                            },
                            onProcessRequest = {
                                NavigationViewModel.processImage = it
                                viewModel.onSelect(NavDestination.Process.route)
                            },
                        )
                    }
                }

                is Route.Process -> {
                    NavEntry(key) {
                        NavigationViewModel.processImage?.let { bitmap ->
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

                is Route.UserManual -> {
                    NavEntry(key) {
                        UserManualScreen {
                            _DrawerIcon(
                                isNavRailMode = isNavRailMode,
                                onClick = openDrawerRequest
                            )
                        }
                    }
                }

                is Route.AppInfo -> {
                    NavEntry(key) {
                        AppInfoPage {
                            _DrawerIcon(
                                isNavRailMode = isNavRailMode,
                                onClick = openDrawerRequest
                            )
                        }
                    }
                }

                is Route.AboutUs -> {
                    NavEntry(key) {
                        AboutUsPage(navigationIcon = {
                            _DrawerIcon(
                                isNavRailMode = isNavRailMode,
                                onClick = openDrawerRequest
                            )
                        })
                    }
                }

                else -> throw RuntimeException("Invalid root")
            }
        }
    )

}
