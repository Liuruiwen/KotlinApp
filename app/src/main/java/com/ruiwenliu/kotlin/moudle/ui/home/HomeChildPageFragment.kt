package com.ruiwenliu.kotlin.moudle.ui.home

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ApiConfig
import com.ruiwenliu.kotlin.moudle.MainPresenter
import com.ruiwenliu.kotlin.moudle.base.BaseFragment
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.ui.home.adapter.HomeChildPageAdapter
import com.ruiwenliu.kotlin.moudle.ui.home.adapter.MenuAdapter
import com.ruiwenliu.kotlin.moudle.ui.home.bean.*
import kotlinx.android.synthetic.main.common_rv.view.*

/**
 * Created by Amuse
 * Date:2020/3/14.
 * Desc:首页
 */
class HomeChildPageFragment :BaseFragment<MainPresenter>(){

    var adapter:HomeChildPageAdapter?=null
    companion object{

        fun getInstance(position:Int,en_name:String):HomeChildPageFragment{
            val bundl= Bundle()
            bundl.putInt("position", position)
            bundl.putString("name",en_name)
            val fragment = HomeChildPageFragment()
            fragment.arguments = bundl
            return fragment
        }
    }



    override fun getViewLayout(): Int {
        return R.layout.common_rv;
    }

    override fun initView() {
        val commonRv=contentView?.findViewById<RecyclerView>(R.id.common_rv)
        commonRv?.layoutManager= GridLayoutManager(mContext, 10);
        var list=ArrayList<MultiItemBean>();
         adapter= HomeChildPageAdapter();
//        adapter.setNewData(list)
        commonRv?.adapter=adapter;

    }

    override fun lazyData() {
        super.lazyData()
        if (arguments!=null){
            getPageData(arguments!!.getString("name",""))
        }

    }


    override fun loadData() {
    }

    override fun <T : BaseBean> onShowResult(requestType: Int?, result: T) {
        when(requestType){
            1->getListData(result as ItemHead)
        }
    }

    fun getPageData(name:String){
        mPresenter?.getData(1, ApiConfig.API_CATEGORY+name, ItemHead::class.java,true)
    }

    fun getListData(bean:ItemHead){
        val list=ArrayList<MultiItemBean>();
        if (bean.results != null) {
//           var listData = arguments?.getParcelableArrayList<PageDataBean>("data")
//            Log.d("============是否有数据","===="+bean.results?.size)

            for (index  in 0 until bean.results?.size!!){
                list.add(bean.results!![index])
                val size=index %7
                var urlGroup=""
                    for (i in 0 until size){
                        if(urlGroup==""){
                            urlGroup=getUrlGroup(size)
                        }
                        val bBean=ItemBodyBean(HomeChildPageAdapter.TYPE_ITEM_BODY)
                        bBean.imageUrl= getImageData().get(i)
                        bBean.index=i
                        bBean.urlGroup=urlGroup
                        list.add(bBean)
                    }
                val bottomBean= ItemOperationBean(HomeChildPageAdapter.TYPE_ITEM_BOTTOM)
                bottomBean.count=index
                list.add(bottomBean)
            }
        }

        adapter?.setNewData(list)
        adapter?.setGridSpanSizeLookup { gridLayoutManager, viewType, position -> list.get(position).getSpanSize() }

    }


     fun getImageData():ArrayList<String>{
         return arrayListOf(
             "http://i.serengeseba.com/uploads/i_4_3346474112x3208843860_26.jpg",
             "http://www.17qq.com/img_qqtouxiang/75253455.jpeg",
             "http://www.17qq.com/img_biaoqing/13132078.jpeg",
             "http://img3.duitang.com/uploads/item/201511/20/20151120170827_nFSB4.jpeg",
             "http://b-ssl.duitang.com/uploads/item/201610/09/20161009145430_JTsHX.thumb.700_0.png",
             "http://i.serengeseba.com/uploads/i_5_1307403103x2751263104_26.jpg",
             "http://www.17qq.com/img_biaoqing/38972081.jpeg",
             "http://imgo2.hqwyarm.com/img2018/4/24/18/20180424677356578828.jpg",
             "http://img3.duitang.com/uploads/item/201511/20/20151120170827_nFSB4.jpeg",
             "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584813441576&di=09a7c05a9c30d087b481f362b3ecbafc&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201706%2F10%2F20170610100510_8mcPC.jpeg"
             );

    }

    fun getUrlGroup(size:Int):String{
        var builder=StringBuilder()
        for (i in 0 until size){
            builder.append(getImageData()[i])
            builder.append(";")
        }

       return builder.toString().substring(0,builder.toString().length-1)
    }


}