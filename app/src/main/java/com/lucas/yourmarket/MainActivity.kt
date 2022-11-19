package com.lucas.yourmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.lucas.yourmarket.presentation.navigation.NavigationComponent
import com.lucas.yourmarket.presentation.ui.theme.YourMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            YourMarketTheme {
                // A surface container using the 'background' color from the theme
                Scaffold {
                    NavigationComponent(
                        navHostController = navController,
                        paddingValues = it
                    )
                }
            }
        }
    }
}

