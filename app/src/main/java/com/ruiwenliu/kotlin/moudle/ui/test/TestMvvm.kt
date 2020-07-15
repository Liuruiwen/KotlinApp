package com.ruiwenliu.kotlin.moudle.ui.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.databinding.ActivityTestBinding
import com.ruiwenliu.kotlin.mvvm.MainViewModel
import com.ruiwenliu.kotlin.mvvm.TestViewModel
import com.ruiwenliu.kotlin.mvvm.model.MainBean
import com.ruiwenliu.kotlin.mvvm.model.MainDataModel
import kotlinx.android.synthetic.main.activity_test.*

/*   
* @Author:      Amuser
* @CreateDate:   2019-12-23 14:56
*@Description: 
*/
class TestMvvm : AppCompatActivity() {
    var  _binding:ActivityTestBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    _binding =DataBindingUtil.setContentView(this,R.layout.activity_test);
//        var  _binding=DataBindingUtil.setContentView(this,R.layout.activity_test) as ActivityMBinding
        var testBean= TestViewModel()
//        var  viewModel= MainViewModel()
        _binding?.test=testBean
//        _binding?.user=viewModel
        _binding?.lifecycleOwner=this

        var mainModel=ViewModelProviders.of(this).get(MainViewModel().javaClass)
//        var mainModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MainViewModel().javaClass);

        btn_test.setOnClickListener({
//            mainModel.name.value="大道朝天";
//            Toast.makeText(this, MainDataModel.get().value?.name, Toast.LENGTH_SHORT).show();
//            val bean=MainBean();
//            bean.name="大道朝天";
            MainDataModel.get().value?.name="大道朝天";
            MainDataModel.get().value?.let { it1 -> MainDataModel.get().setValue(it1) };
            Toast.makeText(this, MainDataModel.get().value?.name, Toast.LENGTH_SHORT).show();

        })
    }

}