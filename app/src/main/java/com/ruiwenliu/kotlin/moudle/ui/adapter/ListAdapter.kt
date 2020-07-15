package com.ruiwenliu.kotlin.moudle.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ui.bean.ListBean

class ListAdapter : BaseQuickAdapter<ListBean, BaseViewHolder>(R.layout.item_menu) {
    override fun convert(helper: BaseViewHolder, item: ListBean?) {
        helper?.setText(R.id.tv_menu,item?.name);
    }
}