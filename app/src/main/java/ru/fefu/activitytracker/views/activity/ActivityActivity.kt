package ru.fefu.activitytracker.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.ActivityActivityBinding

class ActivityActivity: AppCompatActivity() {
    lateinit var binding: ActivityActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        binding.bBack.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

    override fun onBackPressed() {
       finish()
    }
}