@file:Suppress("NewApi")

package com.kzcse.hilsha_detector.feature._navigation

import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.kzcse.hilsha_detector.feature._core.logic.Logger
import com.kzcse.hilsha_detector.feature._core.presentation.BottomBarItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable

sealed interface  NonTopRoute{}
sealed interface Route {
    val route: String

    @Serializable
    data object Home : NavKey, Route {
        override val route = "Home"
    }

    @Serializable
    data object Recognize : NavKey, Route {
        override val route = "Recognize"
    }

    @Serializable
    data object UserManual : NavKey, Route {
        override val route ="UserManual"
    }

    @Serializable
    data object AboutApp : NavKey, Route {
        override val route = "AppInfo"
    }

    @Serializable
    data object AboutUs : NavKey, Route {
        override val route ="AboutUs"
    }

    @Serializable
    data object Process : NavKey, Route, NonTopRoute {
        override val route = "Process"
    }
}

class NavigationViewModel() : ViewModel() {
    val backStack: NavBackStack = mutableStateListOf(Route.Home)
    private val _selected = MutableStateFlow(BottomBarItem.Home)
    val selected = _selected.asStateFlow()
    companion object {
        var processImage: Bitmap? = null
    }

    /** Set the bitmap before navigation, passing bitmap via navigation is
    complex that is why doing this ...**/
    fun onSelect(destination: String) {
        Logger.Companion.on(tag = "Route","onSelected->else:$destination")
        when {
            Route.Home.route == destination -> {
                pushIfNotExist(Route.Home)
                _selected.update { BottomBarItem.Home }
            }
            Route.Recognize.route == destination -> {
                pushIfNotExist(Route.Recognize)
                _selected.update { BottomBarItem.Recognize }
            }
            Route.UserManual.route == destination -> {
                pushIfNotExist(Route.UserManual)
                _selected.update { BottomBarItem.UserManual }
            }
            Route.AboutApp.route == destination -> {
                pushIfNotExist(Route.AboutApp)
                    _selected.update { BottomBarItem.AboutApp }
            }
            Route.AboutUs.route == destination ->{
                pushIfNotExist(Route.AboutUs)
                _selected.update { BottomBarItem.AboutUs }
            }
            Route.Process.route == destination -> {
                backStack.add(Route.Process)

            }
            else -> {
                Logger.Companion.on(tag = "Route","onSelected->else:$destination")
            }
        }

    }


     fun pop(){
        backStack.removeAt(backStack.lastIndex)
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
        _selected.update { BottomBarItem.Home }
    }


}
