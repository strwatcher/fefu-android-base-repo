package ru.fefu.activitytracker.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.databinding.FragmentMyActionsBinding

class MyActionsFragment: Fragment() {
    private lateinit var _binding: FragmentMyActionsBinding

    companion object {
        fun newInstance(): MyActionsFragment {
            return MyActionsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyActionsBinding.inflate(layoutInflater)
        return _binding.root
    }
}
