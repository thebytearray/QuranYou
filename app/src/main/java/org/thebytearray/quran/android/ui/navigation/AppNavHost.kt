package org.thebytearray.quran.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.thebytearray.quran.android.domain.model.Route
import org.thebytearray.quran.android.ui.home.HomeScreen
import org.thebytearray.quran.android.ui.settings.SettingsScreen
import org.thebytearray.quran.android.ui.surah.SurahScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController) {

    NavHost(
        navController = navController, startDestination = Route.Home.route
    ) {
        composable(Route.Home.route) {
            HomeScreen(onSettingsClick = {
                navController.navigate(Route.Settings.route)
            })

        }
        composable(Route.Settings.route) {
            SettingsScreen(onNavigationClick = {
                navController.popBackStack()
            })

        }
        composable(Route.Surah.route) {
            SurahScreen(onNavigationClick = { navController.popBackStack() })
        }

    }


}