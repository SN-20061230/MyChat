package com.example.chat_app.Navigation

const val NAME_KEY = "name_key"

sealed class Screens(val route: String) {
    object Splash : Screens("Splash")
    object Home : Screens("Home")
    object SignIn : Screens("SignIn")
    object SignUp : Screens("SignUp")
    object Settings : Screens("Settings")
    object Chat : Screens("Chat/{$NAME_KEY}") {
        fun getFullRoute(name: String): String {
            return "Chat/$name"
        }
    }
}