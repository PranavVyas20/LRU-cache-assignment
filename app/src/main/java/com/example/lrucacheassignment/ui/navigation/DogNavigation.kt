package com.example.lrucacheassignment.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lrucacheassignment.ui.home.DogGeneratorHomeScreen
import com.example.lrucacheassignment.ui.generate_dog.GenerateDogViewModel
import com.example.lrucacheassignment.ui.generate_dog.GeneratedDogScreenRoute
import com.example.lrucacheassignment.ui.saved.SavedDogsScreenRoute
import com.example.lrucacheassignment.ui.saved.SavedDogsViewModel

@Composable
fun DogNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            DogGeneratorHomeScreen(
                onGenerateDogClick = {
                    navController.navigate(route = "generate_dog")
                },
                onViewSavedDogsClick = {
                    navController.navigate(route = "saved_dogs")
                }
            )
        }
        composable(
            route = "generate_dog", enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            val generateDogViewModel: GenerateDogViewModel = hiltViewModel()
            GeneratedDogScreenRoute(viewModel = generateDogViewModel) {
                navController.popBackStack()
            }
        }
        composable(route = "saved_dogs", enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }) {
            val savedDogsViewModel: SavedDogsViewModel = hiltViewModel()
            SavedDogsScreenRoute(
                viewModel = savedDogsViewModel,
                onBackPress = { navController.popBackStack() })
        }
    }
}