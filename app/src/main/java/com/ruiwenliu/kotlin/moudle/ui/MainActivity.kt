package com.ruiwenliu.kotlin.moudle.ui

import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.Toast
import com.ruiwenliu.kotlin.R
import com.ruiwenliu.kotlin.moudle.ApiConfig
import com.ruiwenliu.kotlin.moudle.BannerBean
import com.ruiwenliu.kotlin.moudle.MainPresenter
import com.ruiwenliu.kotlin.moudle.base.BaseActivity
import com.ruiwenliu.kotlin.moudle.base.bean.BaseBean
import com.ruiwenliu.kotlin.moudle.base.widget.TitleView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.LinkedHashMap

class MainActivity : BaseActivity<MainPresenter>() {
    override fun setLayout(): Int {
       return  R.layout.activity_main
    }

    override fun initData(savedInstanceState: Bundle?, titleView: TitleView) {
        tv_getContext.setOnClickListener(View.OnClickListener {
            //            mPresenter?.getData(0,
//                ApiConfig.HTTP_BANNER, BannerBean::class.java, true)
            login();
        })
    }


    val  tablayout:TableLayout?=null
    override fun <T : BaseBean> onShowResult(requestType: Int?, result: T) {
        showToast("登录成功哦")
        when(requestType){
            0->inputData(result as BannerBean)
        }
    }




    fun inputData(banner: BannerBean){
        Toast.makeText(this, banner.getData()?.get(0)?.title, Toast.LENGTH_SHORT).show()
    }
    fun getToken(){
        val data= mapOf(
            "client_id" to "8altuT6uUlUrJfK7dG7g",
            "client_secret" to "rJLCNw5zaupIcNIAUFm3LCOhWc8Zwx5V",
            "grant_type" to "authorization_code",
            "redirect_uri" to "http://www.jianshu.com/users/auth/qq_connect/callback",
            "code" to "rJLCNw5zaupIcNIAUFm3LCOhWc8Zwx5V",
            "dataType" to "json"

        );
        mPresenter?.getData(1,ApiConfig.OSCHINA_TOKEN,BannerBean::class.java,true,data);
    }
    fun  login(){
        val data= mapOf<String, Any>("username" to "Ruiwen","password" to "123456");
        mPresenter?.postData(1,ApiConfig.HTTP_LOGIN,BaseBean::class.java,true,data);
    }
}
