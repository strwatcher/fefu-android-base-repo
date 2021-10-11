package ru.fefu.activitytracker.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.databinding.FragmentUsersActionsBinding

class UsersActionsFragment: Fragment() {
    private lateinit var _binding: FragmentUsersActionsBinding

    companion object {
        fun newInstance(): UsersActionsFragment {
            return  UsersActionsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersActionsBinding.inflate(layoutInflater)
        return _binding.root
    }

}