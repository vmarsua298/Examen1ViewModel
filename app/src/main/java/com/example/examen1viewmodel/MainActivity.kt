package com.example.examen1viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.examen1viewmodel.Screens.ScreenMain
import com.example.examen1viewmodel.Screens.ScreenVoid
import com.example.examen1viewmodel.Screens.Screens
import com.example.examen1viewmodel.data.ApostarUIState
import com.example.examen1viewmodel.ui.ApostarViewModel
import com.example.examen1viewmodel.ui.theme.Examen1ViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Examen1ViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }

    @Composable
    private fun AppNavigation(navController: NavHostController = rememberNavController()) {
        val viewModelApostar: ApostarViewModel = viewModel()
        val uiState by viewModelApostar.uiState.collectAsState()
        NavHost(navController = navController, startDestination = Screens.ScreenMain.ruta ){
            composable( route = Screens.ScreenMain.ruta){
                ScreenMain(
                    viewModelApostar = viewModelApostar,
                    uiState = uiState,
                    onClickCambiarPantalla = { navController.navigate(Screens.ScreenVoid.ruta) }
                )
            }
            composable( route = Screens.ScreenVoid.ruta){
                ScreenVoid()
            }
        }
    }
}