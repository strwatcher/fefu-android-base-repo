package ru.fefu.activitytracker.views.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.ActivityLoginScreenBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var _binding: ActivityLoginScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.tbTopbar.setNavigationOnClickListener {
            finish()
        }
    }
}