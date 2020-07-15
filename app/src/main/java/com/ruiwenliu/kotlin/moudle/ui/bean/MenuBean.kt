package com.ruiwenliu.kotlin.moudle.ui.bean

import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean

class MenuBean :BaseBean(){
    val data: List<MenuItemData>?=null
}

 class MenuItemData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)