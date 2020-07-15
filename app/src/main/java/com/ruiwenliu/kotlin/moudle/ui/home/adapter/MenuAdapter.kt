package com.ruiwenliu.kotlin.moudle.ui.home.adapter

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ui.home.bean.HomeMenuBean

/**
 * Created by Amuse
 * Date:2020/3/14.
 * Desc:首页menu 菜单适配器
 */
class MenuAdapter :BaseQuickAdapter<HomeMenuBean.HomeMenuDataBean,BaseViewHolder>(R.layout.item_menu){
    var selectPosition=0;
    override fun convert(helper: BaseViewHolder, item: HomeMenuBean.HomeMenuDataBean?) {
        var tvMenu=helper.getView<AppCompatTextView>(R.id.tv_menu)
        tvMenu.text=item?.name
        tvMenu.setTextColor(if (selectPosition==getSelectPosition(item!!)) {
            ContextCompat.getColor(context,R.color.colorPrimary)
        } else
            ContextCompat.getColor(context,R.color.textGray)
        )
    }

    fun setItemSelect(position:Int){
        this.selectPosition=position
        notifyDataSetChanged()
    }

    fun getSelectPosition(item:HomeMenuBean.HomeMenuDataBean):Int{
        return data.indexOf(item)
    }
}