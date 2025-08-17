package org.thebytearray.quran.android.domain.model

sealed class Route(val route: String) {
    data object Home : Route(route = "home")
    data object Settings : Route(route = "settings")
    data object Surah : Route(route = "surah")
}