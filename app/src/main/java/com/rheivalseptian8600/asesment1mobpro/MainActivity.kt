package com.rheivalseptian8600.asesment1mobpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.rheivalseptian8600.asesment1mobpro.navigation.SetupNavGraph
import com.rheivalseptian8600.asesment1mobpro.ui.theme.Asesment1MobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asesment1MobproTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}