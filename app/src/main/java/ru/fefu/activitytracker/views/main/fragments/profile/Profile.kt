package ru.fefu.activitytracker.views.main.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.fefu.activitytracker.BaseFragment
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.FragmentProfileBinding

class Profile: BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.bChangePassword.setOnClickListener {
            val direction = ProfileDirections.actionProfileToPasswordChanger()
            findNavController().navigate(direction)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}