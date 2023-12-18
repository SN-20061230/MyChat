package com.example.chat_app.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chat_app.Screens.ChatScreen
import com.example.chat_app.Screens.HomeScreen
import com.example.chat_app.Screens.SettingsScreen
import com.example.chat_app.Screens.SignInScreen
import com.example.chat_app.Screens.SignUpScreen
import com.example.chat_app.Screens.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(route = Screens.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.SignIn.route) {
            SignInScreen(navController)
        }
        composable(route = Screens.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen(navController)
        }
        composable(route = Screens.Chat.route, arguments = listOf(navArgument(NAME_KEY) {
            type = NavType.StringType
        })) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString(NAME_KEY)
            if (name != null) {
                ChatScreen(name = name, navController = navController)
            }
        }
    }
}