@file:Suppress("NewApi")

package com.kzcse.guava_detector.feature.navigation
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.kzcse.guava_detector.core.ui.SpacerHorizontal
import com.kzcse.guava_detector.core.ui.VoidComposable
import com.kzcse.guava_detector.feature._core.presentation.BottomBar
import com.kzcse.guava_detector.feature.home.HomeRoute
import com.kzcse.guava_detector.feature.misc.AboutAppScreen
import com.kzcse.guava_detector.feature.misc.AboutUsPage
import com.kzcse.guava_detector.feature.misc.UserManualScreen
import com.kzcse.guava_detector.feature.recognize.ClassificationScreen
import com.kzcse.guava_detector.feature.recognize.GalleryScreen
import  com.kzcse.guava_detector.R


@Composable
fun NavigationRootX(
    modifier: Modifier = Modifier
) {

    val viewModel = viewModel { NavigationViewModel() }
    val selected = viewModel.selected.collectAsState().value
    var backPressCountOnHome = remember { 0 }
    val context = LocalContext.current
    val backStack=viewModel.backStack
    BackHandler {
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

    val bottomBar: VoidComposable = remember(selected) {
        {
            BottomBar(
                selectedRoute = selected,
                onHomeClick = {
                    viewModel.onSelect(Route.Home.route)
                },
                onManualRequest = {
                    viewModel.onSelect(Route.UserManual.route)
                },
                onRecognizeRequest = {
                    viewModel.onSelect(Route.Recognize.route)
                },
                onAboutUsRequest = {
                    viewModel.onSelect(Route.AboutUs.route)
                },
                onAboutAppRequest = {
                    viewModel.onSelect(Route.AboutApp.route)
                }
            )
        }
    }
    val fab: VoidComposable =remember {
        @Composable {
            FloatingActionButton(
                onClick = {
                    viewModel.onSelect(Route.Recognize.route)
                }
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ){
                    Text(
                        text = "Recognize",
                    )

                }

            }
        }
    }
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
                                viewModel.onSelect(Route.AboutUs.route)
                            },
                            bottomBar = bottomBar,
                            fab = fab
                        )
                    }
                }

                is Route.UserManual -> {
                    NavEntry(key) {
                        UserManualScreen(
                            bottomBar = bottomBar,
                            fab = {}
                        )
                    }
                }

                is Route.Recognize -> {
                    NavEntry(key) {
                        GalleryScreen(
                            bottomBar = bottomBar,
                            onProcessRequest = {
                                NavigationViewModel.processImage = it
                                viewModel.onSelect(Route.Process.route)
                            },
                        )
                    }
                }
                is Route.Process -> {
                    NavEntry(key) {
                        NavigationViewModel.processImage?.let { bitmap ->
                            ClassificationScreen(
                                bitmap = bitmap,
                                navigationIcon = {})
                        }

                    }
                }
                is Route.AboutUs -> {
                    NavEntry(key) {
                        AboutUsPage(
                            bottomBar = bottomBar,
                            fab = { }
                        )

                    }
                }
                is Route.AboutApp -> {
                    NavEntry(key) {
                        AboutAppScreen(
                            bottomBar = bottomBar,
                            fab = { }
                        )

                    }
                }

                else -> throw kotlin.RuntimeException("Invalid root")
            }
        }
    )

}

