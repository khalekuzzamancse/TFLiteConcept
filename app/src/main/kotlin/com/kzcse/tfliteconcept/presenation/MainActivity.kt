package com.kzcse.tfliteconcept.presenation

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.kzcse.tfliteconcept.data.Classifier
import com.kzcse.tfliteconcept.presenation.core.AppTheme
import com.kzcse.tfliteconcept.presenation.core.GlobalMessenger
import com.kzcse.tfliteconcept.presenation.core.drawer.Destination
import com.kzcse.tfliteconcept.presenation.core.drawer.DrawerHeader
import com.kzcse.tfliteconcept.presenation.core.drawer.DrawerToNavRailDecorator
import com.kzcse.tfliteconcept.presenation.core.drawer.NavDestination
import com.kzcse.tfliteconcept.presenation.core.drawer.NavDestinationBuilder
import com.kzcse.tfliteconcept.presenation.core.drawer.NavigationEvent
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hostState = remember { SnackbarHostState() }
            LaunchedEffect(Unit) {
                GlobalMessenger.messageToUI.collect { msg ->
                    if (msg != null) {
                        hostState.showSnackbar(msg)
                    }

                }

            }
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = hostState)
                    }) { innerPadding ->
                    Column(Modifier.padding(innerPadding))
                    {
                        AppMainRoute()

                    }

                }
            }
        }
    }
}




@Composable
fun AppMainRoute(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val viewModel = remember { MainViewModel() }
    val navigator = remember { Navigator(navController) }
    var isNavRailMode by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        navController.currentBackStack.collect { entries ->
            val lastDestinationRoute =
                entries.lastOrNull { entry -> entry.destination.route != null }?.destination?.route
            val selected: Destination? =
                NavDestinationBuilder.allDestinations.find { it.route == lastDestinationRoute }
            if (selected != null)
                viewModel.select(selected)
            else
                viewModel.select(Destination.None)

        }
    }

    DrawerToNavRailDecorator(
        modifier = modifier,
        groups = NavDestinationBuilder.navGroups,
        controller = viewModel.controller,
        onEvent = { event ->
            if (event is NavigationEvent.Selected) {
                scope.launch { navigator.navigate(event.destination) }
            }
            if (event is NavigationEvent.NavRailNavigationMode)
                isNavRailMode = true
            if (event is NavigationEvent.DrawerNavigationMode)
                isNavRailMode = false
        },
        header = {
            DrawerHeader()
        },

        content = {
            NavHost(
                modifier = Modifier,
                navController = navController,
                graph = navController.createNavGraph(
                    isNavRailMode = isNavRailMode,
                    openDrawerRequest = viewModel::openDrawer,
                    onAppInfoRequest = {
                        navigator.navigate(NavDestination.AboutApp)
                    },
                    onProcessRequest = {
                        MainViewModel.processImage = it
                        navigator.navigate(NavDestination.Process)

                    },
                    onMediaPickRequest = {
                        navigator.navigate(NavDestination.MediaPicker)
                    }
                ),

                enterTransition = {
                    scaleIn(initialScale = 0.8f, animationSpec = tween(700)) + fadeIn(
                        animationSpec = tween(
                            700
                        )
                    )
                },
                exitTransition = {
                    scaleOut(
                        targetScale = 1.1f,
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                },
                popEnterTransition = {
                    scaleIn(initialScale = 1.2f, animationSpec = tween(700)) + fadeIn(
                        animationSpec = tween(
                            700
                        )
                    )
                },
                popExitTransition = {
                    scaleOut(
                        targetScale = 0.8f,
                        animationSpec = tween(700)
                    ) + fadeOut(animationSpec = tween(700))
                }
            )
        }
    )

}

fun testImageFromAssets(context: Context, assetName: String) {
    try {
        val inputStream = context.assets.open(assetName)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val result = Classifier(context).classifyOrThrow(bitmap)
        Log.d("com.kzcse.tfliteconcept.data.Classifier", "Result: $result")
    } catch (e: Exception) {
        Log.e(
            "com.kzcse.tfliteconcept.data.Classifier",
            "Error loading image from assets: ${e.stackTraceToString()}"
        )
    }
}