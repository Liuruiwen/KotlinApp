package com.ruiwenliu.kotlin.moudle.base.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.ruiwenliu.kotlin.moudle.base.BaseWrapperFragment

/**
 * Created by Amuse
 * Date:2020/2/11.
 * Desc:
 */
class TabAdapter constructor (fm: FragmentManager,var list:List<Fragment>) : FragmentPagerAdapter(fm) {
    private var position: Int = 0


    override fun getItem(i: Int): Fragment {
       return list[i]
    }

    override fun getCount(): Int {
       return if (list == null) 0 else list.size;
    }

    override fun setPrimaryItem(container: ViewGroup, i: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
        position = i;
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    public fun getCurrentFragment():Fragment {
        return list[position]
    }

    public  fun  refreshDada(listData:List<Fragment>){
        list=listData;
        notifyDataSetChanged();
    }
}