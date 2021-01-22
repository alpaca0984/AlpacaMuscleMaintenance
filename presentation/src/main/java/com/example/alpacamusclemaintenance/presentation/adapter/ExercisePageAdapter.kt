package com.example.alpacamusclemaintenance.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.alpacamusclemaintenance.presentation.ui.PushUpFragment
import com.example.alpacamusclemaintenance.presentation.ui.SquatFragment

class ExercisePageAdapter(
    fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PushUpFragment()
            else -> SquatFragment()
        }
    }

    // Show 2 total pages.
    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "pushUp"
            else -> "squat"
        }
    }
}