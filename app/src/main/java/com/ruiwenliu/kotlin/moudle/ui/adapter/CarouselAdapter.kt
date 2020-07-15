package com.ruiwenliu.kotlin.moudle.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter


/**
 * Created by Amuse
 * Date:2020/3/22.
 * Desc:
 */
class CarouselAdapter constructor(var list:List<ImageView>): PagerAdapter() {

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0==p1
    }

    override fun getCount(): Int {
       return list.size
    }

    override fun instantiateItem(
        container: ViewGroup, position: Int): Any {
      var  index =position%list.size
        val view=list.get(index)
        container.removeView(view);
        container.addView(view);

        return view
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    }

}