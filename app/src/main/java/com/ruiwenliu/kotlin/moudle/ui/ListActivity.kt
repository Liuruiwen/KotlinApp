package com.ruiwenliu.kotlin.moudle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ui.adapter.ListAdapter
import com.ruiwenliu.kotlin.moudle.ui.bean.ListBean
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        recycler_view.layoutManager=LinearLayoutManager(this)
        val adapter=ListAdapter()
        recycler_view.adapter=adapter
        adapter.setNewData(getListBean())

    }

    fun getListBean(): MutableList<ListBean>? {
        val list = ArrayList<ListBean>()
        list.add(getBean(1))
        list.add(getBean(2))
        list.add(getBean(3))
        list.add(getBean(4))
        list.add(getBean(5))
        list.add(getBean(6))
        return list
    }

    fun getBean(i: Int): ListBean {
        val bean = ListBean()
        bean.name = "张三" + i + "号"
        return bean
    }
}
