package ru.fefu.activitytracker.views.main.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentProfilePasswordChangerBinding

class PasswordChanger:
    BaseFragment<FragmentProfilePasswordChangerBinding>(R.layout.fragment_profile_password_changer)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbTopbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}