package com.ruiwenliu.kotlin.moudle.ui.collect

import android.os.Bundle
import android.widget.TextView
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.MainPresenter
import com.ruiwenliu.kotlin.moudle.base.BaseFragment
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean

/**
 * Created by Amuse
 * Date:2020/2/12.
 * Desc:
 */
class CollectFragment : BaseFragment<MainPresenter>(){
    companion object{
        fun getInstance(type: Int): CollectFragment {
            val bundl = Bundle()
            bundl.putInt("type", type)
            val fragment = CollectFragment()
            fragment.arguments = bundl
            return fragment
        }
    }

    override fun getViewLayout(): Int {
        return  R.layout.fragment_home_pagely
    }

    override fun initView() {
        if (arguments != null) {
            val type = arguments!!.getInt("type")
//            contentView?.findViewById<TextView>(R.id.tv_name)?.text="收藏"+type
        }
    }

    override fun loadData() {

    }

    override fun <T : BaseBean> onShowResult(requestType: Int?, result: T) {

    }
}