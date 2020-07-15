package com.ruiwenliu.kotlin.moudle.ui.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.databinding.ActivityMBinding
import com.ruiwenliu.kotlin.mvvm.MainViewModel
import com.ruiwenliu.kotlin.mvvm.model.MainBean
import com.ruiwenliu.kotlin.mvvm.model.MainDataModel
import kotlinx.android.synthetic.main.activity_m.*

class MActivity : AppCompatActivity() {
     var _binding: ActivityMBinding?=null
//    var bean:MainViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding =DataBindingUtil.setContentView(this,R.layout.activity_m) as ActivityMBinding;
//          bean= MainViewModel()
//

    val beans= MainBean();
    beans.name="kotlin学习";
    MainDataModel.get().setValue(beans);

        var bean= ViewModelProviders.of(this).get(MainViewModel().javaClass)
        _binding?.user=bean
        _binding?.lifecycleOwner=this
        bean.name.value="为甚"
    MainDataModel.get().observe(this, Observer {
        bean.name.value=it.name
    })

        btn.setOnClickListener({
            val intent = Intent()
            //获取intent对象
            intent.setClass(this, TestMvvm().javaClass)
            // 获取class是使用::反射(那么问题来了,反射是个什么鬼?👻👻👻👻小白的悲哀啊,赶紧研究研究去)
            startActivity(intent)
        })

        btn_refresh.setOnClickListener({
//            Toast.makeText(this, banner.getData()?.get(0)?.title, Toast.LENGTH_SHORT).show()
            Toast.makeText(this,bean?.name?.value,Toast.LENGTH_SHORT).show();
        })
    }
}

