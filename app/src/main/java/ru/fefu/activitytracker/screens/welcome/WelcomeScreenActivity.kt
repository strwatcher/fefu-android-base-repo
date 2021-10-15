package ru.fefu.activitytracker.screens.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.fefu.activitytracker.databinding.ActivityWelcomeScreenBinding
import ru.fefu.activitytracker.screens.login.LoginScreenActivity
import ru.fefu.activitytracker.screens.registration.RegistrationScreenActivity

class WelcomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindLoginButton()
        bindRegistrationButton()
    }

    private fun bindLoginButton() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindRegistrationButton() {
        binding.registrationButton.setOnClickListener {
            val intent = Intent(this, RegistrationScreenActivity::class.java)
            startActivity(intent)
        }
    }
}