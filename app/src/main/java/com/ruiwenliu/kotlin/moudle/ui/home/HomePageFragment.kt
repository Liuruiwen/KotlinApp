package com.ruiwenliu.kotlin.moudle.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ApiConfig
import com.ruiwenliu.kotlin.moudle.MainPresenter
import com.ruiwenliu.kotlin.moudle.base.BaseFragment
import com.ruiwenliu.kotlin.moudle.base.BaseWrapperFragment
import com.ruiwenliu.kotlin.moudle.base.adapter.TabAdapter
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.ui.ProjectListActivity
import com.ruiwenliu.kotlin.moudle.ui.collect.CollectFragment
import com.ruiwenliu.kotlin.moudle.ui.home.adapter.MenuAdapter
import com.ruiwenliu.kotlin.moudle.ui.home.bean.HomeMenuBean
import com.ruiwenliu.kotlin.moudle.ui.home.bean.HomePageBean
import com.ruiwenliu.kotlin.moudle.ui.home.bean.PageDataBean
import com.ruiwenliu.kotlin.moudle.ui.mine.MineFragment

/**
 * Created by Amuse
 * Date:2020/2/12.
 * Desc:
 */
class HomePageFragment : BaseFragment<MainPresenter>(){
    var menuAdapter :MenuAdapter?=null
    var viewPager:ViewPager?=null
    var menuRv:RecyclerView?=null
    companion object{
        fun getInstance(type: Int): HomePageFragment {
            val bundl = Bundle()
            bundl.putInt("type", type)
            val fragment = HomePageFragment()
            fragment.arguments = bundl
            return fragment
        }
    }

    override fun getViewLayout(): Int {
     return  R.layout.fragment_home_pagely
    }

    override fun initView() {
         menuRv=contentView?.findViewById<RecyclerView>(R.id.rv_menu)
        val layoutManager= LinearLayoutManager(mContext);
        layoutManager.orientation= LinearLayoutManager.HORIZONTAL
        menuRv?.layoutManager=layoutManager
        menuAdapter= MenuAdapter()
        menuRv?.addItemDecoration(ProjectListActivity.SpaceItemDecoration(15))
        menuRv?.adapter=menuAdapter
        menuAdapter?.setOnItemClickListener { adapter, view, position -> menuClick(position) }

        viewPager=contentView?.findViewById<ViewPager>(R.id.page_view_pager)



    }

    override fun loadData() {
        Log.d("===========1==========","进来了哦哦哦")
        getMenuData()
    }

    override fun <T : BaseBean> onShowResult(requestType: Int?, result: T) {
                    when(requestType){
                        1->processPageData(result as HomeMenuBean)
                    }
    }


    fun getMenuData(){
        mPresenter?.getData(1,ApiConfig.API_CATEGORIES, HomeMenuBean::class.java,true)
    }


    fun  processPageData(bean:HomeMenuBean){
       //设置菜单数据
        menuAdapter?.setNewData(bean.results as MutableList<HomeMenuBean.HomeMenuDataBean>)
        viewPager?.adapter= TabAdapter(childFragmentManager, getListFragment(bean.results!!))
        menuClick(0)
        viewPager?.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageSelected(position: Int) {
                menuClick(position)
            }
        })
        viewPager?.offscreenPageLimit=bean.results.size
    }

    fun menuClick(position:Int){
        menuAdapter?.setItemSelect(position)
        viewPager?.setCurrentItem(position,false)
        menuRv?.scrollToPosition(position)

    }


    fun  getListFragment(listResult:List<HomeMenuBean.HomeMenuDataBean>):List<Fragment>{
        val list=ArrayList<Fragment>();
        for (i:Int in 0..listResult.size-1){
            list.add(HomeChildPageFragment.getInstance(1, listResult[i].en_name))
        }
        return list
    }

//    fun  login(){
//        val data= mapOf<String, Any>("username" to "Ruiwen","password" to "123456");
//        mPresenter?.postData(1, ApiConfig.HTTP_LOGIN,BaseBean::class.java,true,data);
//    }

//    fun test(){
//         mPresenter?.getData(2,ApiConfig.API_GANK_TODAY, HomePageBean::class.java,true)
//    }
//    fun  getTodayData(bean:HomePageBean){
//        bean.category?.get(0)?.let { showToast(it) }
//    }
}
