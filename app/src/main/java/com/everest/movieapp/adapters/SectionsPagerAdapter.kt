package com.everest.movieapp.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.everest.movieapp.ui.CurrentYearMovies
import com.everest.movieapp.ui.PopularMovies

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private lateinit var fragmentList: ArrayList<Fragment>

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        fragmentList = ArrayList()
        fragmentList.add(PopularMovies())
        fragmentList.add(CurrentYearMovies())
        return fragmentList[position]
    }


    override fun getPageTitle(position: Int): CharSequence? {
        val tabNames = ArrayList<String>()
        tabNames.add("PopularMovies")
        tabNames.add("CurrentYearMovies")
        return tabNames[position]
    }


    override fun getCount(): Int {
        // Show 2 total pages.
        return fragmentList.size
    }
}