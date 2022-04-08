package com.everest.movieapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.everest.movieapp.ui.fragments.CurrentYearMoviesFragment
import com.everest.movieapp.ui.fragments.PopularMoviesFragment
import java.lang.RuntimeException


class SectionsPagerAdapter(private val titles: Array<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when(position)
        {
            0 -> PopularMoviesFragment()
            1 -> CurrentYearMoviesFragment()
            else -> {
                throw RuntimeException("Not found")
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }


    override fun getCount(): Int {
        return titles.size
    }

}