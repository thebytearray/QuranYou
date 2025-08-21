package org.thebytearray.quran.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.thebytearray.quran.android.domain.model.Route
import org.thebytearray.quran.android.ui.home.HomeScreen
import org.thebytearray.quran.android.ui.settings.SettingsScreen
import org.thebytearray.quran.android.ui.surah.SurahScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Route.Home.route
    ) {
        composable(Route.Home.route) {
            HomeScreen(onSettingsClick = {
                navController.navigate(Route.Settings.route)
            }, onSurahClick = {
                navController.navigate("surah/$it")
            })

        }
        composable(Route.Settings.route) {
            SettingsScreen(navController, onNavigationClick = {
                navController.popBackStack()
            })

        }
        composable(Route.Surah.route) {
            SurahScreen(navController,onNavigationClick = { navController.popBackStack() })
        }

    }


}