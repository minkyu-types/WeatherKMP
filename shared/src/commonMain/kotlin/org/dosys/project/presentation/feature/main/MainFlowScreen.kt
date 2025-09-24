package org.dosys.project.presentation.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.dosys.project.presentation.feature.todo.TodoMainScreen
import org.dosys.project.presentation.feature.todo.TodoMainViewModel
import org.dosys.project.presentation.feature.todo.TodoModel
import org.dosys.project.presentation.feature.weather.main.WeatherMainScreen
import org.dosys.project.presentation.feature.weather.main.WeatherMainViewModel
import org.dosys.project.presentation.navigation.NavigationScreens
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
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

        },
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    mainScreens.forEach { tab ->
                        NavigationBarItem(
                            selected = currentScreen == tab,
                            onClick = { navController.navigate(tab.name) },
                            icon = {

                            },
                            label = { Text(tab.title) }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (currentScreen == NavigationScreens.TodoMain) {
                val todoMainViewModel: TodoMainViewModel = getKoin().get()
                val newTodo = TodoModel.createNewTodoModel()

                FloatingActionButton(
                    onClick = {
                        todoMainViewModel.addTodo(newTodo)
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.PlusOne,
                            contentDescription = null
                        )
                    }
                )
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
                            navController.navigate("${NavigationScreens.WeatherDetail.name}/$id")
                        },
                        viewModel = getKoin().get<WeatherMainViewModel>()
                    )
                }

                composable(
                    route = NavigationScreens.TodoMain.name
                ) {
                    TodoMainScreen(
                        viewModel = getKoin().get<TodoMainViewModel>()
                    )
                }

                composable(
                    route = "${NavigationScreens.WeatherDetail.name}/{id}",
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