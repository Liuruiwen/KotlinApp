package com.ruiwenliu.kotlin.moudle.ui.home.bean

/**
 * Created by Amuse
 * Date:2020/3/16.
 * Desc:
 */
class ItemOperationBean (override val itemType: Int) : MultiItemBean{
    var isCollect:Int?=0
    var count:Int?=0
    override fun getSpanSize(): Int {
        return 10
    }
}