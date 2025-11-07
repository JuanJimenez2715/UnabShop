package me.juanjimenez.unabshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            var startDestination = "login"

            val auth = Firebase.auth
            val currentUser = auth.currentUser

            // ✅ Determina la pantalla inicial
            if (currentUser != null) {
                startDestination = "home"
            } else {
                startDestination = "login"
            }

            // ✅ Configuración de las rutas de navegación
            NavHost(navController = navController, startDestination = startDestination) {

                // 🟡 Pantalla de Login
                composable(route = "login") {
                    LoginScreen(
                        onClickRegister = {
                            navController.navigate("register")
                        },
                        onSuccessfulLogin = {
                            navController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        }
                    )
                }

                // 🟡 Pantalla de Registro
                composable(route = "register") {
                    RegisterScreen(
                        onClickBack = {
                            navController.popBackStack()
                        },
                        onSuccessfulRegister = {
                            navController.navigate("home") {
                                popUpTo(0)
                            }
                        }
                    )
                }

                // 🏠 Pantalla Principal (Home)
                composable(route = "home") {
                    HomeScreen(
                        onClickLogout = {
                            navController.navigate("login") {
                                popUpTo(0)
                            }
                        },
                        onClickAgregarProducto = { // ✅ Este callback abre la nueva pantalla
                            navController.navigate("agregarProducto")
                        }
                    )
                }

                // 🟢 Pantalla para agregar un nuevo producto
                composable(route = "agregarProducto") {
                    AgregarProductoScreen(
                        onClickBack = {
                            navController.popBackStack() // ✅ Vuelve al home
                        }
                    )
                }
            }
        }
    }
}
