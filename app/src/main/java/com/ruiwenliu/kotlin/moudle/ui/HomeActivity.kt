package com.ruiwenliu.kotlin.moudle.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.base.adapter.TabAdapter
import com.ruiwenliu.kotlin.moudle.ui.collect.CollectFragment
import com.ruiwenliu.kotlin.moudle.ui.home.HomePageFragment
import com.ruiwenliu.kotlin.moudle.ui.mine.MineFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
       when(v?.id){
           R.id.tv_home_page ->
               setTextColor(0)
           R.id.tv_collect ->
               setTextColor(1)
           R.id.tv_mine ->
               setTextColor(2)

       }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tv_home_page.setOnClickListener(this)
        tv_collect.setOnClickListener(this)
        tv_mine.setOnClickListener(this)
        view_pager.adapter= TabAdapter(getSupportFragmentManager(), getListFragment())
        setTextColor(0)
        view_pager.setScrollabele(false)
        view_pager.offscreenPageLimit=3
    }

    fun setTextColor(type:Int) {
        view_pager.setCurrentItem(type,false)
        tv_home_page.setTextColor(getTextColor(type==0))
        tv_collect.setTextColor(getTextColor(type==1))
        tv_mine.setTextColor(getTextColor(type==2))
    }

    fun  getTextColor(type:Boolean):Int{
        if(type==true)
        return Color.parseColor("#008577")
        else
        return Color.parseColor("#333333")
    }
    fun  getListFragment():List<Fragment>{
        val list=ArrayList<Fragment>();
        list.add(HomePageFragment.getInstance(1))
        list.add(CollectFragment.getInstance(2))
        list.add(MineFragment.getInstance(3))

        return list
    }



}
