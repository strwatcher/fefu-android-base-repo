package ru.fefu.activitytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.activitytracker.databinding.TitleScreenBinding

class TitleScreenActivity : AppCompatActivity() {
    private lateinit var binding: TitleScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TitleScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}