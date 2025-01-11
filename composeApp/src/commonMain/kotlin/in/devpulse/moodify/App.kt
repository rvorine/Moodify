package `in`.devpulse.moodify

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.devpulse.moodify.ui.HomeScreen
import `in`.devpulse.moodify.ui.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        AppNavHostController(navController)
    }
}

@Composable
fun RootView(
    navigate: (Route) -> Unit
) {
    CoroutineScope(Dispatchers.Main).launch {
        if (coreComponent.appPreferences.isLoggedIn()) {
            navigate(Route.HomeScreen)
        } else {
            navigate(Route.LoginScreen)
        }
    }
}

@Composable
fun AppNavHostController(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.Root.route
    ) {
        composable(route = Route.Root.route) {
            RootView {
                navHostController.navigate(it.route) {
                    popUpTo(Route.Root.route)
                }
            }
        }

        composable(route = Route.LoginScreen.route) {
            Login(onLoginSuccess = {
                navHostController.navigate(Route.Root.route)
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
    data object Root : Route("root")
    data object LoginScreen : Route("login_screen")
    data object HomeScreen : Route("home_screen")
    data object PlayerScreen : Route("player_screen")
}

object ApplicationComponent {
    private var _coreComponent: CoreComponent? = null
    val coreComponent
        get() = _coreComponent
            ?: throw IllegalStateException("Make sure to call ApplicationComponent.init()")

    fun init() {
        _coreComponent = CoreComponentImpl()
    }
}

val coreComponent get() = ApplicationComponent.coreComponent