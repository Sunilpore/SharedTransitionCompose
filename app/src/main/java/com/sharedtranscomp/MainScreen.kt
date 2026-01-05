package com.sharedtranscomp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sharedtranscomp.navigation.Screens
import com.sharedtranscomp.ui.transition.TransitionsScreen
import com.sharedtranscomp.ui.transition.animated_visibility.TransitionWithAnimatedVisibilityScreen
import com.sharedtranscomp.ui.transition.fab.TransitionWithFabComponentScreen
import com.sharedtranscomp.ui.transition.sheet.TransitionWithSheetScreen
import com.sharedtranscomp.ui.transition.text_transform.TransitionWithTextTransformScreen
import com.sharedtranscomp.ui.transition.with_navigation.Screen
import com.sharedtranscomp.ui.transition.with_navigation.TransitionWithNavigationScreen
import com.sharedtranscomp.ui.transition.without_navigation.TransitionWithoutNavigationScreen


@Composable
fun MainScreen (
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.TransitionListScreen.route
    ){
        composable(Screens.TransitionListScreen.route) {
            TransitionsScreen(
                modifier = Modifier.fillMaxSize(),
                navigateTo = { screen ->
                    navController.navigate(screen)
                }
            )
        }

        composable(Screens.TransitionWithNavigationScreen.route){
            TransitionWithNavigationScreen(
                modifier = Modifier.fillMaxSize(),
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable (Screens.TransitionWithoutNavigationScreen.route) {
            TransitionWithoutNavigationScreen(
                modifier = Modifier.fillMaxWidth(),
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable (Screens.TransitionWithTextTransformScreen.route){
            TransitionWithTextTransformScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screens.TransitionWithAnimatedVisibilityScreen.route) {
            TransitionWithAnimatedVisibilityScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }


        composable (Screens.TransitionWithSheetScreen.route){
            TransitionWithSheetScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screens.TransitionWithFabComponentScreen.route) {
            TransitionWithFabComponentScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

    }

}