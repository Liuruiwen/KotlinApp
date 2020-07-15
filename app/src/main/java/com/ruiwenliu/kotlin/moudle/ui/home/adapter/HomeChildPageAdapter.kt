package com.ruiwenliu.kotlin.moudle.ui.home.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ruiwenliu.glide.library.GlideManager
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ui.ImageBrowseActivity
import com.ruiwenliu.kotlin.moudle.ui.home.bean.*
import com.ruiwenliu.kotlin.moudle.util.BaseGlideHolder

/**
 * Created by Amuse
 * Date:2020/3/1.
 * Desc:
 */
class HomeChildPageAdapter : BaseMultiItemQuickAdapter<MultiItemBean, BaseGlideHolder>(){

    companion object{
        const val TYPE_ITEM_HEAD=1;
        const val TYPE_ITEM_BODY=2;
        const val TYPE_ITEM_BOTTOM=3;
    }

    init {
        addItemType(TYPE_ITEM_HEAD, R.layout.multiplel_item_head)
        addItemType(TYPE_ITEM_BODY, R.layout.multiplel_item_body)
        addItemType(TYPE_ITEM_BOTTOM, R.layout.multiplel_item_bottom)
    }
    override fun convert(helper: BaseGlideHolder, item: MultiItemBean?) {
        when(helper.itemViewType){
            TYPE_ITEM_HEAD->processHead(helper,item as ItemHeadDataBean)
            TYPE_ITEM_BODY->processBody(helper,item as ItemBodyBean)
            TYPE_ITEM_BOTTOM->processBottom(helper,item as ItemOperationBean)

        }
    }

    fun  processHead(helper: BaseGlideHolder,item : ItemHeadDataBean){
        helper.setText(R.id.tv_title,item.title)
        helper.loadCircleImage(context,item.icon!!,helper.getView(R.id.iv_user))
        helper.setText(R.id.tv_name,item.id)


    }

    fun  processBody(helper: BaseGlideHolder,item : ItemBodyBean){
//        val url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584787532023&di=c85af0ae952c211444bde81ff62d70e0&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20181219%2F3501159de98a4c8a98b30c9d1fcbbd5e.jpeg"

       val ivBody=helper.getView(R.id.iv_body) as ImageView
        helper.loadImage(context,item.imageUrl!!,ivBody)

        ivBody.setOnClickListener {
            var strList= item.urlGroup!!.split(";")
            context.startActivity(ImageBrowseActivity.getIntent(context,if(strList.size==1) arrayListOf(strList[0])  else strList as ArrayList<String>,item.index))


        }

    }

    fun  processBottom(helper: BaseGlideHolder,item : ItemOperationBean){
        helper.setText(R.id.tv_star,"点赞"+item.count)
    }


}