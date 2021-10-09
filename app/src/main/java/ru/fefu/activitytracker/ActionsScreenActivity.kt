package ru.fefu.activitytracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.ActivityActionsScreenBinding

class ActionsScreenActivity: AppCompatActivity(){
    private lateinit var _binding: ActivityActionsScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityActionsScreenBinding.inflate(layoutInflater)

        setContentView(_binding.root)
    }
}