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

package com.baksha97.turingmachineapp.android

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.baksha97.owl.ui.NavGraph
import com.baksha97.owl.ui.theme.YellowTheme
import com.baksha97.turingmachineapp.models.factories.MachineFactory
import com.baksha97.turingmachineapp.models.TuringMachine
import com.google.accompanist.insets.ProvideWindowInsets


@Composable
fun TuringApp(finishActivity: () -> Unit) {
    ProvideWindowInsets {
        YellowTheme {
            val navController = rememberNavController()
            Scaffold(
                backgroundColor = MaterialTheme.colors.primary,
            ) { innerPaddingModifier ->
                NavGraph(
                    finishActivity = finishActivity,
                    navController = navController,
                    modifier = Modifier.padding(innerPaddingModifier)
                )
            }
        }
    }
}

val factory: MachineFactory = MachineFactory()
val courses = listOf(
    factory.makeTuringMachine("1", 30, listOf(5,3), "1,B,R,2"),
    factory.makeTuringMachine("2", 30, listOf(5,3), "1,B,R,2"),
    factory.makeTuringMachine("3", 30, listOf(5,3), "1,B,R,2"),
    factory.makeTuringMachine("4", 30, listOf(5,3), "1,B,R,2"),
    factory.makeTuringMachine("5", 30, listOf(5,3), "1,B,R,2"),
    factory.makeTuringMachine("6", 30, listOf(5,3), "1,B,R,2")
)