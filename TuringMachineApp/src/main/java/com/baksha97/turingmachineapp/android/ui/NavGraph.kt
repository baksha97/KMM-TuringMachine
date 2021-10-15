/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.baksha97.owl.ui

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.baksha97.turingmachineapp.android.courses
import com.baksha97.turingmachineapp.android.ui.screens.CreateScreen
import com.baksha97.turingmachineapp.android.ui.screens.HomeScreen
import com.baksha97.turingmachineapp.android.ui.screens.RunMachineScreen
import com.baksha97.turingmachineapp.models.TuringMachine


/**
 * Destinations used in the ([TuringApp]).
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val MACHINE_ROUTE = "machine"
    const val MACHINE_ID_KEY = "machineId"
}

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.HOME_ROUTE,
) {
    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) { backStackEntry ->
            // Intercept back in Onboarding: make it finish the activity
            BackHandler {
                finishActivity()
            }

            HomeScreen() {
                actions.openMachine(it.id, backStackEntry)
            }
        }
        composable(
            "${MainDestinations.MACHINE_ROUTE}/{${MainDestinations.MACHINE_ID_KEY}}",
            arguments = listOf(
                navArgument(MainDestinations.MACHINE_ID_KEY) { type = NavType.StringType }
            )
        ) { backStackEntry: NavBackStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val currentCourseId = arguments.getString(MainDestinations.MACHINE_ID_KEY)
            RunMachineScreen(
                machineId = currentCourseId
            )
        }
    }
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {
    val onboardingComplete: () -> Unit = {
        navController.popBackStack()
    }

    // Used from HOME_ROUTE
    val openMachine = { machineId: String, from: NavBackStackEntry ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.MACHINE_ROUTE}/$machineId")
        }
    }

//    // Used from COURSE_DETAIL_ROUTE
//    val relatedCourse = { newCourseId: Long, from: NavBackStackEntry ->
//        // In order to discard duplicated navigation events, we check the Lifecycle
//        if (from.lifecycleIsResumed()) {
//            navController.navigate("${MainDestinations.MACHINE_ROUTE}/$newCourseId")
//        }
//    }

    // Used from COURSE_DETAIL_ROUTE
    val upPress: (from: NavBackStackEntry) -> Unit = { from ->
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigateUp()
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED
