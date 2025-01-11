package `in`.devpulse.moodify

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.devpulse.moodify.ui.HomeScreen
import `in`.devpulse.moodify.ui.Login
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(onEvent: ((AppUiEvent) -> Unit)? = null) {
    MaterialTheme {
        val navController = rememberNavController()
        //Login(onEvent = onEvent)
        AppNavHostController(navController)
    }
}

@Composable
fun AppNavHostController(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.LoginScreen.route
    ) {
        composable(route = Route.LoginScreen.route) {
            Login(onLoginSuccess = {
                navHostController.navigate(Route.HomeScreen.route) {
                    popUpTo(Route.LoginScreen.route)
                }
            })
        }
        composable(route = Route.HomeScreen.route) {
            HomeScreen()
        }
    }
}


sealed class Route(
    val route: String
) {
    data object LoginScreen : Route("login_screen")
    data object HomeScreen : Route("home_screen")
    data object PlayerScreen : Route("player_screen")
}