package com.kzcse.tfliteconcept.ui.u

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.kzcse.tfliteconcept.ui.drawer.Destination

class Navigator(
    private val navController: NavHostController,
) {

    internal fun navigate(destination: Destination) {
        navigateAsTopMostDestination(destination.route)

    }

    fun pop() {
        navController.popBackStack()
    }

    private fun navigateAsTopMostDestination(destination: String) {
        return try {
            println("Navigate:$destination")
            navController.navigate(destination) {
                navController.graph.findStartDestination().route?.let {
                    popUpTo(it) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true

            }


        } catch (e: Exception) {
            println("NavigateFail:$e")
        }
    }


}





