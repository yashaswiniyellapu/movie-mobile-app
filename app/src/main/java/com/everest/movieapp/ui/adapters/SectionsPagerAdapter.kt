package com.everest.movieapp.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.everest.movieapp.ui.fragments.CurrentYearMovies
import com.everest.movieapp.ui.fragments.PopularMovies

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager
) :
    FragmentPagerAdapter(fm) {

    private var fragmentList: List<Fragment> = listOf(PopularMovies(), CurrentYearMovies())
    private var fragmentNamesList: List<String> = listOf("")

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragmentList[position]
    }


    override fun getPageTitle(position: Int): CharSequence {
        val tabNames = ArrayList<String>()
        tabNames.add("PopularMovies")
        tabNames.add("LatestMovies")
        return tabNames[position]
    }


    override fun getCount(): Int {
        // Show 2 total pages.
        return fragmentList.size
    }

}