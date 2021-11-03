package ru.fefu.activitytracker.views.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.fefu.activitytracker.R

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val fragmentView = supportFragmentManager.findFragmentById(R.id.fvActivities) as NavHostFragment
        val navController = fragmentView.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nvActivitiesAndProfile)

        bottomNavigationView.setupWithNavController(navController)
    }
}