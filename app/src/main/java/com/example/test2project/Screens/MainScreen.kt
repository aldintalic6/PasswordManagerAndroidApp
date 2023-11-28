package com.example.test2project.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test2project.EntryList
import com.example.test2project.EntryPage
import com.example.test2project.GeneratePassword
import com.example.test2project.LoginScreen
import com.example.test2project.R
import com.example.test2project.RegistrationScreen
import com.example.test2project.addEntry
import com.example.test2project.compareid
import com.example.test2project.editEntry
import com.example.test2project.fetchEntryList

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val entryList = fetchEntryList()

    NavHost(navController, startDestination = "LoginScreen") {
        composable("LoginScreen") {
            LoginScreen(navController)
        }
        composable("RegisterScreen") {
            RegistrationScreen(navController)
        }
        composable("HomeScreen") {
            EntryList(entryList, navController)
        }
        composable("EntryDataScreen") {
            when (compareid) {
                1 -> EntryPage("Facebook", "facebook@fb.com", "facebook", R.drawable.fb_logo, navController = navController)
                2 -> EntryPage("Twitter", "twitter@tw.com", "twitter", R.drawable._0wmt_articlelarge_v4_jpg, navController = navController)
                3 -> EntryPage("Klix", "klix@klix.com", "klix", R.drawable.klix, navController = navController)
                4 -> EntryPage("GitHub", "github@gh.com", "github", R.drawable.github_logo, navController = navController)
                5 -> EntryPage("Spotify", "spotify@sp.com", "spotify", R.drawable.spotify_logo_without_text, navController = navController)
                6 -> EntryPage("IBU", "ibu@ibu.com", "Burch", R.drawable.ibu_logo_user, navController = navController)
                7 -> EntryPage("Linkedin", "linkedin@ln.com", "linkedin", R.drawable.linkedin_logo_initials, navController = navController)
                8 -> EntryPage("Gmail", "gmail@gmail.com", "gmail", R.drawable.gmail_logo, navController = navController)
            }
        }

        composable("addEntryScreen") {
            addEntry(navController)
        }
        composable("editEntryScreen") {
            editEntry(navController)
        }
        composable("GeneratePasswordScreen") {
            GeneratePassword(navController)
        }
    }
}