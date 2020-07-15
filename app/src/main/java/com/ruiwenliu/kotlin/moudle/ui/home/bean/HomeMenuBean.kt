package com.ruiwenliu.kotlin.moudle.ui.home.bean

import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean

/**
 * Created by Amuse
 * Date:2020/3/21.
 * Desc:
 */
class HomeMenuBean  :BaseBean(){
    val results: List<HomeMenuDataBean>?=null

data class HomeMenuDataBean(
    val _id: String,
    val en_name: String,
    val name: String,
    val rank: Int
)
}