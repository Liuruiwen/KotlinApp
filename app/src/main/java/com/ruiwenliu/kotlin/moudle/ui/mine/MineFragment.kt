package com.ruiwenliu.kotlin.moudle.ui.mine

import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ruiwenliu.glide.library.GlideApp
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.MainPresenter
import com.ruiwenliu.kotlin.moudle.base.BaseFragment
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean

/**
 * Created by Amuse
 * Date:2020/2/12.
 * Desc:
 */
class MineFragment : BaseFragment<MainPresenter>(){
    companion object{
        fun getInstance(type: Int): MineFragment {
            val bundl = Bundle()
            bundl.putInt("type", type)
            val fragment = MineFragment()
            fragment.arguments = bundl
            return fragment
        }
    }

    override fun getViewLayout(): Int {
        return  R.layout.fragment_minely
    }

    override fun initView() {
        if (arguments != null) {
            val type = arguments!!.getInt("type")

//            contentView?.findViewById<TextView>(R.id.tv_name)?.text="我的"+type
        }

        val imagae=contentView?.findViewById<ImageView>(R.id.iv_body)
        val url="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584638510875&di=b7f41e147fbc538ea98209d44f720dbb&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201811%2F02%2F20181102204053_fvjes.thumb.700_0.jpg"
//        GlideManager.getInstance(mContext!!).loadImage(url,imagae)


        GlideApp.with(mContext!!)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .fallback(R.drawable.placeholder)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(60)))
            .into(imagae!!)


//        GlideApp.with(mContext!!)
//            .load(url)
//            .apply(
//                RequestOptions()
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//            )
//            .into(imagae!!)

    }

    override fun loadData() {

    }

    override fun <T : BaseBean> onShowResult(requestType: Int?, result: T) {

    }
}