package ru.fefu.activitytracker.screens.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationBarHandler
    (
    navBar: BottomNavigationView,
    fragmentManager: FragmentManager,
    fragments: List<FragmentItem>,
    fragmentContainerId: Int
) {
    private val _navBar: BottomNavigationView = navBar
    private val _fragmentManager: FragmentManager = fragmentManager
    private val _fragments: List<FragmentItem> = fragments
    private val _fragmentContainerId = fragmentContainerId

    data class FragmentItem (
        val id: Int,
        val tag: String,
        val newInstance: () -> Fragment,
    )

    init {
        setListeners()
    }

    private fun setListeners() {
        _navBar.setOnItemSelectedListener(::onItemSelected)
    }

    private fun onItemSelected(menuItem: MenuItem): Boolean {
        _fragmentManager.beginTransaction().apply {
            for (fragmentItem in _fragments) {
                val fragment = _fragmentManager.findFragmentByTag(fragmentItem.tag)
                if (fragmentItem.id == menuItem.itemId) {
                    fragment?.let {show(fragment)} ?: add(
                        _fragmentContainerId,
                        fragmentItem.newInstance(),
                        fragmentItem.tag
                    )
                }
                else {
                    fragment?.let {hide(fragment)}
                }
            }
            commit()
        }

        return true
    }
}