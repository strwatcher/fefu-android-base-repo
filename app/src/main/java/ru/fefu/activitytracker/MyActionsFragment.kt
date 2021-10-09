package ru.fefu.activitytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.databinding.FragmentMyActionsBinding

class MyActionsFragment: Fragment() {
    lateinit var message: String
    private lateinit var binding: FragmentMyActionsBinding
    companion object {
        fun newInstance(message: String): MyActionsFragment {
            val fragment = MyActionsFragment()
            fragment.message = message
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyActionsBinding.inflate(layoutInflater)

        binding.textView.text = message

        return binding.root
    }




}