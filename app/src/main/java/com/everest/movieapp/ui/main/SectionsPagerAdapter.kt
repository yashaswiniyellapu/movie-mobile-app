package com.everest.movieapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.everest.movieapp.R
import com.everest.movieapp.ui.main.fragments.CurrentYearMovies
import com.everest.movieapp.ui.main.fragments.PopularMovies

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when (position) {
            0 -> {
                PopularMovies()
            }
            1 -> {
                CurrentYearMovies()
            }
            else -> getItem(position)
        }
    }


    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "PopularMovies"
            }
            1 -> {
                "CurrentYearMovies"
            }
            else -> getPageTitle(position)
        }
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}