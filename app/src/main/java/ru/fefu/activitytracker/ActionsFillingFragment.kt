package ru.fefu.activitytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.fefu.activitytracker.databinding.FragmentActionsFillingBinding

class ActionsFillingFragment: Fragment() {
    lateinit var message: String
    private lateinit var _binding: FragmentActionsFillingBinding
    companion object {
        fun newInstance(message: String): ActionsFillingFragment {
            val fragment = ActionsFillingFragment()
            fragment.message = message
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActionsFillingBinding.inflate(layoutInflater)

        _binding.textView.text = message

        return _binding.root
    }




}