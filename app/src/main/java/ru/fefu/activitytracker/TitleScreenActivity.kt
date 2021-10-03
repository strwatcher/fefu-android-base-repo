package ru.fefu.activitytracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.activitytracker.databinding.TitleScreenBinding

class TitleScreenActivity : AppCompatActivity() {
    private lateinit var binding: TitleScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TitleScreenBinding.inflate(layoutInflater)
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