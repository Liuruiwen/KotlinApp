package com.ruiwenliu.kotlin.moudle.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ApiConfig
import com.ruiwenliu.kotlin.moudle.MainPresenter
import com.ruiwenliu.kotlin.moudle.base.BaseActivity
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.base.widget.TitleView
import com.ruiwenliu.kotlin.moudle.ui.adapter.MenuAdapter
import com.ruiwenliu.kotlin.moudle.ui.bean.MenuBean
import com.ruiwenliu.kotlin.moudle.ui.bean.ProjectListBean
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.base_layout.*

class ProjectListActivity : BaseActivity<MainPresenter>() {

    override fun setLayout(): Int {
        return  R.layout.activity_project_list
    }

    override fun initData(savedInstanceState: Bundle?, titleView: TitleView) {
        base_title_view.setTitle("让我说点什么好")
        btn_data.setOnClickListener({
            getData()
            getProjectList()
        })
        btn_scroll.setOnClickListener({
            menu_rv.scrollToPosition(5)
//            menu_rv.scrollX=140
//            menu_rv.scrollY=140
//            menu_rv.smoothScrollBy(120,148)
        })
    }





    override fun <T : BaseBean> onShowResult(requestType: Int?, result: T) {
            when(requestType){
                1->processData(result as MenuBean)
                2-> processProjectList(result as ProjectListBean)
            }


    }

    fun  processData(bean:MenuBean){
        val layoutManager= LinearLayoutManager(this);
        layoutManager.orientation= LinearLayoutManager.HORIZONTAL
        menu_rv.layoutManager=layoutManager
        menu_rv.adapter= bean?.data?.let { MenuAdapter(this, it) }
        menu_rv.addItemDecoration(SpaceItemDecoration(15))
        btn_data.text= bean?.data?.get(0)?.name


    }
    fun  processProjectList(bean:ProjectListBean){

        showToast(bean?.data?.datas?.get(0)?.author as String);
    }

    fun  getData(){
        mPresenter?.getData(1,ApiConfig.HTTP_MENU_LIST, MenuBean::class.java,true)
    }
    fun  getProjectList(){
        mPresenter?.getData(2,ApiConfig.HTTP_PROJECT_LIST, ProjectListBean::class.java,true)
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_project_list)
//    }

    class SpaceItemDecoration constructor(private  val space:Int): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) == 0) {
                //item是第一个的时候不设置间距
                outRect.left = 0;
            }else {
                outRect.left = space;
            }
        }
    }
}
