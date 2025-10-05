package com.kzcse.tfliteconcept.feature.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.kzcse.tfliteconcept.feature._core.presentation.Destination

object CustomNavigator{

    internal fun navigate(navController: NavHostController,destination: Destination) {
        navController.navigate(destination.route)
    }

    fun pop(navController: NavHostController,) {
        navController.popBackStack()
    }

     fun navigateAsTopMostDestination(navController: NavHostController,destination: String) {
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





