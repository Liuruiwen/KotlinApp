package com.ruiwenliu.kotlin.moudle.ui.home.bean

import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.ui.home.adapter.HomeChildPageAdapter

/**
 * Created by Amuse
 * Date:2020/3/21.
 * Desc:
 */
class ItemHead : BaseBean() {
    var results: List<ItemHeadDataBean>?=null

}

class ItemHeadDataBean :MultiItemBean{
    var _id: String?=null
    var created_at: String?=null
    var icon: String?=null
    var id: String?=null
    var title: String?=null
    override fun getSpanSize(): Int {
        return 10
    }

    override val itemType: Int
        get() = HomeChildPageAdapter.TYPE_ITEM_HEAD
}