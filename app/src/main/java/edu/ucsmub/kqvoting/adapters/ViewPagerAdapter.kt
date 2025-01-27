package edu.ucsmub.kqvoting.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter (manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val mFragmentList = ArrayList<Fragment>()


    override fun getItem(position: Int): Fragment = mFragmentList[position]


    override fun getCount(): Int = mFragmentList.size


    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)

    }

}