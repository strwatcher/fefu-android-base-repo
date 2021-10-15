package ru.fefu.activitytracker.screens

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationBarHandler
    (
    private val navBar: BottomNavigationView,
    private val fragmentManager: FragmentManager,
    private val fragments: List<FragmentItem>,
    private val fragmentContainerId: Int
) {
    data class FragmentItem (
        val id: Int,
        val tag: String,
        val newInstance: () -> Fragment,
    )

    init {
        setListeners()
    }

    private fun setListeners() {
        navBar.setOnItemSelectedListener(::onItemSelected)
    }

    private fun onItemSelected(menuItem: MenuItem): Boolean {
        fragmentManager.beginTransaction().apply {
            for (fragmentItem in fragments) {
                val fragment = fragmentManager.findFragmentByTag(fragmentItem.tag)
                if (fragmentItem.id == menuItem.itemId) {
                    fragment?.let {show(fragment)} ?: add(
                        fragmentContainerId,
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