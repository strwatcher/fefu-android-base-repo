package ru.fefu.activitytracker.screens.registration

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityRegistrationScreenBinding

class RegistrationScreenActivity: AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener {
            finish()
        }

        setupSexDropDownMenu()

        enableLinks()
    }

    private fun setupSexDropDownMenu() {
        val sexes = listOf(
            getString(R.string.sex_man),
            getString(R.string.sex_woman),
            getString(R.string.sex_other))

        val inputLayout = binding.sexInputLayout

        val adapter = ArrayAdapter(baseContext , R.layout.list_item, sexes)
        (inputLayout.editText as? MaterialAutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun enableLinks() {
        binding.importantInfo.movementMethod = LinkMovementMethod.getInstance()
    }
}