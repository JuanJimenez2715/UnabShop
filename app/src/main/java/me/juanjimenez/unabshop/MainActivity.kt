package me.juanjimenez.unabshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val startDestination = "login"
            NavHost(navController,startDestination){
                composable(route= "login"){
                    LoginScreen()
                }
                composable(route="register") {
                    RegisterScreen()
                }
                composable(route="home") {
                    HomeScreen()
                }


            }


        }
    }
}