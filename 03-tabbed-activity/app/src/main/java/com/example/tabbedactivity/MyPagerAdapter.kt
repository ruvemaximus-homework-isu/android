package com.example.tabbedactivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val repository = CityWeatherRepository()

    override fun getItem(position: Int): Fragment {
        return CityWeatherFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return repository.count()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return repository.get(position)?.cityName ?: "Unknown"
    }
}
