package org.dosys.project.presentation.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.dosys.project.presentation.feature.todo.TodoMainScreen
import org.dosys.project.presentation.feature.weather.main.WeatherMainScreen
import org.dosys.project.presentation.navigation.NavigationScreens
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainFlowScreen(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationScreens.valueOf(
        backStackEntry?.destination?.route ?: NavigationScreens.WeatherMain.name
    )
    val mainScreens = listOf(NavigationScreens.WeatherMain, NavigationScreens.TodoMain) // 바텀바 보여야 함
    val showBottomBar = (currentScreen in listOf(NavigationScreens.WeatherMain, NavigationScreens.TodoMain))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(currentScreen.title)
                },
                navigationIcon = {

                },
                actions = {

                }
            )
        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    mainScreens.forEach { tab ->
                        NavigationBarItem(
                            selected = currentScreen == tab,
                            onClick = { navController.navigate(tab) },
                            icon = {

                            },
                            label = { Text(tab.title) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavigationScreens.WeatherMain.name
            ) {
                composable(
                    route = NavigationScreens.WeatherMain.name
                ) {
                    WeatherMainScreen(
                        onWeatherClick = { id: Long ->
                            navController.navigate(NavigationScreens.WeatherDetail.name)
                        }
                    )
                }

                composable(
                    route = NavigationScreens.TodoMain.name
                ) {
                    TodoMainScreen()
                }

                composable(
                    route = NavigationScreens.WeatherDetail.name,
                    arguments = listOf(navArgument("id") { type = NavType.LongType })
                ) { backStackEntry ->

                }
            }
        }
    }
}

@Preview
@Composable
private fun MainFlowScreenPreview(

) {
    MainFlowScreen()
}