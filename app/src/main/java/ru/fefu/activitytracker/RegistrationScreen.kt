package ru.fefu.activitytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toolbar
import ru.fefu.activitytracker.databinding.ActivityRegistrationScreenBinding

class RegistrationScreen : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarRegistration.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}